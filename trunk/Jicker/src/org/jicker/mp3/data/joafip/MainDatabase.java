package org.jicker.mp3.data.joafip;

import java.io.File;
import java.util.Collection;
import java.util.List;

import net.sf.joafip.entity.EnumFilePersistenceCloseAction;
import net.sf.joafip.service.DataAccessSession;
import net.sf.joafip.service.FilePersistence;
import net.sf.joafip.service.FilePersistenceClassNotFoundException;
import net.sf.joafip.service.FilePersistenceDataCorruptedException;
import net.sf.joafip.service.FilePersistenceException;
import net.sf.joafip.service.FilePersistenceInvalidClassException;
import net.sf.joafip.service.FilePersistenceNotSerializableException;
import net.sf.joafip.store.service.copier.Copier;
import net.sf.joafip.store.service.copier.CopierException;

import org.apache.log4j.Logger;

public final class MainDatabase {
	//Logger starten
	private static final Logger _log = Logger.getLogger(MainDatabase.class);
	//Speicherort der DB
	private final static File STORAGE_DIRECTORY = new File("runtime");
	// ???
	private final static Copier copier = Copier.getInstance();

	private MainDatabase() {
		super();
	}

	public static void main(final String[] args) {
		final MainDatabase main = new MainDatabase();
		try {
			STORAGE_DIRECTORY.delete();
			STORAGE_DIRECTORY.mkdirs();
			main.run();
		} catch (Exception exception) {
			//_log.error("run error", exception);
		}
	}

	private void run() throws FilePersistenceException,
			FilePersistenceInvalidClassException,
			FilePersistenceNotSerializableException,
			FilePersistenceClassNotFoundException,
			FilePersistenceDataCorruptedException, CopierException {

		/* open file persistence */
		final FilePersistence filePersistence =
		/**/new FilePersistence(STORAGE_DIRECTORY, false, false);
		final DataAccessSession session = filePersistence
				.createDataAccessSession();

		/* store new item table (empty) */
		session.open();
		ItemTable itemTable = new ItemTable();
		session.setObject("itemTable", itemTable);
		session.close(EnumFilePersistenceCloseAction.SAVE);

		/* populate item table */
		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		Item item = new Item("123", 10);
		itemTable.addItem(item);
		item = new Item("456", 10);
		itemTable.addItem(item);
		item = new Item("789", 11);
		itemTable.addItem(item);
		session.close(EnumFilePersistenceCloseAction.SAVE);

		/* retrieve by code */
		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		item = itemTable.getItemByCode("456");
		// _log.info("retrieved by code 456: " + item);
		item = (Item) copier.copy(item);// create accessible out of session
		session.close(EnumFilePersistenceCloseAction.DO_NOT_SAVE);
		//_log.info("retrieved by code 456:" + item);

		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		final Collection<Item> collection = itemTable.getAllItem();
		//_log.info("all item: " + collection);
		session.close(EnumFilePersistenceCloseAction.DO_NOT_SAVE);

		/* update */
		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		item = itemTable.getItemByCode("123");
		item.setPrice(9);
		session.close(EnumFilePersistenceCloseAction.SAVE);

		/* delete */
		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		item = itemTable.removeItem("456");
		//_log.info("deleted " + item);
		session.close(EnumFilePersistenceCloseAction.SAVE);

		/* retrieve by price */
		session.open();
		itemTable = (ItemTable) session.getObject("itemTable");
		final List<Item> list = itemTable
				.getItemByPrice(9/* fromPrice */, 11/* toPrice */);
		//_log.info("retrieved by price from 9 thru 11:" + list);
		session.close(EnumFilePersistenceCloseAction.DO_NOT_SAVE);

		/* close file persistence */
		filePersistence.close();
	}
}
