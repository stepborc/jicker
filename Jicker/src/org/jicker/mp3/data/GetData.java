package org.jicker.mp3.data;

import java.io.File;
import java.util.List;

import org.jicker.mp3.JickerMp3Globals;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class GetData {
	public GetData() throws SecurityException, NoSuchFieldException {
		// File dbFile = new File(dbName);
		File dbFile = new File(JickerMp3Globals.dbName);
		// Datenbank löschen
		dbFile.delete();
		// Collection für das Ergebnis des Verzeichnisscans
		List<File> browse = null;
		// Wenn Datenbank existiert
		if (!dbFile.exists()) {
			// Filesystem scannen, nach Dateien mit der Endung mp3
			File dir = new File(JickerMp3Globals.baseMusicDir);
			JickerFilter filter = new JickerFilter();
			browse = new DirBrowser(filter
					.createFilter(new String[] { ".mp3" }), -1).find(dir);
			System.out.println(browse.size());
		} else {
			// DB synchronisieren
		}
		// Datenbank öffnen oder anlegen
		ObjectContainer db = Db4o.openFile(JickerMp3Globals.dbName);

		// Datenobjekt initialisieren
		Mp3File mp3File = null;
		// Alle Positionen des Ergebnisses ansteuern
		for (int n = 0; n < browse.size(); n++) {
			// Neues Objekt vom Type Mp3File anlegen
			mp3File = new Mp3File(browse.get(n).getName(), browse.get(n)
					.getPath(), browse.get(n).lastModified());
			// Abspeichern
			db.store(mp3File);
			// Zahl als Fortschrittsangabe ausgeben
			System.out.println(n + 1);
		}
		// Datenbank schließen
		db.close();
		// Ergebnisliste löschen
		browse.clear();
	}
}
