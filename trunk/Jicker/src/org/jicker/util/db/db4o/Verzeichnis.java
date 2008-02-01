package org.jicker.util.db.db4o;
public class Verzeichnis {
	private String verzeichnisName;
	private int tiefe;

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
	
	public String toString(){
	return verzeichnisName;
}
}
