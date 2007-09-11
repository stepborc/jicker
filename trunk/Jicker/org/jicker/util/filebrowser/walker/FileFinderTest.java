package org.jicker.util.filebrowser.walker;

import java.io.File;
import java.util.List;

public class FileFinderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileFinder ff = new FileFinder();
		//String startpath = new File( "e:/bilder/s45-bilder" ).getPath();
		String startpath = new File( "z:/cd" ).getPath();
		String extensionPattern = "(.*\\.gif$)|(.*\\.jpg$)";
		List<File> files = ff.find(startpath, extensionPattern);
		System.out.println(files);
		System.out.println(files.size());
	}

}
