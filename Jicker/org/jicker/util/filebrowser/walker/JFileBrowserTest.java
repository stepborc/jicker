package org.jicker.util.filebrowser.walker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JFileBrowserTest extends JFileBrowser {

	public JFileBrowserTest(String extension) {
		super(extension);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<File> fileResult= new ArrayList<File>();
		String name = "e:/bilder/Alte Bilder";
		File root = new File(name);
		JFileBrowser fileList = new JFileBrowser("(.*\\.gif$)|(.*\\.jpg$)");
		
		fileList.filterValueCopy = "(.*\\.gif$)|(.*\\.jpg$)";
		fileResult = fileList.dirWalk(root);
		System.out.println(fileResult);
		System.out.println(fileResult.size());
		
		Writer fw = null; 
		 
		try 
		{ 
		  fw = new FileWriter( "test.lst" );
		  for (int n = 0;n<fileResult.size();n++)
		  fw.write( fileResult.get(n).toString() + "\n"); 
		} 
		catch ( IOException e ) { 
		  System.err.println( "Konnte Datei nicht erstellen" ); 
		} 
		finally { 
		  if ( fw != null ) 
		    try { fw.close(); } catch ( IOException e ) { } 
		}
	}

}
