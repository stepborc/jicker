package org.jicker.sample;

import java.io.File;
import java.util.List;

import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class DirFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 new File("dirfile.db4o").delete();
		ObjectContainer db = Db4o.openFile("dirfile.db4o");
		File dir = new File("e:/bilder/");
		JickerFilter filter = new JickerFilter();
		// Aufruf des DirBrowser mit Angabe des Verzeichnisses und den
		// Dateiendungen
		// Mehrfachnennungen sind erlaubt.
		List browse = new DirBrowser(filter
				.createFilter(new String[] { ".jpg" }), 1).find(dir);
		browse.remove(browse.size()-1);
		for (int n = 0; n <= browse.size() - 1; n++) {
			if (((File) browse.get(n)).isFile()) {
				// long csum = FileUtils.checksum((File) browse.get(n),new
				// CRC32()).getValue();
				System.out.println("Datei: " + browse.get(n).toString());
				storeFile(db,null,browse.get(n).toString());
			} else {
				System.out.println("Verzeichnis: " + browse.get(n).toString());
				storeDir(db,null,browse.get(n).toString());
				List browseDir = new DirBrowser(filter
						.createFilter(new String[] { ".jpg" }), 1).find(new File(browse.get(n).toString()));
				for(int m = 0;m<=browseDir.size()-1;m++){
					if (((File) browseDir.get(m)).isFile()) {
						System.out.println("Datei: " + browseDir.get(m).toString());
						storeFile(db,null,browseDir.get(m).toString());
					}
				}
			}
		}

		try {
			// storeFirstPilot(db);
			// storeSecondPilot(db);
			// retrieveAllPilots(db);
		} finally {
			db.close();
		}

	}

	public static void storeDir(ObjectContainer db, Dir dirObject, String dirname) {
		Dir dir = new Dir(dirObject, dirname);
		db.set(dir);
		//System.out.println("Gespeichert " + dir);
	}

	public static void storeFile(ObjectContainer db, Dir dirObject, String filename) {
		org.jicker.sample.File file = new org.jicker.sample.File(dirObject, filename);
		db.set(file);
		//System.out.println("Stored " + file);
	}
}
