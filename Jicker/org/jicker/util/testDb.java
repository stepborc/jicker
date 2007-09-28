package org.jicker.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
			File dir = new File("z:/CD");
			JickerFilter filter = new JickerFilter();
			//Aufruf des DirBrowser mit Angabe des Verzeichnisses und den Dateiendungen
			//Mehrfachnennungen sind erlaubt.
			List browse = new DirBrowser(filter.createFilter(new String[]{".jpg"}),-1).find(dir);
			for(int n = 0;n<browse.size();n++){
				if (((File) browse.get(n)).isFile()) {
					long csum = FileUtils.checksum((File) browse.get(n),
							new CRC32()).getValue();
					testdb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ browse.get(n).toString().replace("'",
											"''") + "'," + n + "," + csum + ")");
				} else {
					//logger.info("Untersuche Verzeichnis " + browse.get(n));
					// System.out.println(browse.get(n));
					testdb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ browse.get(n).toString().replace("'",
											"''") + "'," + n + ", 0 )");
				}
				
			}
			try {
				testdb.query("SELECT * FROM main");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//testdb.dropTable();
			testdb.stopDb();
		}
		
	}

}
