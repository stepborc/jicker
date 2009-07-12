/*
 *
 */
package org.jicker.flickrj.examples;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.util.AuthStore;
import com.aetrion.flickr.util.FileAuthStore;

// TODO: Auto-generated Javadoc
/**
 * A simple program to backup all of a users private and public photos in a photoset aware manner.  If photos
 * are classified in multiple photosets, they will be copied.  Its a sample, its not perfect :-)
 *
 * This sample also uses the AuthStore interface, so users will only be asked to authorize on the first run.
 *
 * @author Matthew MacKenzie
 * @version $Id: Backup.java,v 1.2 2007/12/09 12:56:20 x-mago Exp $
 */

public class Backup {

    /** The nsid. */
    private String nsid = null;

    /** The flickr. */
    private Flickr flickr = null;

    /** The auth store. */
    private AuthStore authStore = null;

    /** The shared secret. */
    private String sharedSecret = null;

    /**
     * Instantiates a new backup.
     *
     * @param apiKey the api key
     * @param nsid the nsid
     * @param sharedSecret the shared secret
     * @param authsDir the auths dir
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public Backup(String apiKey, String nsid, String sharedSecret, File authsDir) throws IOException {
        this.flickr = new Flickr(apiKey);
        this.sharedSecret = sharedSecret;
        this.nsid = nsid;

        if (authsDir != null) {
            this.authStore = new FileAuthStore(authsDir);
        }
    }

	/**
	 * Authorize.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SAXException the SAX exception
	 * @throws FlickrException the flickr exception
	 */
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
	 * Do backup.
	 *
	 * @param directory the directory
	 */
	public void doBackup(File directory) {
		if (!directory.exists()) directory.mkdir();

		RequestContext rc = RequestContext.getRequestContext();
		rc.setSharedSecret(this.sharedSecret);

		if (this.authStore != null) {
			Auth auth = this.authStore.retrieve(this.nsid);
			if (auth == null)
				try {
					this.authorize();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (FlickrException e) {
					e.printStackTrace();
				}
			else rc.setAuth(auth);
		}


		PhotosetsInterface pi = flickr.getPhotosetsInterface();
		PhotosInterface photoInt = flickr.getPhotosInterface();
		Map allPhotos = new HashMap();

		Iterator sets=null;
		try {
			sets = pi.getList(this.nsid).getPhotosets().iterator();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FlickrException e) {
			e.printStackTrace();
		}

		while (sets.hasNext()) {
			Photoset set = (Photoset)sets.next();
			PhotoList photos=null;
			try {
				photos = pi.getPhotos(set.getId(), 500, 1);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (FlickrException e) {
				e.printStackTrace();
			}
			allPhotos.put(set.getTitle(), photos);
		}

		int notInSetPage = 1;
		Collection notInASet = new ArrayList<Object>();
		while (true) {
			Collection nis=null;
			try {
				nis = photoInt.getNotInSet(50, notInSetPage);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (FlickrException e) {
				e.printStackTrace();
			}
			notInASet.addAll(nis);
			if (nis.size() < 50) break;
			notInSetPage++;
		}
		allPhotos.put("NotInASet", notInASet);



		Iterator allIter = allPhotos.keySet().iterator();


		while (allIter.hasNext()) {
			//String setTitle = makeSafeFilename((String)allIter.next());
			String setTitle = (String)allIter.next();
			String setDirectoryName = setTitle;
			setDirectoryName = makeSafeFilename(setDirectoryName);

			Collection currentSet = (Collection)allPhotos.get(setTitle);
			Iterator setIterator = currentSet.iterator();
			//File setDirectory = new File(directory, setTitle);
			File setDirectory = new File(directory, setDirectoryName);
			setDirectory.mkdir();
			while (setIterator.hasNext()) {

				Photo p = (Photo)setIterator.next();
				String url=null;
				try {
					url = p.getOriginalUrl();
				} catch (FlickrException e) {
					e.printStackTrace();
				}
				URL u=null;
				try {
					u = new URL(url);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				String filename = u.getFile();
				filename = filename.substring(filename.lastIndexOf("/") + 1 , filename.length());
				//filename = filename.substring(1);
				try {
					System.out.println("Now writing " + filename + " to " + setDirectory.getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				/*BufferedInputStream inStream = new BufferedInputStream(photoInt.getImageAsStream(p, Size.ORIGINAL));
				File newFile = new File(setDirectory, filename);

				FileOutputStream fos = new FileOutputStream(newFile);

				int read;

				while ((read = inStream.read()) != -1) {
					fos.write(read);
				}
				fos.flush();
				fos.close();
				inStream.close();*/
			}
		}

	}

	/**
	 * Make safe filename.
	 *
	 * @param input the input
	 *
	 * @return the string
	 */
	private String makeSafeFilename(String input) {
		/*String replacepattern = "\\\\";
		input.replace("\\", "+");
		input.replace("/", "_");
		input.replace(replacepattern , "_");
		*/
		byte[] fname = input.getBytes();
		byte[] bad = new byte[]{'\\', '/'};
		byte replace = '_';
		for (int i = 0; i < fname.length; i++) {
			for (int j = 0; j < bad.length; j++) {
				if (fname[i] == bad[j]) fname[i] = replace;
			}
		}
		return new String(fname);
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 *
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.out.println("Usage: java " + Backup.class.getName() + " api_key nsid shared_secret output_dir");
			System.exit(1);
		}
		Backup bf = new Backup(args[0], args[1], args[2],
				new File(System.getProperty("user.home") + File.separatorChar + ".flickrAuth"));
		bf.doBackup(new File(args[3]));
	}
}