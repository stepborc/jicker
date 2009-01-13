package org.jicker.mp3.data;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ReadData {
	/**
	 * 
	 */
	public ReadData(String dbName) {
		ObjectContainer db = Db4o.openFile(dbName);
		Mp3File mp3File = new Mp3File(null, null, 0, null, null);
		;
		ObjectSet<Mp3File> mp3FileList = db.queryByExample(mp3File);
		Mp3File mp3FileGet = null;
		while (mp3FileList.hasNext()) {
			mp3FileGet = mp3FileList.next();
			// File m = new File(mp3FileGet.getFile());
			// if (m.isFile()){
			System.out.println(mp3FileGet.getDir());
			// }
		}
		db.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SecurityException,
			NoSuchFieldException {
		String dbName = "JickerMp2.yap";
		File laufwerk = new File("e:/");
		boolean view = FileSystemView.getFileSystemView().isDrive(laufwerk);
		if (view) {
			new ReadData(dbName);
		}
	}
}
