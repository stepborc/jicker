/*
 * 
 */
package org.jicker.flickrj.db4o;

import com.aetrion.flickr.photosets.Photoset;

// TODO: Auto-generated Javadoc
/**
 * The Class DbSets.
 */
public class DbSets {

	/** The id. */
	private String id;
	
	/** The primary photo. */
	private String primaryPhoto;
	
	/** The secrect. */
	private String secrect;
	
	/** The server. */
	private String server;
	
	/** The farm. */
	private String farm;
	
	/** The photo count. */
	private int photoCount;
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new db sets.
	 * 
	 * @param set the set
	 */
	public DbSets(Photoset set) {
		this.id = set.getId();
		// this.primaryPhoto = set.getPrimaryPhoto();
		this.secrect = set.getSecret();
		this.server = set.getServer();
		this.farm = set.getFarm();
		this.photoCount = set.getPhotoCount();
		this.title = set.getTitle();
		this.description = set.getDescription();
		// this.user = set.getOwner();
		this.url = set.getUrl();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the primary photo.
	 * 
	 * @return the primary photo
	 */
	public String getPrimaryPhoto() {
		return primaryPhoto;
	}
	
	/**
	 * Gets the secrect.
	 * 
	 * @return the secrect
	 */
	public String getSecrect() {
		return secrect;
	}	
	
	/**
	 * Gets the server.
	 * 
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Gets the farm.
	 * 
	 * @return the farm
	 */
	public String getFarm() {
		return farm;
	}

	/**
	 * Gets the photo count.
	 * 
	 * @return the photo count
	 */
	public int getPhotoCount() {
		return photoCount;
	}
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

}