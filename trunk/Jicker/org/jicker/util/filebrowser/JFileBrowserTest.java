package org.jicker.util.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JFileBrowserTest extends JFileBrowser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<File> fileResult= new ArrayList();
		String name = "e:/bilder/Alte Bilder";
		File root = new File(name);
		JFileBrowser fileList = new JFileBrowser();
		fileResult = fileList.dirWalk(root);
		System.out.println(fileResult);
		System.out.println(fileResult.size());

	}

}
