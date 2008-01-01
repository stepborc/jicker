package org.jicker.flickrj.examples;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.util.FileAuthStore;

public class FBackup {

	private static String sharedSecret;
	private static String backupDir;
	private static String nsid;
	private static String apiKey;
	private Flickr flickr = null;
	private FileAuthStore authStore;
	private Object authsDir = new File(System.getProperty("user.home")
			+ File.separatorChar + ".flickrAuth");

	public FBackup() throws IOException, SAXException, FlickrException {
		this.flickr = new Flickr(apiKey);
		if (this.authsDir != null) {
			try {
				this.authStore = new FileAuthStore(new File(System
						.getProperty("user.home")
						+ File.separatorChar + ".flickrAuth"));
				System.out.print("Verzeichnis ");
				System.out.print(new File(System.getProperty("user.home")
						+ File.separatorChar + ".flickrAuth"));
				System.out.println(" wird angelegt!");
			} catch (IOException e) {
				System.out.println("Userhome konnte nicht ermittelt werden.");
				e.printStackTrace();
			}
		}
		RequestContext rc = RequestContext.getRequestContext();
		rc.setSharedSecret(this.sharedSecret);

		if (this.authStore != null) {
			Auth auth = this.authStore.retrieve(this.nsid);
			if (auth == null)
				try {
					this.authorize();
				} catch (IOException e) {
					System.out.println("Fehler beim Lesen oder Schreiben.");
					e.printStackTrace();
				} catch (SAXException e) {
					System.out
							.println("Fehler beim verarbeiten der XML-Daten.");
					e.printStackTrace();
				} catch (FlickrException e) {
					System.out.println("Flickr Fehler.");
					e.printStackTrace();
				}
			else
				rc.setAuth(auth);
		}

		// Photosets lesen
		PhotosetsInterface pi = flickr.getPhotosetsInterface();
		//Iterator über Photoset bilden
		Iterator sets = pi.getList(this.nsid).getPhotosets().iterator();
		int n = 1;
		while (sets.hasNext()) {
			//aktuelles Objekt der Photosets Liste einem Photoset zuordnen
			Photoset set = (Photoset) sets.next();
			//Namen des Photosets ausgeben
			System.out.print(n + ". " + set.getTitle().toString());
			int countPhotos = pi.getInfo(set.getId()).getPhotoCount();
			System.out.println("\t" + countPhotos);
			Iterator photos = pi.getPhotos(set.getId(), countPhotos, 1).iterator();
			while (photos.hasNext()){
				Photo photo = (Photo) photos.next();
				System.out.println("\t" +photo.getId());
			}
			n++;
		}
		System.out.println("Setliste komplett");
	}

	private void authorize() throws IOException, SAXException, FlickrException {
		String frob = this.flickr.getAuthInterface().getFrob();

		URL authUrl = this.flickr.getAuthInterface().buildAuthenticationUrl(
				Permission.READ, frob);
		System.out.println("Öffne: " + authUrl.toExternalForm()
				+ " und bestätige mit ENTER.");

		System.in.read();

		Auth token = this.flickr.getAuthInterface().getToken(frob);
		RequestContext.getRequestContext().setAuth(token);
		this.authStore.store(token);
		System.out
				.println("Thanks.  You probably will not have to do this every time.  Now starting backup.");
	}

	/**
	 * @param args
	 * @throws FlickrException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, SAXException,
			FlickrException {

		apiKey = "6fe409e0413a2a5e03d54c30ca6a27c4";
		nsid = "37931219@N00";
		sharedSecret = "5c570508bbc86e00";
		backupDir = "c:/tmp/flickrj/";
		FBackup bf = new FBackup();

	}

}
