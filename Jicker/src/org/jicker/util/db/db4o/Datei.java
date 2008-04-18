/*
 * 
 */
package org.jicker.util.db.db4o;
import java.io.File;
import java.io.IOException;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Datei.
 */
public class Datei {
	
	/** The datei name. */
	private String dateiName;
	
	/** The verz name. */
	private String verzName;
	
	/** The full name. */
	private String fullName;
	
	/** The crc32. */
	private long crc32;
	
	/** The verzeichnis name. */
	private Verzeichnis verzeichnisName;
	

	/**
	 * Instantiates a new datei.
	 * 
	 * @param dateiName the datei name
	 * @param verzeichnisName the verzeichnis name
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Gets the datei name.
	 * 
	 * @return the datei name
	 */
	public String getDateiName() {
		return dateiName;
	}

	/**
	 * Gets the crc32.
	 * 
	 * @return the crc32
	 */
	public long getCrc32() {
		return crc32;
	}

	/**
	 * Sets the verzeichnis.
	 * 
	 * @param verzeichnisName the new verzeichnis
	 */
	public void setVerzeichnis(Verzeichnis verzeichnisName) {
		this.verzeichnisName = verzeichnisName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return dateiName + "(" + crc32 + ")" + "\t" + verzName + "\t" + fullName;
	}
}
