package org.jicker.flickrj.examples;

import java.io.File;
import java.io.IOException;

import com.aetrion.flickr.Flickr;
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
			} catch (IOException e) {
				System.out.println("Userhome konnte nicht ermittelt werden.");
				e.printStackTrace();
			}
		}
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
