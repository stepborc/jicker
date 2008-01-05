package org.jicker.flickrj;

import java.io.IOException;
import java.util.Iterator;

import org.jicker.flickrj.db4o.Sets;
import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.db4o.ObjectContainer;

public class SetBrowse {

	public SetBrowse(Flickr flickr, ObjectContainer db, String nsid) throws IOException, SAXException, FlickrException{
	// Photosets lesen
	PhotosetsInterface pi = flickr.getPhotosetsInterface();
	//Iterator über Photoset bilden
	//nsid gegen eine andere austauschen
	Iterator sets = pi.getList(nsid).getPhotosets().iterator();
	//Iterator sets = pi.getList("14267014@N03").getPhotosets().iterator();
	//int n = 1;
	Sets s = null;
	while (sets.hasNext()) {
		//aktuelles Objekt der Photosets Liste einem Photoset zuordnen
		Photoset set = (Photoset) sets.next();
		//Namen des Photosets ausgeben
		//System.out.println(set.getTitle().toString());
		s = new Sets(set);
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
