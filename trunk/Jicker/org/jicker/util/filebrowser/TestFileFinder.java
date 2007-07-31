package org.jicker.util.filebrowser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;

class TestFileFinder extends DirectoryWalker {

	         protected TestFileFinder(FileFilter filter, int depthLimit) {
	             super(filter, depthLimit);
	         }

	         protected TestFileFinder(IOFileFilter dirFilter, IOFileFilter fileFilter, int depthLimit) {
	             super(dirFilter, fileFilter, depthLimit);
	         }

	         /** find files. */
	         protected List find(File startDirectory) {
	            List results = new ArrayList();
	            try {
	                walk(startDirectory, results);
	            } catch(IOException ex) {
	                //Assert.fail(ex.toString());
	            }
	            return results;
	         }

	         /** Handles a directory end by adding the File to the result set. */
	         protected void handleDirectoryEnd(File directory, int depth, Collection results) {
	             results.add(directory);
	         }

	         /** Handles a file by adding the File to the result set. */
	         protected void handleFile(File file, int depth, Collection results) {
	             results.add(file);
	         }
	     }
