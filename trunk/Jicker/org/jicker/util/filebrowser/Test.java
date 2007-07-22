package org.jicker.util.filebrowser;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//BrowseDir dir;
//dir = new BrowseDir("c:/");

// Use the filters to construct the walker
BrowseDir walker = new BrowseDir(
  HiddenFileFilter.VISIBLE,
  FileFilterUtils.suffixFileFilter(".txt") );
System.out.println(walker.toString());

	}

}
