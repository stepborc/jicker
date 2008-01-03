package org.jicker.flickrj.db4o;

import com.aetrion.flickr.photosets.Photoset;

public class Sets {

	private String id;
	private String primaryPhoto;
	private String secrect;
	private String server;
	private String farm;
	private int photoCount;
	private String title;
	private String description;
	private String url;

	public Sets(Photoset set) {
		this.id = set.getId();
		//this.primaryPhoto = set.getPrimaryPhoto();
		this.secrect = set.getSecret();
		this.server = set.getServer();
		this.farm = set.getFarm();
		this.photoCount = set.getPhotoCount();
		this.title = set.getTitle();
		this.description = set.getDescription();
		//this.user = set.getOwner();
		this.url = set.getUrl();
	}
}