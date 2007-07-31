package org.jicker.util.filebrowser;

import java.io.File;

public class JFileBrowserTest extends JFileBrowser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "d:/Eigene Bilder";
		File root = new File(name);
		JFileBrowser fileList = new JFileBrowser();
		;
		System.out.println(fileList.dirWalk(root));

	}

}
