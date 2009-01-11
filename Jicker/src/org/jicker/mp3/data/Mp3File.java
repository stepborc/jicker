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
	
public Mp3File(String file, String dir, long date, String absotuteDir, File absolutFile){
	this.setFile(file);
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
	this.dir = dir;
}

public String getDir() {
	return dir;
}

public void setFile(String file) {
	this.file = file;
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
