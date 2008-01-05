package org.jicker.flickrj.db4o;

import com.aetrion.flickr.photos.Editability;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Permissions;

public class Photo {

	private int Comments;
	private java.util.Date DateAdded;
	private java.util.Date DatePosted;
	private java.util.Date DateTaken;
	private java.lang.String Description;
	private Editability Editability;
	private java.lang.String Farm;
	private GeoData GeoData;
	private java.lang.String IconFarm;
	private java.lang.String IconServer;
	private java.lang.String Id;
	// Deprecated
	private java.io.InputStream LargeAsStream;
	// Deprecated
	private java.awt.image.BufferedImage LargeImage;
	private java.lang.String LargeUrl;
	private java.util.Date LastUpdate;
	private java.lang.String License;
	// Deprecated
	private java.io.InputStream MediumAsStream;
	// Deprecated.
	private java.awt.image.BufferedImage MediumImage;
	private java.lang.String MediumUrl;
	private java.util.Collection Notes;
	// Deprecated
	private java.io.InputStream OriginalAsStream;
	private java.lang.String OriginalFormat;
	// Deprecated
	private java.awt.image.BufferedImage OriginalImage;
	private java.lang.String OriginalSecret;
	private java.lang.String OriginalUrl;
	private User Owner;
	private Permissions Permissions;
	private int Rotation;
	private java.lang.String Secret;
	private java.lang.String Server;
	// Deprecated
	// private java.io.InputStream SmallAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallImage;
	// Deprecated
	// private java.io.InputStream SmallSquareAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage SmallSquareImage;
	private java.lang.String SmallSquareUrl;
	private java.lang.String SmallUrl;
	private java.util.Collection Tags;
	private java.lang.String TakenGranularity;
	// Deprecated
	// private java.io.InputStream ThumbnailAsInputStream;
	// Deprecated
	// private java.awt.image.BufferedImage ThumbnailImage;
	private java.lang.String ThumbnailUrl;
	private java.lang.String Title;
	private java.lang.String Url;
	private java.util.Collection Urls;
	private int Views;
	private boolean hasGeoData;
	private boolean isFamilyFlag;
	private boolean isFavorite;
	private boolean isFriendFlag;
	private boolean isPrimary;
	private boolean isPublicFlag;

	public Photo(Photo p) {
		this.Comments = p.getComments();
		this.DateAdded = p.getDateAdded();
		this.DatePosted = p.getDatePosted();
		this.DateTaken = p.getDateTaken();
		this.Description = p.getDescription();

	}

	public int getComments() {
		return Comments;
	}

	public java.util.Date getDateAdded() {
		return DateAdded;
	}

	public java.util.Date getDatePosted() {
		return DatePosted;
	}

	public java.util.Date getDateTaken() {
		return DateTaken;
	}

	public java.lang.String getDescription() {
		return Description;
	}

	public Editability getEditability() {
		return Editability;
	}

	public java.lang.String getFarm() {
		return Farm;
	}

	public GeoData getGeoData() {
		return GeoData;
	}

	public java.lang.String getIconFarm() {
		return IconFarm;
	}

	public java.lang.String getIconServer() {
		return IconServer;
	}

	public java.lang.String getId() {
		return Id;
	}

	public java.io.InputStream getLargeAsStream() {
		return LargeAsStream;
	}

	public java.awt.image.BufferedImage getLargeImage() {
		return LargeImage;
	}

	public java.lang.String getLargeUrl() {
		return LargeUrl;
	}

	public java.util.Date getLastUpdate() {
		return LastUpdate;
	}

	public java.lang.String getLicense() {
		return License;
	}

	public java.io.InputStream getMediumAsStream() {
		return MediumAsStream;
	}

	public java.awt.image.BufferedImage getMediumImage() {
		return MediumImage;
	}

	public java.lang.String getMediumUrl() {
		return MediumUrl;
	}

	public java.util.Collection getNotes() {
		return Notes;
	}

	public java.io.InputStream getOriginalAsStream() {
		return OriginalAsStream;
	}

	public java.lang.String getOriginalFormat() {
		return OriginalFormat;
	}

	public java.awt.image.BufferedImage getOriginalImage() {
		return OriginalImage;
	}

	public java.lang.String getOriginalSecret() {
		return OriginalSecret;
	}

	public java.lang.String getOriginalUrl() {
		return OriginalUrl;
	}

	public User getOwner() {
		return Owner;
	}

	public Permissions getPermissions() {
		return Permissions;
	}

	public int getRotation() {
		return Rotation;
	}

	public java.lang.String getSecret() {
		return Secret;
	}

	public java.lang.String getServer() {
		return Server;
	}

	public java.lang.String getSmallSquareUrl() {
		return SmallSquareUrl;
	}

	public java.lang.String getSmallUrl() {
		return SmallUrl;
	}

	public java.util.Collection getTags() {
		return Tags;
	}

	public java.lang.String getTakenGranularity() {
		return TakenGranularity;
	}

	public java.lang.String getThumbnailUrl() {
		return ThumbnailUrl;
	}

	public java.lang.String getTitle() {
		return Title;
	}

	public java.lang.String getUrl() {
		return Url;
	}

	public java.util.Collection getUrls() {
		return Urls;
	}

	public int getViews() {
		return Views;
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
