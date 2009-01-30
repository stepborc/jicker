/**
 * 
 */
package org.jicker.mp3.data;


/**
 * @author sborcher
 * 
 */
public class Mp3File {
	private String file;
	//private String dir;
	private Mp3Dir dir;
	private long date;
	//private String absolutDir;
	//private File absolutFile;
	//private int tiefe;

	public Mp3File(String file, Mp3Dir dir, long date) {
		this.setFile(file, dir);
		this.setDir(dir);
		this.setDate(date);
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getDate() {
		return date;
	}

	public void setDir(Mp3Dir dir) {
/*		// das trimmen des Strings nur ausführen wenn Wert ungleich null
		// z.B. für QueryByExample wichtig
		int tiefe = 0;
		if (dir != null) {
			File testDir = new File(dir);
			if (!testDir.isDirectory()) {
				dir = dir.substring(0, dir.lastIndexOf("\\") + 1);
			} else {
				dir = dir + "\\";
			}
			int index = JickerMp3Globals.baseMusicDir.length();
			//Verzeichnistiefe berechnen
			while (dir.indexOf("\\", index) != -1) {
				index = dir.indexOf("\\", index) + 1;
				tiefe = tiefe + 1;
			}
		}
		this.setTiefe(tiefe);*/
		this.dir = dir;
	}

	public Mp3Dir getDir() {
		return dir;
	}

	public void setFile(String file, Mp3Dir dir) {
		// File testFile = new File(dir + file);
/*		if (dir != null && file != null) {
			File testFile = new File(dir);
			if (testFile.isFile()) {
				this.file = file;
			} else {
				this.file = null;
			}
		} else {
			this.file = null;
		}*/
		this.file = file;
	}

	public String getFile() {
		return file;
	}

	//public void setAbsolutDir(String absolutDir) {
	//	this.absolutDir = absolutDir;
	//}

	//public String getAbsolutDir() {
	//	return absolutDir;
	//}

	//public void setAbsolutFile(File absolutFile) {
	//	this.absolutFile = absolutFile;
	//}

	//public File getAbsolutFile() {
	//	return absolutFile;
	//}

	//public void setTiefe(int tiefe) {
		//this.tiefe = tiefe;
	//}

	//public int getTiefe() {
		//return tiefe;
	//}
}
