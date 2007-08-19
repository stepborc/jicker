package org.jicker.util.filebrowser.walker;

import java.io.File;
import java.io.FilenameFilter;

public class BilderEndung implements FilenameFilter {

	public boolean accept(File arg0, String name) {
		// TODO Auto-generated method stub
		return (name.endsWith(".jpg") | name.endsWith(".JPG")| name.endsWith("*.gif"));
	}

}
