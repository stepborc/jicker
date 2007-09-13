package org.jicker.util.dirbrowser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class TestDirBrowser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File dir = new File("Z:/" + "CD");

		// Erstelle Filter für sichtbare Verzeichnisse
		IOFileFilter JickerDirFilter = FileFilterUtils
				.andFileFilter(FileFilterUtils.directoryFileFilter(),
						HiddenFileFilter.VISIBLE);

		// Erstelle Filter für Dateien miot der Endung ".mp3"
		IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter(
				FileFilterUtils.fileFileFilter(), FileFilterUtils
						.suffixFileFilter(".mp3"));

		// Verbinde die Filter mit dem or-Filter
		java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
				JickerDirFilter, JickerFileFilter);

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
	}

}
