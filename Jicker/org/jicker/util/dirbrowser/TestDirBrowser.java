package org.jicker.util.dirbrowser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class TestDirBrowser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File dir = new File("E:/" + "Bilder");

		// Erstelle Filter für sichtbare Verzeichnisse
		IOFileFilter JickerDirFilter = FileFilterUtils
				.andFileFilter(FileFilterUtils.directoryFileFilter(),
						HiddenFileFilter.VISIBLE);

/*		Alte Variante, neue verwendet SuffixFileFilter
 * 		// Erstelle Filter für Dateien miot der Endung ".mp3"
		String suffix = ".jpg";

		String[] suffixFilter = new String[2];
		suffixFilter[0]=suffix.toLowerCase();
		suffixFilter[1]=suffix.toUpperCase();

		//IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter(
		//		FileFilterUtils.fileFileFilter(), FileFilterUtils
		//				.suffixFileFilter(".JPG"));

		IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter(
				FileFilterUtils.fileFileFilter(), FileFilterUtils
						.suffixFileFilter(".mp3"));
*/
		// Erstelle Filter für Dateien mit der bestimmnten Endungen
		String suffix = ".jpg";
		List<String> suffixFilter = new ArrayList<String>();
		suffixFilter.add(suffix.toLowerCase());
		suffixFilter.add(suffix.toUpperCase());
		SuffixFileFilter sf = new SuffixFileFilter(suffixFilter);



/*		Alte Variante des FileFilters
 * 		// Verbinde die Filter mit dem or-Filter
		java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
				JickerDirFilter, JickerFileFilter);
*/
		//Neue Variante des FileFilters, Suffix ist nun ein List
		java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
		JickerDirFilter, sf);


		// Erstelle eine Liste
		List results = new DirBrowser(JickerFilter, -1).find(dir);
		for (int n = 0; n < results.size(); n++) {
			System.out.println(n + "\t" + results.get(n));
		}

		// Daten als Datei sichern
		Writer fw = null;

		try {
			fw = new FileWriter("test.lst");
			for (int n = 0; n < results.size(); n++)
				fw.write(results.get(n).toString() + "\n");
		} catch (IOException e) {
			System.err.println("Konnte Datei nicht erstellen");
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
				}
		}
		try {
			Connection c = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "sa", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
