/**
 * 
 */
package org.jicker.mp3.data;

import java.io.File;

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

	public Mp3Dir(String dir, long date) {
		// this.setFile(file, dir);
		this.setDir(dir);
		this.setDate(date);
		this.setDirName(dir);
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getDate() {
		return date;
	}

	public void setDir(String dir) {
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
		this.setTiefe(tiefe);
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}

	public int getTiefe() {
		return tiefe;
	}

	public void setTiefe(int tiefe) {
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
