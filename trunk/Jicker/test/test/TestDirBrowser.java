package test;

import java.io.File;
import java.util.List;

import org.jicker.util.db.db4o.Datei;
import org.jicker.util.db.db4o.Verzeichnis;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

public class TestDirBrowser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Klasse DirBrowser
		//Startverzeichnis dir festlegen
		File dir = new File("e:/musik/cd/");
		JickerFilter filter = new JickerFilter();
		List browse = new DirBrowser(filter.createFilter(new String[] { ".mp3" }), -1).find(dir);
		for (int n = 0; n < browse.size(); n++) {
			System.out.println(browse.get(n));
		}
			/*if (((File) browse.get(n)).isFile()) {
				verzeichnis = new Verzeichnis(browse.get(n).toString().substring(0, browse.get(n).toString().lastIndexOf("\\")));
				Datei datei = new Datei(browse.get(n).toString(), verzeichnis);
				db.set(datei);
			} else {
				verzeichnis = new Verzeichnis(browse.get(n).toString());
				db.set(verzeichnis);
			}*/

	
	}
}
