package org.jicker.mp3.data;

import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.jicker.mp3.JickerMp3Globals;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class ReadData {
	/**
	 * 
	 */
	public ReadData(String dbName) {
		ObjectContainer db = Db4o.openFile(dbName);
		System.out.println("--- Verzeichnisse ---");
		//QBE
		List<Mp3Dir> mp3DirTest = db.query(new Predicate<Mp3Dir>(){
			public boolean match(Mp3Dir mp3DirTest){
				return mp3DirTest.getTiefe() == 2;
			}
		});
		mp3DirTest.iterator();
		
		Mp3Dir mp3Dir = new Mp3Dir(null, 0, null, 1);
		ObjectSet<Mp3Dir> mp3DirList = db.get(mp3Dir);
		//ObjectSet<Mp3Dir> mp3DirList = db.queryByExample(mp3Dir);
		Mp3Dir mp3DirGet = null;
		while (mp3DirList.hasNext()) {
			mp3DirGet = mp3DirList.next();
			System.out.print(mp3DirGet.getTiefe() + "->"+mp3DirGet.getDate()+ ":");
			for (int n = 0;n<mp3DirGet.getTiefe();n++){
				System.out.print("\t");
			}
			System.out
					.println(mp3DirGet.getDirName());
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
