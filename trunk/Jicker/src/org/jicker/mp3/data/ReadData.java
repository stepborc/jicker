package org.jicker.mp3.data;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.jicker.mp3.JickerMp3Globals;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ReadData {
	/**
	 * 
	 */
	public ReadData(String dbName) {
		ObjectContainer db = Db4o.openFile(dbName);
		System.out.println("--- Verzeichnisse ---");
		Mp3Dir mp3Dir = new Mp3Dir(null, 0);
		ObjectSet<Mp3Dir> mp3DirList = db.queryByExample(mp3Dir);
		Mp3Dir mp3DirGet = null;
		while (mp3DirList.hasNext()) {
			mp3DirGet = mp3DirList.next();
			System.out
					.println(mp3DirGet.getTiefe() + "::" + mp3DirGet.getDir());
		}
		System.out.println("--- Dateien ---");
		Mp3File mp3File = new Mp3File(null, null, 0);
		ObjectSet<Mp3File> mp3FileList = db.queryByExample(mp3File);
		Mp3File mp3FileGet = null;
		while (mp3FileList.hasNext()) {
			mp3FileGet = mp3FileList.next();
			// File m = new File(mp3FileGet.getFile());
			// if (m.isFile()){
			System.out.println(mp3FileGet.getDir() + "->" + " -> "
					+ mp3FileGet.getFile());
			// }
		}
		db.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File laufwerk = new File(JickerMp3Globals.baseMusicDir.substring(0, 3));
		boolean view = FileSystemView.getFileSystemView().isDrive(laufwerk);
		if (view) {
			new ReadData(JickerMp3Globals.dbName);
		} else {
			System.out.println("baseMusicDir: " + JickerMp3Globals.baseMusicDir
					+ " ist kein Laufwerk");
		}
	}
}
