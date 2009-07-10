package org.jicker.viralpetal.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FileDirectory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File dir = new File("e:");
		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
			}
		}
		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i]);
		}
		// It is also possible to filter the list of returned files.
		// This example does not return any files that start with `.'.
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};
		children = dir.list(filter);
		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i]);
		}
		// The list of files can also be retrieved as File objects
		File[] files = dir.listFiles();

		// This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		files = dir.listFiles(fileFilter);
		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i]);
		}
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
		}
	}

}
