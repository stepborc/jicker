/**
 * 
 */
package org.jicker.mp3.data;

import org.jicker.mp3.JickerMp3Globals;

/**
 * @author sborcher
 * 
 */
public class Mp3Dir {
	private String dir;
	private long date;
	private String dirName;
	// private String absolutDir;
	// private File absolutFile;
	private int tiefe;

	public Mp3Dir(String dir, long date, String dir2, int tiefe) {
		this.dir = dir;
		this.date = date;
		this.setDirName(dir);
		this.setTiefe(dir);
	}
	
	public long getDate() {
		return date;
	}

	public String getDir() {
		return dir;
	}

	public int getTiefe() {
		return tiefe;
	}

	public void setTiefe(String dir) {
		// das trimmen des Strings nur ausführen wenn Wert ungleich null
		// z.B. für QueryByExample wichtig
		int tiefe = 0;
		if (dir != null) {
			dir = dir + "\\";
			int index = JickerMp3Globals.baseMusicDir.length();
			// Verzeichnistiefe berechnen
			while (dir.indexOf("\\", index) != -1) {
				index = dir.indexOf("\\", index) + 1;
				tiefe = tiefe + 1;
			}
		}
		this.tiefe = tiefe;
	}

	public void setDirName(String dir) {
		if (dir != null) {
			dir = dir.substring(dir.lastIndexOf("\\"),
					dir.length());
		}
		this.dirName = dir;
	}

	public String getDirName() {
		return dirName;
	}
}
