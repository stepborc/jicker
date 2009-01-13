package org.jicker.mp3.data;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ReadData {
	/**
	 * 
	 */
	public ReadData(String dbName) {
		ObjectContainer db = Db4o.openFile(dbName);
		Mp3File mp3File = new Mp3File(null, null, 0, null, null);;
		ObjectSet<Mp3File> mp3FileList = db.queryByExample(mp3File);
		Mp3File mp3FileGet = null;
		while (mp3FileList.hasNext()){
			mp3FileGet = mp3FileList.next();
			System.out.println(mp3FileGet.getDir());
		}
		db.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		String dbName = "JickerMp2.yap";
		new ReadData(dbName);
	}
}
