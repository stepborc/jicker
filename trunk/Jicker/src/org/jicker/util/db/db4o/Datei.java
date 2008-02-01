package org.jicker.util.db.db4o;
import java.io.File;
import java.io.IOException;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;

public class Datei {
	private String dateiName;
	private String verzName;
	private String fullName;
	private long crc32;
	private Verzeichnis verzeichnisName;
	

	public Datei(String dateiName, Verzeichnis verzeichnisName)
			throws IOException {
		String slash = "\\\\";
		
		//this.dateiName = dateiName;
		//CRC32 wird nur beim Speichern eines neuen Eintrag benötigt.
		//Bei der Formulierung einer Suche mit dem Ziel alle Dateiobjekte 
		//zu einem Verzeichnis zu erhalten, ist der Dateiname null. Dabei 
		//stört die Umwandlung nach File und die entsprechende Generierung 
		//der CRC32 Summe
		if (dateiName != null) {
			dateiName = dateiName.replaceAll(slash, "/");
			this.dateiName = dateiName.substring(dateiName.lastIndexOf("/")+1);
			this.verzName = dateiName.substring(0, dateiName.lastIndexOf("/")+1);
			this.fullName = dateiName;
			File fName = new File(dateiName);
			this.crc32 = FileUtils.checksum(fName, new CRC32()).getValue();
		}else{
			this.dateiName = dateiName;
		}
		this.verzeichnisName = verzeichnisName;
	}

	public String getDateiName() {
		return dateiName;
	}

	public long getCrc32() {
		return crc32;
	}

	public void setVerzeichnis(Verzeichnis verzeichnisName) {
		this.verzeichnisName = verzeichnisName;
	}

	public String toString() {
		return dateiName + "(" + crc32 + ")" + "\t" + verzName + "\t" + fullName;
	}
}
