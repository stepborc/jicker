package org.jicker.flickrj.examples;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.util.FileAuthStore;

public class FBackup {

	private static String sharedSecret;
	private static String backupDir;
	private static String nsid;
	private static String apiKey;
	private Flickr flickr = null;
	private FileAuthStore authStore;
	private Object authsDir = new File(System.getProperty("user.home") + File.separatorChar + ".flickrAuth");

	public FBackup() {
		this.flickr = new Flickr(apiKey);
		if (this.authsDir != null) {
			try {
				this.authStore = new FileAuthStore(new File(System.getProperty("user.home") + File.separatorChar + ".flickrAuth"));
				System.out.print("Verzeichnis ");
				System.out.print(new File(System.getProperty("user.home") + File.separatorChar + ".flickrAuth"));
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FlickrException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else rc.setAuth(auth);
		}
	}
	private void authorize() throws IOException, SAXException, FlickrException {
		String frob = this.flickr.getAuthInterface().getFrob();
		
		URL authUrl = this.flickr.getAuthInterface().buildAuthenticationUrl(Permission.READ, frob);
		System.out.println("Please visit: " + authUrl.toExternalForm() + " then, hit enter.");
				
		System.in.read();

		Auth token = this.flickr.getAuthInterface().getToken(frob);
		RequestContext.getRequestContext().setAuth(token);
		this.authStore.store(token);
		System.out.println("Thanks.  You probably will not have to do this every time.  Now starting backup.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		apiKey = "6fe409e0413a2a5e03d54c30ca6a27c4";
		nsid = "37931219@N00";
		sharedSecret = "5c570508bbc86e00";
		backupDir = "c:/tmp/flickrj/";
		FBackup bf = new FBackup();

	}

}
