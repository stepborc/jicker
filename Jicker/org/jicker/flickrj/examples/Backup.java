package org.jicker.flickrj.examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import com.aetrion.flickr.photos.Size;
import com.aetrion.flickr.photosets.Photoset;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.util.AuthStore;
import com.aetrion.flickr.util.FileAuthStore;

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

    private String nsid = null;
    private Flickr flickr = null;
    private AuthStore authStore = null;
    private String sharedSecret = null;

    public Backup(String apiKey, String nsid, String sharedSecret, File authsDir) throws IOException {
        this.flickr = new Flickr(apiKey);
        this.sharedSecret = sharedSecret;
        this.nsid = nsid;

        if (authsDir != null) {
            this.authStore = new FileAuthStore(authsDir);
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
		
		
		PhotosetsInterface pi = flickr.getPhotosetsInterface();
		PhotosInterface photoInt = flickr.getPhotosInterface();
		Map allPhotos = new HashMap();
		
		Iterator sets=null;
		try {
			sets = pi.getList(this.nsid).getPhotosets().iterator();
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
		
		while (sets.hasNext()) {
			Photoset set = (Photoset)sets.next();
			PhotoList photos=null;
			try {
				photos = pi.getPhotos(set.getId(), 500, 1);
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
			allPhotos.put(set.getTitle(), photos);
		}
		
		int notInSetPage = 1;
		Collection notInASet = new ArrayList();
		while (true) {
			Collection nis=null;
			try {
				nis = photoInt.getNotInSet(50, notInSetPage);
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
			notInASet.addAll(nis);
			if (nis.size() < 50) break;
			notInSetPage++;
		}
		allPhotos.put("NotInASet", notInASet);
		
		
		
		Iterator allIter = allPhotos.keySet().iterator();
		
		
		while (allIter.hasNext()) {
			String setTitle = makeSafeFilename((String)allIter.next());
			
			Collection currentSet = (Collection)allPhotos.get(setTitle);
			Iterator setIterator = currentSet.iterator();
			File setDirectory = new File(directory, setTitle);
			setDirectory.mkdir();
			while (setIterator.hasNext()) {
			
				Photo p = (Photo)setIterator.next();
				String url=null;
				try {
					url = p.getOriginalUrl();
				} catch (FlickrException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				URL u=null;
				try {
					u = new URL(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String filename = u.getFile();
				filename = filename.substring(filename.lastIndexOf("/") + 1 , filename.length());
				//filename = filename.substring(1);
				try {
					System.out.println("Now writing " + filename + " to " + setDirectory.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
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

	private String makeSafeFilename(String input) {
		input.replace("/", "_");
		input.replace("\\", "_");
		/*byte[] fname = input.getBytes();
		byte[] bad = new byte[]{'\\', '/'};
		byte replace = '_';
		for (int i = 0; i < fname.length; i++) {
			for (int j = 0; j < bad.length; j++) {
				if (fname[i] == bad[j]) fname[i] = replace;
			}
		}*/
		return new String(input);
	}
	
	
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
