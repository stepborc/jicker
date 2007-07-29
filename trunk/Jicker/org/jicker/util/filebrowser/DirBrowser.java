package org.jicker.util.filebrowser;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class DirBrowser {
	public DirBrowser() {
		// TODO Auto-generated constructor stub
	}

	public List<File> find(File file) {
		final List<File> dirs = new ArrayList<File>();
		List<File> files = new ArrayList<File>();
		//final FilenameFilter filter = new BilderEndung();
		final File[] f = file.listFiles();
		for (int n = 0; n < f.length; n++) {
			if (f[n].isDirectory()){
				dirs.add(f[n]);
				//DirBrowser db = new DirBrowser();
				//db.find(f[]);
			}else{
				files.add(f[n]);
			}
			
		}
/*		for (int m = 0;m <= dirs.size();m++ ){
			DirBrowser db = new DirBrowser();
			db.find(dirs.get(m));
		}*/

		return files;
	}
}
