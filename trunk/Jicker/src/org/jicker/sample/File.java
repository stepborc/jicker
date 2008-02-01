package org.jicker.sample;

public class File {
	private Dir org;
	private String filename;

	public File(Dir org, String filename) {
		this.org = org;
		this.filename = filename;
	}

	public Dir getOrg() {
		return org;
	}

	public String getFilename() {
		return filename;
	}

	public String toString() {
		return filename;
	}
}
