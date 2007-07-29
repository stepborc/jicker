package org.jicker.util.filebrowser;

import java.io.File;
import java.util.List;

public class DirBrowserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("e:/bilder");
		DirBrowser db = new DirBrowser();
		List<File> ergebnis = db.find(file);
		System.out.println(ergebnis);

	}

}
