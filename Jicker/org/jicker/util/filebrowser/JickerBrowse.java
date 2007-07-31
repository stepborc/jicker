package org.jicker.util.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;

public class JickerBrowse extends DirectoryWalker {

	public JickerBrowse() {
		super();
	}

	public JickerBrowse(FileFilter arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public JickerBrowse(IOFileFilter arg0, IOFileFilter arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}
public List jBrowser(){
	List results = new ArrayList();
	try {
		walk(new File("c:/tmp"), results);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return results;
}
public static void main(String[] args)  {
	JickerBrowse jb = new JickerBrowse();
	jb.toString();
}
}
