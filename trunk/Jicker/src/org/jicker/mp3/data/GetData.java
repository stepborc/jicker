package org.jicker.mp3.data;

import java.io.File;
import java.util.List;

import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class GetData {
	private String dbName = "/JickerMp2.yap";

	public GetData() {
		// TODO Auto-generated constructor stub

		// Datenbank öffnen oder anlegen
		// File dbFile = new File(dbName);
		File dbFile = new File(dbName);
		if (!dbFile.exists()){
			File dir = new File("d:/JickerMp3/");
			JickerFilter filter = new JickerFilter();
			List<File> browse = new DirBrowser(filter
					.createFilter(new String[] { ".mp3" }), -1).find(dir);
		}
		ObjectContainer db = Db4o.openFile(dbName);
		
	}
}
