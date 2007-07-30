package org.jicker.util.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class JFileBrowser.
 * Ehemals JDirectoryScanner
 */
public class JFileBrowser {

	private File root;
	private List fileList;
	protected boolean stop = false;
	private FileFilter filter = new FileFilter() {

		public boolean accept(File file) {
			if (file.isDirectory())
				return true;

			String name = file.getName();
			if (name.endsWith(".bmp"))
				return true;
			if (name.endsWith(".png"))
				return true;
			return false;
		}
	};

	public JFileBrowser() {
		fileList = new ArrayList();
		root = new File("c:/windows");
		treeWalk(root);
		System.out.println(fileList);
	}

	//frei nach der dclj FAQ (www.dclj.de)
	public final void treeWalk(File root) {

		File[] files = root.listFiles(filter);
		if (files == null || files.length < 1)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				treeWalk(files[i]);
			} else {
				//System.out.println(files[i].getName());
				fileList.add(files[i]);
			}
		}
	}

	public static void main(String[] args) {
		new JFileBrowser();
	}
}