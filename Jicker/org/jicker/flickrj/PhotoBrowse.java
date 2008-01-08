package org.jicker.flickrj;

import java.io.IOException;
import java.util.Iterator;

import org.jicker.flickrj.db4o.Sets;
import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class PhotoBrowse {
	public PhotoBrowse(PhotosetsInterface pi, Flickr flickr, ObjectContainer db, String nsid ) throws IOException, SAXException, FlickrException{
		ObjectSet<Sets> setlist = db.get(Sets.class);
		// setlist.get(1).getTitle();
		System.out.println(setlist.size() + " Sets");
		Sets s = null;
		org.jicker.flickrj.db4o.Photos p = null;
		while (setlist.hasNext()) {
			s = setlist.next();
			System.out.println(s.getTitle() + " " + s.getId());
			Iterator photos = pi.getPhotos(s.getId(), 1000, 1).iterator();
			while (photos.hasNext()){
				Photo photo = (Photo) photos.next();
				System.out.println("\t" +photo.getId());
				p = new org.jicker.flickrj.db4o.Photos(photo);
				db.set(p);
			}
		}
		System.out.println("--- Ende PhotoBrowse");
	}

}
