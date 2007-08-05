package org.jicker.util.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class JFileBrowserFilter implements FileFilter {
	public String filterValue;
	final Pattern p = Pattern.compile(filterValue, Pattern.CASE_INSENSITIVE);
	public boolean accept(File file) {
		if (file.isDirectory() | p.matcher(file.getName()).matches())
			return true;
		return false;
		
	}

}
