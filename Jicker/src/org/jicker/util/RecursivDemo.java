/*
 * 
 */
package org.jicker.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;
import org.jicker.util.db.Db;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;
import org.jicker.util.log.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class RecursivDemo.
 */
public class RecursivDemo {
	
	/** The logger. */
	private static Log logger = Log.getInstance();
	
	/**
	 * Test der rekursiven Verwendung der Db Klasse.
	 * 
	 * @param args the args
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		Db test = new Db();
		test.startDb();
		File dir = new File("e:/bilder/");
		JickerFilter filter = new JickerFilter();
		List browse = new DirBrowser(filter.createFilter(new String[]{".jpg"}),1).find(dir);
		browse.remove(browse.size()-1);
		for(int n = 0;n<browse.size();n++){
			if (((File) browse.get(n)).isFile()) {
				long csum = FileUtils.checksum((File) browse.get(n),
						new CRC32()).getValue();
				test.update("insert into main (kid,name,dir,crc) VALUES(" + n + ",'" + browse.get(n).toString().replace("'","''") + "'," + ((File)(browse.get(n))).isDirectory() + "," + csum + ")");
			} else {
				test.update("insert into main (kid,name,dir,crc) VALUES(" + n + ",'" + browse.get(n).toString().replace("'","''") + "'," + ((File)(browse.get(n))).isDirectory() + ",0 )");
			}
			
		}
		test.stopDb();
	}

}
