/*
 * 
 */
package org.jicker.util.db.db4o;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

// TODO: Auto-generated Javadoc
/**
 * The Class First.
 */
public class First {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		// Testverzeichnisse generieren
		for (int n = 1; n < 10; n++) {
			String verzName = "e:/bilder/test/test" + n + "/";
			boolean status = new File(verzName).mkdir();
			for (int m = 1; m < 10; m++) {
				String fileName = "test" + m + ".jpg";
				boolean statusFile = new File(verzName + fileName)
						.createNewFile();
			}
		}
		// Datenbank löschen
		new File("first.db4o").delete();
		// Neue Datenbank anlegen
		ObjectContainer db = Db4o.openFile("first.db4o");
		// Datenbank befüllen
		try {
			File dir = new File("e:/bilder/test/");
			JickerFilter filter = new JickerFilter();
			// Aufruf des DirBrowser mit Angabe des Verzeichnisses und den
			// Dateiendungen
			// Mehrfachnennungen sind erlaubt.
			List browse = new DirBrowser(filter
					.createFilter(new String[] { ".jpg" }), -1).find(dir);
			browse.remove(browse.size() - 1);
			Verzeichnis verzeichnis;
			for (int n = 0; n < browse.size(); n++) {
				System.out.println(browse.get(n));
				if (((File) browse.get(n)).isFile()) {
					verzeichnis = new Verzeichnis(browse.get(n).toString().substring(0, browse.get(n).toString().lastIndexOf("\\")));
					Datei datei = new Datei(browse.get(n).toString(), verzeichnis);
					db.set(datei);
				} else {
					verzeichnis = new Verzeichnis(browse.get(n).toString());
					db.set(verzeichnis);
				}

			}
			/*
			 * for (int n = 1;n<10;n++){ String verzName = "E:/bilder/test" + n +
			 * "/"; Verzeichnis verzeichnis = new Verzeichnis(verzName);
			 * db.set(verzeichnis); for (int m =1;m<10;m++){ Datei datei = new
			 * Datei(verzName + "test" + m + ".jpg", verzeichnis);
			 * db.set(datei); //System.out.println((verzName + "test" + m +
			 * ".jpg").substring((verzName + "test" + m +
			 * ".jpg").lastIndexOf("/")+1)); } }
			 */
			// Suche nach allen Dateien
			ObjectSet<Datei> result = db.get(Datei.class);
			System.out.println(result.size() + " Datei(en)");
			while (result.hasNext()) {
				System.out.println(result.next());
			}
			System.out.println("---");

			// Suche nach allen Verzeichnissen
			ObjectSet resultVerzeichnis = db.get(Verzeichnis.class);

			System.out.println(resultVerzeichnis.size() + " Verzeichniss(e)");
			while (resultVerzeichnis.hasNext()) {
				System.out.println(resultVerzeichnis.next());
			}
			System.out.println("---");
			// Suche nach allen Einträgen für ein bestimmtes Verzeichnis
			String tmpVerz = "e:/bilder/test/test1";

			Verzeichnis verzeichnisProto = new Verzeichnis(tmpVerz);
			Datei dateiProto = new Datei(null, verzeichnisProto);
			//dateiProto.setVerzeichnis(verzeichnisProto);
			ObjectSet result3 = db.get(dateiProto);

			System.out.println(result3.size() + " Datei(en) im Verzeichnis " + tmpVerz);
			while (result3.hasNext()) {
				System.out.println(result3.next());
			}
			System.out.println("---");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

}
