package org.jicker.mp3.data;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.jicker.mp3.JickerMp3Globals;
import org.jicker.mp3.dbobject.Mp3Dir;
import org.jicker.mp3.dbobject.Mp3File_;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class GetData {
	private String musicBase;
	private Boolean delDB;

	public GetData(String musicBase, boolean delDB) {
		setMusicBase(musicBase);
		setDelDB(delDB);
		getData();
	}

	public GetData() {
		// setMusicBase(JickerMp3Globals.baseMusicDir);
		// Datenbank löschen?
		// setDelDB(true);
		// getData();
	}

	/**
	 * 
	 * 
	 */
	public void getData() {

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
			System.out.println(browse.size());
		} else {
			// DB synchronisieren
		}
		for (int n = 0; n < browse.size(); n++) {
			System.out.println(browse.get(n).isDirectory() + "::"
					+ browse.get(n).getName());
		}

		// Datenbank öffnen oder anlegen
		ObjectContainer db = Db4o.openFile(JickerMp3Globals.dbName);

		// Datenobjekt initialisieren
		Mp3File_ mp3File = null;
		Mp3Dir mp3Dir = null;
		// Alle Positionen des Ergebnisses ansteuern
		for (int n = 0; n < browse.size(); n++) {
			if (browse.get(n).isDirectory()) {
				mp3Dir = new Mp3Dir(browse.get(n).getPath(), browse.get(n)
						.lastModified(), null, 0);
				db.store(mp3Dir);
			} else {

			}
			// Neues Objekt vom Type Mp3File anlegen
			// mp3File = new Mp3File(browse.get(n).getName(), browse.get(n)
			// .getPath(), browse.get(n).lastModified());
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
			Logger.getAnonymousLogger().info( "Anzahl Einträge: " + browse.size());
		} else {
			// DB synchronisieren
		}

		// Datenbank öffnen oder anlegen
		ObjectContainer db = Db4o.openFile(JickerMp3Globals.dbName);

		// Datenobjekt initialisieren
		Mp3File_ mp3File = null;
		Mp3Dir mp3Dir = null;
		// Alle Positionen des Ergebnisses ansteuern
		for (int n = 0; n < browse.size(); n++) {
			if (browse.get(n).isDirectory()) {
				mp3Dir = new Mp3Dir(browse.get(n).getPath(), browse.get(n)
						.lastModified(), null, 0);
				db.store(mp3Dir);
			} else {

			}
			// Abspeichern
			db.store(mp3File);
			// Zahl als Fortschrittsangabe ausgeben
			//System.out.println((browse.size() - n - 1) + " - " + browse.size());
		}
		// Datenbank schließen
		db.close();
		// Ergebnisliste löschen
		browse.clear();
		Logger.getAnonymousLogger().info( "Scan der musicBase = " + musicBase + " beendet." );
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
