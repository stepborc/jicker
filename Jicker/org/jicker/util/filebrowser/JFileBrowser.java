package org.jicker.util.filebrowser;

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
	private List<File> fileList = new ArrayList<File>();
	private String filterValueCopy; 
	public JFileBrowser(){
		filterValueCopy = "(.*\\.gif$)|(.*\\.jpg$)";	
	}
	public JFileBrowser(String filterValue){
		//filterValueCopy = filterValue;
		filterValueCopy = "(.*\\.gif$)|(.*\\.jpg$)";
	}

//	public void setFilterValue(String value){
//		filterValue = value;
//	}
	
	private FileFilter filter = new FileFilter() {
		final Pattern p = Pattern
				.compile("(.*\\.gif$)|(.*\\.jpg$)",
						Pattern.CASE_INSENSITIVE);
		//.compile(filterValueCopy, Pattern.CASE_INSENSITIVE);

		public boolean accept(File file) {
			
			if (file.isDirectory() | p.matcher(file.getName()).matches())
				return true;
			return false;
		}
	};
//	private FileFilter filterAlt = new FileFilter(){
//		
//		public boolean accept(File file){
//			if (file.isDirectory() | filterValue.matches(file.getName()))
//				return true;
//			return false;
//		}
//	};

	
	
	public List<File> dirWalk(File start, FileFilter filter) {
		//JFileBrowserFilter filterTest = new JFileBrowserFilter("(.*\\.gif$)|(.*\\.jpg$)");
		File[] files = start.listFiles(filter);
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