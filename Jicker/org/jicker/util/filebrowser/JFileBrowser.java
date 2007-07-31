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

	private List<File> fileList = new ArrayList<File>();

	private FileFilter filter = new FileFilter() {
		final Pattern p = Pattern.compile( "(.*\\.gif$)|(.*\\.jpg$)|(.*\\.ico$)", Pattern.CASE_INSENSITIVE );
		public boolean accept(File file) {
			if (file.isDirectory() | p.matcher(file.getName()).matches())
				return true;
			return false;
		}
	};

	public List<File> dirWalk(File start) {
		File[] files = start.listFiles(filter);
		if (!(files == null || files.length < 1)) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					dirWalk(files[i]);
				} else {
					fileList.add(files[i]);
				}
			}
		} //else {
			//Was ist zu tun, wenn ich hier lande?
			//Dann sollte das letzte Verzeichnis leer gewesen sein!
			//System.out.println("Leeres Verzeichnis");
		//}
		return fileList;
	}
}