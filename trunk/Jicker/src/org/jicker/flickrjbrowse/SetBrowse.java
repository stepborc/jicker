/*
 * 
 */
package org.jicker.flickrjbrowse;

import java.io.IOException;
import java.util.Iterator;

import org.jicker.flickrj.db4o.DbSets;
import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.db4o.ObjectContainer;

// TODO: Auto-generated Javadoc
/**
 * The Class SetBrowse.
 */
public class SetBrowse {

	/**
	 * Instantiates a new sets the browse.
	 * 
	 * @param pi the pi
	 * @param flickr the flickr
	 * @param db the db
	 * @param nsid the nsid
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SAXException the SAX exception
	 * @throws FlickrException the flickr exception
	 */
	public SetBrowse(PhotosetsInterface pi, Flickr flickr, ObjectContainer db, String nsid) throws IOException, SAXException, FlickrException{
	// Photosets lesen
	//PhotosetsInterface pi = flickr.getPhotosetsInterface();
	//Iterator über Photoset bilden
	//nsid gegen eine andere austauschen
	Iterator sets = pi.getList(nsid).getPhotosets().iterator();
	//Iterator sets = pi.getList("14267014@N03").getPhotosets().iterator();
	//int n = 1;
	DbSets s = null;
	while (sets.hasNext()) {
		//aktuelles Objekt der Photosets Liste einem Photoset zuordnen
		Photoset set = (Photoset) sets.next();
		//Namen des Photosets ausgeben
		//System.out.println(set.getTitle().toString());
		s = new DbSets(set);
		db.set(s);
/*		int countPhotos = pi.getInfo(set.getId()).getPhotoCount();
		System.out.println("\t" + countPhotos);
		Iterator photos = pi.getPhotos(set.getId(), countPhotos, 1).iterator();
		while (photos.hasNext()){
			Photo photo = (Photo) photos.next();
			System.out.println("\t" +photo.getId());
		}
*/		//n++;
	}

	}
}
