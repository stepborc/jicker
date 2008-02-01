package org.jicker.quickrflickr;

import org.jtb.quickr.AuthService;
import org.jtb.quickr.FrobResponse;
import org.jtb.quickr.QuickrException;

public class Quickrflickr {

	/**
	 * @param args
	 * @throws QuickrException 
	 */
	public static void main(String[] args) throws QuickrException {
		AuthService as = new AuthService();
		FrobResponse f = as.getFrob();
		//FrobResponse f = as.getFrob("read");
		//URL u = as.getAuthUrl(f, "read");
	}

}
