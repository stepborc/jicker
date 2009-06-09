package org.jicker.viralpetal.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class HttpRequest2 {

	public static void main(String[] args) throws IOException {
		System.setProperty("proxySet", "true");
		System.setProperty("proxyHost", "164.23.111.211");
		System.setProperty("proxyPort", "8080");
		try {
			// URL my_url = new URL("http://www.viralpatel.net/blogs/");
			URL url = new URL(
					"https://milesplus.t-systems.com/prod/plsql/plogin.login");
			URLConnection con = url.openConnection();
			System.out.println(con);
			System.out.println("Datum:\t" + new Date(con.getDate()));
/*			try {
				InputStream is = url.openStream();
				// System.out.println(new
				// Scanner(is).useDelimiter("\\Z").next());
				// OutputStream os = new FileOutputStream("test.html");
				// int i;
				// while ((i = is.read()) > -1) {
				// os.write(i);
				// }
			} catch (IOException e) {
				e.printStackTrace();
			}*/

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}
}
