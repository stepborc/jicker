/*
 * 
 */
package org.jicker.flickrj.examples;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.util.IOUtilities;

// TODO: Auto-generated Javadoc
/**
 * Demonstrates the authentication-process.<p>
 * 
 * If you registered API keys, you find them with the shared secret at your
 * <a href="http://www.flickr.com/services/api/registered_keys.gne">list of API keys</a>
 * 
 * @author mago
 * @version $Id: AuthExample.java,v 1.4 2007/12/01 00:05:34 x-mago Exp $
 */
public class AuthExample {
    
    /** The f. */
    Flickr f;
    
    /** The request context. */
    RequestContext requestContext;
    
    /** The frob. */
    String frob = "";
    
    /** The token. */
    String token = "";
    
    /** The properties. */
    Properties properties = null;

    /**
     * Instantiates a new auth example.
     * 
     * @throws ParserConfigurationException the parser configuration exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws SAXException the SAX exception
     */
    public AuthExample() throws ParserConfigurationException, IOException, SAXException {
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream("" + "setup.properties");
            properties = new Properties();
            properties.load(in);
        } finally {
            IOUtilities.close(in);
        }
        f = new Flickr(properties.getProperty("apiKey"), new REST());
        Flickr.debugStream = false;
        // Set the shared secret which is used for any calls which require signing.
        requestContext = RequestContext.getRequestContext();
        requestContext.setSharedSecret(properties.getProperty("secret"));
        AuthInterface authInterface = f.getAuthInterface();
        try {
            frob = authInterface.getFrob();
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        System.out.println("frob: " + frob);
        URL url = authInterface.buildAuthenticationUrl(Permission.WRITE, frob);
        
        System.out.println("Press return after you granted access at this URL:");
        System.out.println(url.toExternalForm());
        //2009-01-30: Zeile nicht in Verwendung
        //            Sinn Und Zweck unklar
        /*
         * BufferedReader infile = new BufferedReader ( new InputStreamReader (System.in) );
         * String line = infile.readLine();
         */
        try {
            Auth auth = authInterface.getToken(frob);
            System.out.println("Authentication success");
            // This token can be used until the user revokes it.
            System.out.println("Token: " + auth.getToken());
            System.out.println("nsid: " + auth.getUser().getId());
            System.out.println("Realname: " + auth.getUser().getRealName());
            System.out.println("Username: " + auth.getUser().getUsername());
            System.out.println("Permission: " + auth.getPermission().getType());
        } catch (FlickrException e) {
            System.out.println("Authentication failed");
            e.printStackTrace();
        }
    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(String[] args) {
        try {
            //AuthExample t = new AuthExample();
        	new AuthExample();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
