/*
 * 
 */
package org.jicker.util.db.db4o;
// TODO: Auto-generated Javadoc

/**
 * The Class Verzeichnis.
 */
public class Verzeichnis {
	
	/** The verzeichnis name. */
	private String verzeichnisName;
	
	/** The tiefe. */
	private int tiefe;

	/**
	 * Instantiates a new verzeichnis.
	 * 
	 * @param verzeichnisName the verzeichnis name
	 */
	public Verzeichnis(String verzeichnisName) {
		verzeichnisName = verzeichnisName.replaceAll("\\\\", "/");
		if (verzeichnisName.lastIndexOf("/") != verzeichnisName.length() - 1){
			this.verzeichnisName = verzeichnisName + "/";
		}else{
			this.verzeichnisName = verzeichnisName;
		}
		String tmp = verzeichnisName ;
		int tiefe = 0;
		int counter = tmp.indexOf("/");
		
		while (tmp.indexOf("/", counter+1) != -1){
			counter = tmp.indexOf("/", counter+1);
			tiefe++;
			}
		this.tiefe = tiefe;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
	return verzeichnisName;
}
}
