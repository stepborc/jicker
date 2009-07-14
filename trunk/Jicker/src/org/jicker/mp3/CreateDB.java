/**
 * 
 */
package org.jicker.mp3;

import java.io.File;
import java.util.List;

import org.jicker.mp3.object.Mp3File;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 * @author sborcher
 * 
 */
public class CreateDB {
	public CreateDB(List list) {
		// ZU Testzwecken die Datenbank löschen
		File db = new File("jickerMp3.odb");
		if (db.exists()) {
			db.delete();
			System.out.println("Datenbank gelöscht.");
		}
		// Zu Testzwecken DB anlegen
		ODB odb = ODBFactory.open("jickerMp3.odb");
		System.out.println(odb.getName() + " angelegt.");

		for (int n = 0; n < list.size(); n++) {
			if (new File(list.get(n).toString()).isFile()){
			list.get(n).toString();
			Mp3File mp3Files = new Mp3File(list.get(n));
			odb.store(mp3Files);
			}
		}
		odb.close();
	}
}
