/*
 * 
 */
package org.jicker.util.dirbrowser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.log4j.Level;
import org.jicker.util.log.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class DirBrowser.
 */
public class DirBrowser extends DirectoryWalker {

    /**
     * Instantiates a new dir browser.
     * 
     * @param filter the filter
     * @param depthLimit the depth limit
     */
    public DirBrowser(FileFilter filter, int depthLimit) {
        super(filter, depthLimit);
    }

    /**
     * Instantiates a new dir browser.
     * 
     * @param dirFilter the dir filter
     * @param fileFilter the file filter
     * @param depthLimit the depth limit
     */
    protected DirBrowser(IOFileFilter dirFilter, IOFileFilter fileFilter, int depthLimit) {
        super(dirFilter, fileFilter, depthLimit);
    }

    /**
     * find files.
     * 
     * @param startDirectory the start directory
     * 
     * @return the list
     */
    public List find(File startDirectory) {
       List results = new ArrayList();
       try {
           walk(startDirectory, results);
       } catch(IOException ex) {
    	   Log.log(Level.FATAL, this, "FILEerror", new String[]{ex.toString()});
           //ex.printStackTrace();
       }
       return results;
    }

    /**
     * Handles a directory end by adding the File to the result set.
     * 
     * @param directory the directory
     * @param depth the depth
     * @param results the results
     */
    @SuppressWarnings("unchecked")
	protected void handleDirectoryEnd(File directory, int depth, Collection results) {
        results.add(directory);
    }

    /**
     * Handles a file by adding the File to the result set.
     * 
     * @param file the file
     * @param depth the depth
     * @param results the results
     */
    @SuppressWarnings("unchecked")
	protected void handleFile(File file, int depth, Collection results) {
        results.add(file);
    }
}
