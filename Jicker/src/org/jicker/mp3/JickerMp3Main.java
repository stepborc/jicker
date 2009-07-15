package org.jicker.mp3;

import org.jicker.mp3.data.GetMp3File;
import org.jicker.mp3.dbobject.Mp3File;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class JickerMp3Main {

	public JickerMp3Main(String verzeichnis) {
		// Schritt 1: Einscannen der Dateien
		GetMp3File mp3List = new GetMp3File(verzeichnis);
		System.out.println("Dateien gescannt");
		// Schritt 2: Überführen der Ergebnisse in DB
		new CreateDB(mp3List.getMp3List());
		ODB odb = ODBFactory.open("jickerMp3.odb");
		System.out.println(odb.getValues(new ValuesCriteriaQuery(Mp3File.class)
				.count("mp3Complett")));

		Objects<Mp3File> mp3Files = odb.getObjects(Mp3File.class);
		// Schritt 3: Alle Einträge in der Datenbank lesen und ID3Tags auslesen
		Mp3File mp3File = null;
		while (mp3Files.hasNext()) {
			mp3File = mp3Files.next();
			System.out.println(mp3File.getMp3File());
		}
		odb.close();
	}
}
