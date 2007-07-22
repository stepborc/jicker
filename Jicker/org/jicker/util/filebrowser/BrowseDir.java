package org.jicker.util.filebrowser;

import java.io.File;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;

public class BrowseDir extends DirectoryWalker {
	public BrowseDir(IOFileFilter dirFilter, IOFileFilter fileFilter) {
	      super(dirFilter, fileFilter, -1);
	    }
	  }
	  

	


