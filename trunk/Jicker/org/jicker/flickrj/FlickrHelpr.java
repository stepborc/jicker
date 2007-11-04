/*
    Copyright 2005 Abram Catalano

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA


 This program uses two external libraries, flickrj and ostermiller.org utils.
 See LICENSE.flickrj, and LICENSE.ostermiller for their licenses.


 */

package org.jicker.flickrj;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Collection;

import com.Ostermiller.util.Browser;
import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;


/**
 * @author Abram Catalano
 *
 */
public class FlickrHelpr {

	//private static String title; //bla

	private static String description = "";

	private static Collection tags;

	private static boolean publicFlag = false;

	private static boolean friendFlag = true;

	private static boolean familyFlag = true;

	final static int EXIT_SUCCESS = 0;

	private static String sharedSecret = "5c570508bbc86e00";
	private static String apiKey = "6fe409e0413a2a5e03d54c30ca6a27c4";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// iterate over each arg (file to upload) and upload.
		// if cant get file, throw usage message

		System.out.println("Starting...");
		RequestContext rc = RequestContext.getRequestContext();

		rc.setSharedSecret(sharedSecret);
		Flickr flickr = new Flickr(apiKey);
		AuthInterface auth = flickr.getAuthInterface();
		String frob = null;

		try{
		frob = auth.getFrob();
		}catch(Exception e){
			System.err.println("Usage: flickrhelpr [options] FILE...  \nError - While in getFrob()"
					+ e.getClass().toString() + " " + e.getMessage());
		}
		URL authUrl = null;

		try{
		authUrl = auth.buildAuthenticationUrl(Permission.WRITE, frob);
		}catch(Exception e){
			System.err.println("Usage: flickrhelpr [options] FILE...  \nError - While buildAuthURL"
					+ e.getClass().toString() + " " + e.getMessage());
		}
		System.out.println("\n\nYou will now have to login to flickr via the web browser.\n\nAttempting to open browser...\n");
		Browser.init();
		try{
		Browser.displayURL(authUrl.toString());
		}catch(Exception e){
			System.err.println("Failed to open browser, please browse to the following URL: \n"+authUrl.toString());
		}
		System.out.println("Press [Enter] once you have logged in to begin upload");
		try{
		System.in.read();
		}catch(Exception e){
			System.err.println("Usage: flickrhelpr [options] FILE...  Error - While paused"
					+ e.getClass().toString() + " " + e.getMessage());
		}

		Auth token = null;
		try{
		token = auth.getToken(frob);
		}catch(Exception e){
			System.err.println("Usage: flickrhelpr [options] FILE...  Error in getToken"
					+ e.getClass().toString() + " " + e.getMessage());
		}

		rc.setAuth(token);

		for (int i = 0; i < args.length; i++) {
			System.out.println("Processing image #"+i);

			File file;
			FileInputStream fis = null;

			file = new File(args[i]);

			try {
				fis = new FileInputStream(file);
			} catch (Exception e) {
				System.err.println("Usage: flickrhelpr [options] FILE... Error - "
						+ e.getClass().toString() + " " + e.getMessage());
			}

			UploadMetaData umeta = new UploadMetaData();
			umeta.setDescription(description);
			umeta.setFamilyFlag(familyFlag);
			umeta.setFriendFlag(friendFlag);
			umeta.setPublicFlag(publicFlag);
			umeta.setTags(tags);
			umeta.setTitle(file.getName());

			Uploader upldr = new Uploader(apiKey);
			try {
				upldr.upload(fis, umeta);
				System.out.println("Uploading "+file.getName()+"...");
			} catch (Exception e) {
				System.out.println("Error on upload: "
						+ e.getClass().toString() + e.getMessage());
				break;
			}

		}
		System.out.println("Exiting.");
		System.exit(EXIT_SUCCESS);
	}
}

