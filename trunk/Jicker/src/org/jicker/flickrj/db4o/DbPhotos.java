/*
 * 
 */
package org.jicker.flickrj.db4o;

import com.aetrion.flickr.photos.Editability;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Permissions;
import com.aetrion.flickr.photos.Photo;

// TODO: Auto-generated Javadoc
/**
 * The Class DbPhotos.
 */
public class DbPhotos {

	/** The comments. */
	private int comments;
	
	/** The date added. */
	private java.util.Date dateAdded;
	
	/** The date posted. */
	private java.util.Date datePosted;
	
	/** The date taken. */
	private java.util.Date dateTaken;
	
	/** The description. */
	private java.lang.String description;
	
	/** The editability. */
	private Editability editability;
	
	/** The farm. */
	private java.lang.String farm;
	
	/** The geo data. */
	private GeoData geoData;
	
	/** The icon farm. */
	private java.lang.String iconFarm;
	
	/** The icon server. */
	private java.lang.String iconServer;
	
	/** The id. */
	private java.lang.String id;
	// Deprecated
	/** The large as stream. */
	private java.io.InputStream largeAsStream;
	// Deprecated
	/** The large image. */
	private java.awt.image.BufferedImage largeImage;
	
	/** The large url. */
	private java.lang.String largeUrl;
	
	/** The last update. */
	private java.util.Date lastUpdate;
	
	/** The license. */
	private java.lang.String license;
	// Deprecated
	/** The medium as stream. */
	private java.io.InputStream mediumAsStream;
	// Deprecated.
	/** The medium image. */
	private java.awt.image.BufferedImage mediumImage;
	
	/** The medium url. */
	private java.lang.String mediumUrl;
	
	/** The notes. */
	@SuppressWarnings("unchecked")
	private java.util.Collection notes;
	// Deprecated
	/** The original as stream. */
	private java.io.InputStream originalAsStream;
	
	/** The original format. */
	private java.lang.String originalFormat;
	// Deprecated
	/** The original image. */
	private java.awt.image.BufferedImage originalImage;
	
	/** The original secret. */
	private java.lang.String originalSecret;
	
	/** The original url. */
	private java.lang.String originalUrl;
	
	/** The owner. */
	private DbUser owner;
	
	/** The permissions. */
	private Permissions permissions;
	
	/** The rotation. */
	private int rotation;
	
	/** The secret. */
	private java.lang.String secret;
	
	/** The server. */
	private java.lang.String server;
	// Deprecated
	// private java.io.InputStream SmallAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallImage;
	// Deprecated
	// private java.io.InputStream SmallSquareAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallSquareImage;
	/** The small square url. */
	private java.lang.String smallSquareUrl;
	
	/** The small url. */
	private java.lang.String smallUrl;
	
	/** The tags. */
	@SuppressWarnings("unchecked")
	private java.util.Collection tags;
	
	/** The taken granularity. */
	private java.lang.String takenGranularity;
	// Deprecated
	// private java.io.InputStream ThumbnailAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage ThumbnailImage;
	/** The thumbnail url. */
	private java.lang.String thumbnailUrl;
	
	/** The title. */
	private java.lang.String title;
	
	/** The url. */
	private java.lang.String url;
	
	/** The urls. */
	@SuppressWarnings("unchecked")
	private java.util.Collection urls;
	
	/** The views. */
	private int views;
	
	/** The has geo data. */
	private boolean hasGeoData;
	
	/** The is family flag. */
	private boolean isFamilyFlag;
	
	/** The is favorite. */
	private boolean isFavorite;
	
	/** The is friend flag. */
	private boolean isFriendFlag;
	
	/** The is primary. */
	private boolean isPrimary;
	
	/** The is public flag. */
	private boolean isPublicFlag;
	
	/** The set id. */
	private String setId;

	
	/**
	 * Instantiates a new db photos.
	 * 
	 * @param p the p
	 * @param setId the set id
	 */
	public DbPhotos(Photo p, String setId) {
		if (p != null){
		this.comments = p.getComments();
		this.dateAdded = p.getDateAdded();
		this.datePosted = p.getDatePosted();
		this.dateTaken = p.getDateTaken();
		this.description = p.getDescription();
		this.editability = p.getEditability();
		this.farm = p.getFarm();
		this.geoData = p.getGeoData();
		this.hasGeoData = p.hasGeoData();
		this.iconFarm = p.getIconFarm();
		this.iconServer = p.getIconServer();
		this.id = p.getId();
		this.isFamilyFlag = p.isFamilyFlag();
		this.isFavorite = p.isFavorite();
		this.isFriendFlag = p.isFriendFlag();
		this.isPrimary = p.isPrimary();
		this.isPublicFlag = p.isPublicFlag();
		//this.largeAsStream = p.getLargeAsStream();
		//this.largeImage = p.getLargeImage();
		this.largeUrl = p.getLargeUrl();
		this.lastUpdate = p.getLastUpdate();
		this.license = p.getLicense();
		//this.mediumAsStream = p.getMediumAsStream();
		//this.mediumImage = p.getMediumImage();
		this.mediumUrl = p.getMediumUrl();
		this.notes = p.getNotes();
		//this.originalAsStream = p.getOriginalAsStream();
		//this.originalFormat = p.getOriginalFormat();
		//this.originalImage = p.getOriginalImage();
		//this.originalSecret = p.getOriginalImage();
		//this.owner = p.getOwner();
		this.permissions = p.getPermissions();
		this.rotation = p.getRotation();
		this.secret = p.getSecret();
		this.server = p.getServer();
		this.smallSquareUrl = p.getSmallSquareUrl();
		this.smallUrl = p.getSmallUrl();
		this.tags = p.getTags();
		this.takenGranularity = p.getTakenGranularity();
		this.thumbnailUrl = p.getThumbnailUrl();
		this.title = p.getTitle();
		this.url = p.getUrl();
		this.urls = p.getUrls();
		this.views = p.getViews();
		}
		this.setId = setId;
		//}else{
		//	this.setId = setId;
		//}
	}

	/**
	 * Gets the comments.
	 * 
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}

	/**
	 * Gets the date added.
	 * 
	 * @return the date added
	 */
	public java.util.Date getDateAdded() {
		return dateAdded;
	}

	/**
	 * Gets the date posted.
	 * 
	 * @return the date posted
	 */
	public java.util.Date getDatePosted() {
		return datePosted;
	}

	/**
	 * Gets the date taken.
	 * 
	 * @return the date taken
	 */
	public java.util.Date getDateTaken() {
		return dateTaken;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Gets the editability.
	 * 
	 * @return the editability
	 */
	public Editability getEditability() {
		return editability;
	}

	/**
	 * Gets the farm.
	 * 
	 * @return the farm
	 */
	public java.lang.String getFarm() {
		return farm;
	}

	/**
	 * Gets the geo data.
	 * 
	 * @return the geo data
	 */
	public GeoData getGeoData() {
		return geoData;
	}

	/**
	 * Gets the icon farm.
	 * 
	 * @return the icon farm
	 */
	public java.lang.String getIconFarm() {
		return iconFarm;
	}

	/**
	 * Gets the icon server.
	 * 
	 * @return the icon server
	 */
	public java.lang.String getIconServer() {
		return iconServer;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * Gets the large as stream.
	 * 
	 * @return the large as stream
	 */
	public java.io.InputStream getLargeAsStream() {
		return largeAsStream;
	}

	/**
	 * Gets the large image.
	 * 
	 * @return the large image
	 */
	public java.awt.image.BufferedImage getLargeImage() {
		return largeImage;
	}

	/**
	 * Gets the large url.
	 * 
	 * @return the large url
	 */
	public java.lang.String getLargeUrl() {
		return largeUrl;
	}

	/**
	 * Gets the last update.
	 * 
	 * @return the last update
	 */
	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * Gets the license.
	 * 
	 * @return the license
	 */
	public java.lang.String getLicense() {
		return license;
	}

	/**
	 * Gets the medium as stream.
	 * 
	 * @return the medium as stream
	 */
	public java.io.InputStream getMediumAsStream() {
		return mediumAsStream;
	}

	/**
	 * Gets the medium image.
	 * 
	 * @return the medium image
	 */
	public java.awt.image.BufferedImage getMediumImage() {
		return mediumImage;
	}

	/**
	 * Gets the medium url.
	 * 
	 * @return the medium url
	 */
	public java.lang.String getMediumUrl() {
		return mediumUrl;
	}

	/**
	 * Gets the notes.
	 * 
	 * @return the notes
	 */
	@SuppressWarnings("unchecked")
	public java.util.Collection getNotes() {
		return notes;
	}

	/**
	 * Gets the original as stream.
	 * 
	 * @return the original as stream
	 */
	public java.io.InputStream getOriginalAsStream() {
		return originalAsStream;
	}

	/**
	 * Gets the original format.
	 * 
	 * @return the original format
	 */
	public java.lang.String getOriginalFormat() {
		return originalFormat;
	}

	/**
	 * Gets the original image.
	 * 
	 * @return the original image
	 */
	public java.awt.image.BufferedImage getOriginalImage() {
		return originalImage;
	}

	/**
	 * Gets the original secret.
	 * 
	 * @return the original secret
	 */
	public java.lang.String getOriginalSecret() {
		return originalSecret;
	}

	/**
	 * Gets the original url.
	 * 
	 * @return the original url
	 */
	public java.lang.String getOriginalUrl() {
		return originalUrl;
	}

	/**
	 * Gets the owner.
	 * 
	 * @return the owner
	 */
	public DbUser getOwner() {
		return owner;
	}

	/**
	 * Gets the permissions.
	 * 
	 * @return the permissions
	 */
	public Permissions getPermissions() {
		return permissions;
	}

	/**
	 * Gets the rotation.
	 * 
	 * @return the rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Gets the secret.
	 * 
	 * @return the secret
	 */
	public java.lang.String getSecret() {
		return secret;
	}

	/**
	 * Gets the server.
	 * 
	 * @return the server
	 */
	public java.lang.String getServer() {
		return server;
	}

	/**
	 * Gets the small square url.
	 * 
	 * @return the small square url
	 */
	public java.lang.String getSmallSquareUrl() {
		return smallSquareUrl;
	}

	/**
	 * Gets the small url.
	 * 
	 * @return the small url
	 */
	public java.lang.String getSmallUrl() {
		return smallUrl;
	}

	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	@SuppressWarnings("unchecked")
	public java.util.Collection getTags() {
		return tags;
	}

	/**
	 * Gets the taken granularity.
	 * 
	 * @return the taken granularity
	 */
	public java.lang.String getTakenGranularity() {
		return takenGranularity;
	}

	/**
	 * Gets the thumbnail url.
	 * 
	 * @return the thumbnail url
	 */
	public java.lang.String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * Gets the urls.
	 * 
	 * @return the urls
	 */
	@SuppressWarnings("unchecked")
	public java.util.Collection getUrls() {
		return urls;
	}

	/**
	 * Gets the views.
	 * 
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * Checks if is checks for geo data.
	 * 
	 * @return true, if is checks for geo data
	 */
	public boolean isHasGeoData() {
		return hasGeoData;
	}

	/**
	 * Checks if is family flag.
	 * 
	 * @return true, if is family flag
	 */
	public boolean isFamilyFlag() {
		return isFamilyFlag;
	}

	/**
	 * Checks if is favorite.
	 * 
	 * @return true, if is favorite
	 */
	public boolean isFavorite() {
		return isFavorite;
	}

	/**
	 * Checks if is friend flag.
	 * 
	 * @return true, if is friend flag
	 */
	public boolean isFriendFlag() {
		return isFriendFlag;
	}

	/**
	 * Checks if is primary.
	 * 
	 * @return true, if is primary
	 */
	public boolean isPrimary() {
		return isPrimary;
	}

	/**
	 * Checks if is public flag.
	 * 
	 * @return true, if is public flag
	 */
	public boolean isPublicFlag() {
		return isPublicFlag;
	}

	/**
	 * Gets the sets the id.
	 * 
	 * @return the sets the id
	 */
	public String getSetId() {
		return setId;
	}

}
