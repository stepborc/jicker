/*
 * 
 */
package org.jicker.sample;
// TODO: Auto-generated Javadoc

/**
 * The Class Dir.
 */
public class Dir {
	
	/** The org. */
	private Dir org;
	
	/** The dirname. */
	private String dirname;

	/**
	 * Instantiates a new dir.
	 * 
	 * @param org the org
	 * @param dirname the dirname
	 */
	public Dir(Dir org, String dirname) {
		this.org = org;
		this.dirname = dirname;
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
	 * Gets the dirname.
	 * 
	 * @return the dirname
	 */
	public String getDirname() {
		return dirname;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return dirname;
	}
}
