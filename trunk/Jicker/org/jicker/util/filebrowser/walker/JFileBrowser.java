package org.jicker.util.filebrowser.walker;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Class JFileBrowser. Ehemals JDirectoryScanner
 */
public class JFileBrowser {
	//JFileBrowserFilter f = new JFileBrowserFilter();
	//private List<File> fileList = new ArrayList<File>();
	public String filterValueCopy;
	final Pattern p;
	
	public JFileBrowser(String extension) {
		p = Pattern
		//		.compile("(.*\\.gif$)|(.*\\.jpg$)",
		//				Pattern.CASE_INSENSITIVE);
		.compile(extension, Pattern.CASE_INSENSITIVE);

	}
	
	private FileFilter filter = new FileFilter() {
		//final Pattern p = Pattern
		//		.compile("(.*\\.gif$)|(.*\\.jpg$)",
		//				Pattern.CASE_INSENSITIVE);
		//.compile(filterValueCopy, Pattern.CASE_INSENSITIVE);

		public boolean accept(File file) {
			
			if (file.isDirectory() | p.matcher(file.getName()).matches())
				return true;
			return false;
		}
	};

	
	public List<File> dirWalk(File start) {
		List<File> fileList = new ArrayList<File>();
		//JFileBrowserFilter filterTest = new JFileBrowserFilter("(.*\\.gif$)|(.*\\.jpg$)");
		BilderEndung filterTest = new BilderEndung();
		//File[] files = start.listFiles(filter);
		File[] files = start.listFiles(filterTest.accept(start, ""));
		if (!(files == null || files.length < 1)) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					dirWalk(files[i]);
				} else {
					fileList.add(files[i]);
				}
			}
		}
		return fileList;
	}
}