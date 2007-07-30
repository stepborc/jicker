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
	private List<File> fileList;
	protected boolean stop = false;
	private FileFilter filter = new FileFilter() {

		public boolean accept(File file) {
			if (file.isDirectory())
				return true;

			String name = file.getName();
			if (name.endsWith(".jpg"))
				return true;
			if (name.endsWith(".JPG"))
				return true;
			return false;
		}
	};

	public List<File> JFileBrowser(File root) {
		fileList = new ArrayList<File>();
		//root = new File("e:/bilder/s45-bilder/");
		//treeWalk(root);
		//System.out.println(fileList);
		return fileList;
	}

	//frei nach der dclj FAQ (www.dclj.de)
	public List<File> treeWalk(File root) {

		File[] files = root.listFiles(filter);
		if (files == null || files.length < 1)
			return fileList;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				treeWalk(files[i]);
			} else {
				//System.out.println(files[i].getName());
				fileList.add(files[i]);
			}
		}
		return fileList;
	}

	public static void main(String[] args) {
		JFileBrowser files = new JFileBrowser();
		files.treeWalk(new File("e:/bilder/s45-bilder/"));
		
	}
}