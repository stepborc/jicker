/*
 * 
 */
package org.jicker.sample;

// TODO: Auto-generated Javadoc
/**
 * The Class File.
 */
public class File {
	
	/** The org. */
	private Dir org;
	
	/** The filename. */
	private String filename;

	/**
	 * Instantiates a new file.
	 * 
	 * @param org the org
	 * @param filename the filename
	 */
	public File(Dir org, String filename) {
		this.org = org;
		this.filename = filename;
	}

	/**
	 * Gets the org.
	 * 
	 * @return the org
	 */
	public Dir getOrg() {
		return org;
	}

	/**
	 * Gets the filename.
	 * 
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return filename;
	}
}
