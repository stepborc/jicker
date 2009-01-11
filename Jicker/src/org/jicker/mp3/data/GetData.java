package org.jicker.mp3.data;

import java.io.File;
import java.util.List;

import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class GetData {
	private String dbName = "JickerMp2.yap";

	public GetData() throws SecurityException, NoSuchFieldException {
		// TODO Auto-generated constructor stub

		// Datenbank öffnen oder anlegen
		// File dbFile = new File(dbName);
		File dbFile = new File(dbName);
		dbFile.delete();
		List<File> browse =null;
		if (!dbFile.exists()){
			File dir = new File("Z:/CD/");
			JickerFilter filter = new JickerFilter();
			browse = new DirBrowser(filter
					.createFilter(new String[] { ".mp3" }), -1).find(dir);
			System.out.println(browse.size());
		}else{
			//DB synchronisieren
		}
		ObjectContainer db = Db4o.openFile(dbName);
		Mp3File mp3File = null;
		for (int n = 0; n < browse.size();n++){
			
			mp3File = new Mp3File(browse.get(n).getName(), browse.get(n).getPath(),browse.get(n).lastModified(),browse.get(n).getAbsolutePath(),browse.get(n).getAbsoluteFile());
			db.store(mp3File);
			System.out.println(n + 1);
		}
		ObjectSet<Mp3File> mp3FileList = db.get(Mp3File.class);
		Mp3File mp3FileGet = null;
		while (mp3FileList.hasNext()){
			mp3FileGet = mp3FileList.next();
			System.out.println(mp3FileGet.getAbsolutDir());
		}
	}
}
