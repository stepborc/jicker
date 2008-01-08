package org.jicker.flickrj.db4o;

import com.aetrion.flickr.photos.Editability;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Permissions;
import com.aetrion.flickr.photos.Photo;

public class Photos {

	private int comments;
	private java.util.Date dateAdded;
	private java.util.Date datePosted;
	private java.util.Date dateTaken;
	private java.lang.String description;
	private Editability editability;
	private java.lang.String farm;
	private GeoData geoData;
	private java.lang.String iconFarm;
	private java.lang.String iconServer;
	private java.lang.String id;
	// Deprecated
	private java.io.InputStream largeAsStream;
	// Deprecated
	private java.awt.image.BufferedImage largeImage;
	private java.lang.String largeUrl;
	private java.util.Date lastUpdate;
	private java.lang.String license;
	// Deprecated
	private java.io.InputStream mediumAsStream;
	// Deprecated.
	private java.awt.image.BufferedImage mediumImage;
	private java.lang.String mediumUrl;
	private java.util.Collection notes;
	// Deprecated
	private java.io.InputStream originalAsStream;
	private java.lang.String originalFormat;
	// Deprecated
	private java.awt.image.BufferedImage originalImage;
	private java.lang.String originalSecret;
	private java.lang.String originalUrl;
	private User owner;
	private Permissions permissions;
	private int rotation;
	private java.lang.String secret;
	private java.lang.String server;
	// Deprecated
	// private java.io.InputStream SmallAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallImage;
	// Deprecated
	// private java.io.InputStream SmallSquareAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallSquareImage;
	private java.lang.String smallSquareUrl;
	private java.lang.String smallUrl;
	private java.util.Collection tags;
	private java.lang.String takenGranularity;
	// Deprecated
	// private java.io.InputStream ThumbnailAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage ThumbnailImage;
	private java.lang.String thumbnailUrl;
	private java.lang.String title;
	private java.lang.String url;
	private java.util.Collection urls;
	private int views;
	private boolean hasGeoData;
	private boolean isFamilyFlag;
	private boolean isFavorite;
	private boolean isFriendFlag;
	private boolean isPrimary;
	private boolean isPublicFlag;

	public Photos(Photo p) {
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
	}

	public int getComments() {
		return comments;
	}

	public java.util.Date getDateAdded() {
		return dateAdded;
	}

	public java.util.Date getDatePosted() {
		return datePosted;
	}

	public java.util.Date getDateTaken() {
		return dateTaken;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public Editability getEditability() {
		return editability;
	}

	public java.lang.String getFarm() {
		return farm;
	}

	public GeoData getGeoData() {
		return geoData;
	}

	public java.lang.String getIconFarm() {
		return iconFarm;
	}

	public java.lang.String getIconServer() {
		return iconServer;
	}

	public java.lang.String getId() {
		return id;
	}

	public java.io.InputStream getLargeAsStream() {
		return largeAsStream;
	}

	public java.awt.image.BufferedImage getLargeImage() {
		return largeImage;
	}

	public java.lang.String getLargeUrl() {
		return largeUrl;
	}

	public java.util.Date getLastUpdate() {
		return lastUpdate;
	}

	public java.lang.String getLicense() {
		return license;
	}

	public java.io.InputStream getMediumAsStream() {
		return mediumAsStream;
	}

	public java.awt.image.BufferedImage getMediumImage() {
		return mediumImage;
	}

	public java.lang.String getMediumUrl() {
		return mediumUrl;
	}

	public java.util.Collection getNotes() {
		return notes;
	}

	public java.io.InputStream getOriginalAsStream() {
		return originalAsStream;
	}

	public java.lang.String getOriginalFormat() {
		return originalFormat;
	}

	public java.awt.image.BufferedImage getOriginalImage() {
		return originalImage;
	}

	public java.lang.String getOriginalSecret() {
		return originalSecret;
	}

	public java.lang.String getOriginalUrl() {
		return originalUrl;
	}

	public User getOwner() {
		return owner;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public int getRotation() {
		return rotation;
	}

	public java.lang.String getSecret() {
		return secret;
	}

	public java.lang.String getServer() {
		return server;
	}

	public java.lang.String getSmallSquareUrl() {
		return smallSquareUrl;
	}

	public java.lang.String getSmallUrl() {
		return smallUrl;
	}

	public java.util.Collection getTags() {
		return tags;
	}

	public java.lang.String getTakenGranularity() {
		return takenGranularity;
	}

	public java.lang.String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public java.util.Collection getUrls() {
		return urls;
	}

	public int getViews() {
		return views;
	}

	public boolean isHasGeoData() {
		return hasGeoData;
	}

	public boolean isFamilyFlag() {
		return isFamilyFlag;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public boolean isFriendFlag() {
		return isFriendFlag;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public boolean isPublicFlag() {
		return isPublicFlag;
	}

}
