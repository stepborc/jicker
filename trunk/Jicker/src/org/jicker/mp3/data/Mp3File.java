/**
 * 
 */
package org.jicker.mp3.data;

import java.io.File;

/**
 * @author sborcher
 * 
 */
public class Mp3File {
	private String file;
	private String dir;
	private long date;
	private String absolutDir;
	private File absolutFile;

	public Mp3File(String file, String dir, long date, String absotuteDir,
			File absolutFile) {
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

	public void setDir(String dir) {
		// das trimmen des Strings nur ausführen wenn Wert ungleich null
		// z.B. für QueryByExample wichtig

		if (dir != null) {
			File testDir = new File(dir);
			if (!testDir.isDirectory()) {
				dir = dir.substring(0, dir.lastIndexOf("\\") + 1);
			}else{
				dir = dir + "\\";
			}
		}
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}

	public void setFile(String file, String dir) {
		// File testFile = new File(dir + file);
		if (dir != null && file != null) {
			File testFile = new File(dir);
			if (testFile.isFile()) {
				this.file = file;
			} else {
				this.file = null;
			}
		} else {
			this.file = null;
		}
	}

	public String getFile() {
		return file;
	}

	public void setAbsolutDir(String absolutDir) {
		this.absolutDir = absolutDir;
	}

	public String getAbsolutDir() {
		return absolutDir;
	}

	public void setAbsolutFile(File absolutFile) {
		this.absolutFile = absolutFile;
	}

	public File getAbsolutFile() {
		return absolutFile;
	}
}
