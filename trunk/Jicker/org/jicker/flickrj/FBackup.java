package org.jicker.flickrj;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.jicker.flickrj.db4o.Sets;
import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.util.FileAuthStore;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

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

		// Zu Testzwecken jedesmal eine neue Datenbank anlegen
		new File("flickrDb.db4o").delete();
		ObjectContainer db = Db4o.openFile("flickrDb.db4o");
		//Kommentar schreiben
		PhotosetsInterface pi = flickr.getPhotosetsInterface();
		// Setsliste ermitteln und speichern
		SetBrowse sb = new SetBrowse(pi, flickr, db, nsid);
		// Fotos pro Set ermitteln
		PhotoBrowse pb = new PhotoBrowse(pi, flickr, db, nsid);
		// Zu Testzwecken Setliste ausgeben
/*		ObjectSet<Sets> setlist = db.get(Sets.class);
		// setlist.get(1).getTitle();
		System.out.println(setlist.size() + " Sets");
		Sets s = null;
		while (setlist.hasNext()) {
			s = setlist.next();
			System.out.println(s.getTitle() + " " + s.getId());
		}
		System.out.println("---");
*/
		//Fotos der Sets ermitteln
		
		PhotosInterface pin = flickr.getPhotosInterface();
		Iterator<Photo> nis = pin.getNotInSet(10000, 1).iterator();
		System.out.println("Fotos die in keinem Set sind.");
		while (nis.hasNext()) {
			Photo nisPhoto = (Photo) nis.next();
			System.out.println("\t" + nisPhoto.getId());
		}
		System.out.println("Setliste komplett");
		db.close();
	}

	private void authorize() throws IOException, SAXException, FlickrException {
		// Einen frob anfordern
		String frob = this.flickr.getAuthInterface().getFrob();
		// Aus frob und angeforderten Rechten die Authentification Url
		// generieren
		URL authUrl = this.flickr.getAuthInterface().buildAuthenticationUrl(
				Permission.READ, frob);
		// Die generierte Url ausgeben
		System.out.println("Öffne: " + authUrl.toExternalForm()
				+ " und bestätige mit ENTER.");
		// Auf Eingabe warten
		System.in.read();
		// Nach der Eingabe die Authentifizierung überprüfen
		Auth token = this.flickr.getAuthInterface().getToken(frob);
		RequestContext.getRequestContext().setAuth(token);
		// Den token in das AuthStor im Userhome schreiben
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
