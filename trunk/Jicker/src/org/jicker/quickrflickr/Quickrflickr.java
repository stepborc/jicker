/*
 * 
 */
package org.jicker.quickrflickr;

import org.jtb.quickr.AuthService;
import org.jtb.quickr.FrobResponse;
import org.jtb.quickr.QuickrException;

// TODO: Auto-generated Javadoc
/**
 * The Class Quickrflickr.
 */
public class Quickrflickr {

	/**
	 * The main method.
	 * 
	 * @param args the args
	 * 
	 * @throws QuickrException the quickr exception
	 */
	public static void main(String[] args) throws QuickrException {
		AuthService as = new AuthService();
		FrobResponse f = as.getFrob();
		//FrobResponse f = as.getFrob("read");
		//URL u = as.getAuthUrl(f, "read");
	}

}
