package org.jicker.mp3.data;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.jicker.mp3.JickerMp3Globals;
import org.jicker.mp3.dbobject.Mp3Dir;
import org.jicker.mp3.dbobject.Mp3File;
import org.jicker.mp3.dbobject.Mp3File_;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class GetMp3Files {
	private String musicBase;
	private Boolean delDB;

	public GetMp3Files(String musicBase, boolean delDB) {
		setMusicBase(musicBase);
		setDelDB(delDB);
		this.getDataNew();
	}

	public GetMp3Files() {
		// setMusicBase(JickerMp3Globals.baseMusicDir);
		// Datenbank löschen?
		// setDelDB(true);
		// getData();
	}

	/**
	 * 
	 * 
	 */

	public void getDataNew() {
		// Datenbankname setzen
		String dbName = JickerMp3Globals.dbName;
		File dbFile = new File(dbName);
		// Prüfen on Datenbankfile exisitiert und dann löschen
		if (delDB && dbFile.exists()) {
			dbFile.delete();
		}
		// Collection für das Ergebnis des Verzeichnisscans
		List<File> browse = null;
		// Wenn Datenbank existiert
		if (!dbFile.exists()) {
			// Filesystem scannen, nach Dateien mit der Endung mp3
			File dir = new File(musicBase);
			JickerFilter filter = new JickerFilter();
			browse = new DirBrowser(filter
					.createFilter(new String[] { ".mp3" }), -1).find(dir);
			Logger.getAnonymousLogger().info(
					"Anzahl Einträge: " + browse.size());
		} else {
			// DB synchronisieren
		}

		// Datenbank öffnen oder anlegen
		// ObjectContainer db = Db4o.openFile(JickerMp3Globals.dbName);
		ODB odb = ODBFactory.open(JickerMp3Globals.dbName);
		// Datenobjekt initialisieren
		Mp3File mp3File = null;
		Mp3Dir mp3Dir = null;
		Logger.getAnonymousLogger().info(
				"Daten werden gespeichert in " + JickerMp3Globals.dbName
						+ " gespeichert.");
		// Fortschrittsausgabe: 20 Punkte ausgeben. Jeder Punkt entspricht 5%.
		// Anzahl aller Einträge durch 20 teilen
		int fortschritt = browse.size() / 20;
		// Wert für if-clause berechnen
		int fortschrittValue = fortschritt;
		// Schleife über alle browse Einträge
		for (int n = 0; n < browse.size(); n++) {
			if (fortschrittValue == n) {
				if ((fortschrittValue + fortschritt) < browse.size()) {
					System.out.print(".");
				} else {
					System.out.println(".");
				}
				fortschrittValue = fortschrittValue + fortschritt;
			}
			if (browse.get(n).isDirectory()) {
				// mp3Dir = new Mp3Dir(browse.get(n).getPath(),
				// browse.get(n).lastModified(), null, 0);
				// odb.store(mp3Dir);
			} else {
				mp3File = new Mp3File(browse.get(n));
				odb.store(mp3File);
			}
		}
		// Datenbank schließen
		odb.close();
		// Ergebnisliste löschen
		browse.clear();
		Logger.getAnonymousLogger().info(
				"Scan der musicBase = " + musicBase + " beendet.");
	}

	/**
	 * @return the musicBase
	 */
	public String getMusicBase() {
		return musicBase;
	}

	/**
	 * @param musicBase
	 *            the musicBase to set
	 */
	public void setMusicBase(String musicBase) {
		this.musicBase = musicBase;
	}

	/**
	 * @return the delDB
	 */
	public Boolean getDelDB() {
		return delDB;
	}

	/**
	 * @param delDB
	 *            the delDB to set
	 */
	public void setDelDB(Boolean delDB) {
		this.delDB = delDB;
	}
}
