package org.jicker.flickrj.db4o;

public class Sets {

	private String id;
	private String primaryPhoto;
	private String secrect;
	private String server;
	private String farm;
	private int photoCount;
	private String title;
	private String description;

	public Sets(String title) {
		this.title = title;
	}
}