package org.jicker.util.filebrowser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;

public class BrowseDir extends DirectoryWalker {
    // Directories
	      private static final File current      = new File(".");
	      private static final File javaDir      = new File("src/java");
	      private static final File orgDir       = new File(javaDir, "org");
	      private static final File apacheDir    = new File(orgDir, "apache");
	      private static final File commonsDir   = new File(apacheDir, "commons");
	      private static final File ioDir        = new File(commonsDir, "io");
	      private static final File outputDir    = new File(ioDir, "output");
	      private static final File[] dirs       = new File[] {orgDir, apacheDir, commonsDir, ioDir, outputDir};

	      // Files
	      private static final File copyUtils     = new File(ioDir, "CopyUtils.java");
	      private static final File ioUtils       = new File(ioDir, "IOUtils.java");
	      private static final File proxyWriter   = new File(outputDir, "ProxyWriter.java");
	      private static final File nullStream    = new File(outputDir, "NullOutputStream.java");
	      private static final File[] ioFiles     = new File[] {copyUtils, ioUtils};
	      private static final File[] outputFiles = new File[] {proxyWriter, nullStream};

	      // Filters
	      private static final IOFileFilter dirsFilter        = createNameFilter(dirs);
	      private static final IOFileFilter iofilesFilter     = createNameFilter(ioFiles);
	      private static final IOFileFilter outputFilesFilter = createNameFilter(outputFiles);
	      private static final IOFileFilter ioDirAndFilesFilter = new OrFileFilter(dirsFilter, iofilesFilter);
	      private static final IOFileFilter dirsAndFilesFilter = new OrFileFilter(ioDirAndFilesFilter, outputFilesFilter);



	public BrowseDir() {
	      super();
	    }

	    public List result(File startDirectory)  {
	      List results = new ArrayList();
	    try {
			walk(startDirectory, results);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int test = results.size();
	      return results;
	    }
	    private static IOFileFilter createNameFilter(File[] files) {
	    	         String[] names = new String[files.length];
	    	         for (int i = 0; i < files.length; i++) {
	    	             names[i] = files[i].getName();
	    	         }
	    	         return new NameFileFilter(names);
	    	     }

	    public void testFilter() {
	    	          List results = new TestFileFinder(dirsAndFilesFilter, -1).find(javaDir);
	    	         //assertEquals("Result Size", (1 + dirs.length + ioFiles.length + outputFiles.length), results.size());
	    	         //assertTrue("Start Dir", results.contains(javaDir));
	    	         //checkContainsFiles("Dir", dirs, results);
	    	         //checkContainsFiles("IO File", ioFiles, results);
	    	         //checkContainsFiles("Output File", outputFiles, results);
	    	 }


	  }





