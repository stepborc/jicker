package org.jicker.util;

import java.io.File;

public class TestOpenBrowser {

	/**
	 * @param <OpenBrowse>
	 * @param args
	 */
	public static void main(String[] args) {
		//OpenBrowser t = new OpenBrowser(new File("C:\\thread.jspa.htm"));
		OpenBrowser t = new OpenBrowser("http://www.flickr.com:80/services/auth?api_key=6fe409e0413a2a5e03d54c30ca6a27c4&frob=72157603781017414-bc1c84152ca76055-310345&perms=read&api_sig=44abae89be9fe6079e06d6c2bcd712cd");
		

	}

}
