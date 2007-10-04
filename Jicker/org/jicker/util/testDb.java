package org.jicker.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;
import org.jicker.util.db.Db;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;
import org.jicker.util.log.Log;

public class testDb {
	private static Log logger = Log.getInstance();
	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException {
		
		Db testdb = new Db();
		if (testdb.startDb()){
			File dir = new File("e:/bilder/");
			JickerFilter filter = new JickerFilter();
			//Aufruf des DirBrowser mit Angabe des Verzeichnisses und den Dateiendungen
			//Mehrfachnennungen sind erlaubt.
			List browse = new DirBrowser(filter.createFilter(new String[]{".jpg"}),-1).find(dir);
			for(int n = 0;n<browse.size();n++){
				if (((File) browse.get(n)).isFile()) {
					long csum = FileUtils.checksum((File) browse.get(n),
							new CRC32()).getValue();
					testdb.update("insert into main (kid,name,dir,crc) VALUES(" + n + ",'" + browse.get(n).toString().replace("'","''") + "'," + ((File)(browse.get(n))).isDirectory() + "," + csum + ")");
				} else {
					testdb.update("insert into main (kid,name,dir,crc) VALUES(" + n + ",'" + browse.get(n).toString().replace("'","''") + "'," + ((File)(browse.get(n))).isDirectory() + ",0 )");
				}
				
			}
/*			try {
				List i = testdb.query("SELECT * FROM main");
				Iterator a = i.iterator();
				int n = 1;
				while (a.hasNext()){
					System.out.println(i.get(n));
					n++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//testdb.dropTable();
			testdb.stopDb();
		}
		
	}

}
