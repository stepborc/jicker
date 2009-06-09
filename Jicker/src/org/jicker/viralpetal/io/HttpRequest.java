package org.jicker.viralpetal.io;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpRequest {

		public static void main(String[] args)  {
			System.setProperty("proxySet", "true");
			System.setProperty("proxyHost", "http://www-8e.dienste.telekom.de");
			System.setProperty("proxyPort", "8080");
			//System.setProperty(key, value);
			//System.setProperty(key, value);
			
			//System.getProperties().put("http.proxyHost", "http://www-8e.dienste.telekom.de");
			//System.getProperties().put("http.proxyPort", "8080");
			//System.getProperties().put("http.proxyUser", "");
			//System.getProperties().put("http.proxyPassword", "");
			try {
				URL my_url = new URL("http://www.viralpatel.net/blogs/");
				BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
				String strTemp = "";
				while(null != (strTemp = br.readLine())){
				System.out.println(strTemp);
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
