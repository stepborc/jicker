/*
 * 
 */
package org.jicker.util.db.db4o;

/**
 * The Class Verzeichnis.
 */
public class Verzeichnis {

	/** The verzeichnis name. */
	private String verzeichnisName;

	/** The tiefe. */
	private int tiefe;

	/**
	 * Konstruktor mit Variante verzeichsName
	 * 
	 * @param verzeichnisName
	 * Der zu übergebene Verzeichnisname, in dem die doppelten
	 * Backslashes durch einen Slash ersetzt werden
	 */
	public Verzeichnis(String verzeichnisName) {
		verzeichnisName = verzeichnisName.replaceAll("\\\\", "/");
		if (verzeichnisName.lastIndexOf("/") != verzeichnisName.length() - 1) {
			this.verzeichnisName = verzeichnisName + "/";
		} else {
			this.verzeichnisName = verzeichnisName;
		}
		String tmp = verzeichnisName;
		int tiefe = 0;
		int counter = tmp.indexOf("/");

		while (tmp.indexOf("/", counter + 1) != -1) {
			counter = tmp.indexOf("/", counter + 1);
			tiefe++;
		}
		this.setTiefe(tiefe);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return verzeichnisName;
	}

	public void setTiefe(int tiefe) {
		this.tiefe = tiefe;
	}

	public int getTiefe() {
		return tiefe;
	}
}
