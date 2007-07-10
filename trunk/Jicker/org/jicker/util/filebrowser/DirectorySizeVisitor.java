package org.jicker.util.filebrowser;

import java.io.*;

public class DirectorySizeVisitor implements DirectoryVisitor {
	int files = 0;

	int dirs = 0;

	long size = 0;

	public void enterDirectory(File dir) {
		++dirs;

	}

	public void leaveDirectory(File dir) {
	}

	public void visitFile(File file) {
		++files;
		size += file.length();

	}

	public int getDirs() {
		return dirs;
	}

	public int getFiles() {
		return files;
	}

	public long getSize() {
		return size;
	}

}
