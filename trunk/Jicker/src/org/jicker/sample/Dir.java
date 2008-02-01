package org.jicker.sample;
public class Dir {
	private Dir org;
	private String dirname;

	public Dir(Dir org, String dirname) {
		this.org = org;
		this.dirname = dirname;
	}

	public Dir getOrg() {
		return org;
	}

	public String getDirname() {
		return dirname;
	}
	public String toString(){
		return dirname;
	}
}
