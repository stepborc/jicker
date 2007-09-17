package org.jicker.util.dirbrowser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jicker.util.db.Database;
import org.jicker.util.db.RunDatabase;

public class TestDirBrowser {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Datenbank starten
		RunDatabase runDb = new RunDatabase();

		if (runDb.start() ) {
			runDb.createTable();

			// File dir = new File("e:/" + "Bilder/S45-Bilder");
			File dir = new File(args[0] + args[1]);

			// Erstelle Filter f�r sichtbare Verzeichnisse
			IOFileFilter JickerDirFilter = FileFilterUtils.andFileFilter(
					FileFilterUtils.directoryFileFilter(),
					HiddenFileFilter.VISIBLE);

			/*
			 * Alte Variante, neue verwendet SuffixFileFilter // Erstelle Filter
			 * f�r Dateien miot der Endung ".mp3" String suffix = ".jpg";
			 *
			 * String[] suffixFilter = new String[2];
			 * suffixFilter[0]=suffix.toLowerCase();
			 * suffixFilter[1]=suffix.toUpperCase();
			 *
			 * //IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter( //
			 * FileFilterUtils.fileFileFilter(), FileFilterUtils //
			 * .suffixFileFilter(".JPG"));
			 *
			 * IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter(
			 * FileFilterUtils.fileFileFilter(), FileFilterUtils
			 * .suffixFileFilter(".mp3"));
			 */
			// Erstelle Filter f�r Dateien mit der bestimmnten Endungen
			String suffix = ".jpg";
			List<String> suffixFilter = new ArrayList<String>();
			suffixFilter.add(suffix.toLowerCase());
			suffixFilter.add(suffix.toUpperCase());
			SuffixFileFilter sf = new SuffixFileFilter(suffixFilter);

			/*
			 * Alte Variante des FileFilters // Verbinde die Filter mit dem
			 * or-Filter java.io.FileFilter JickerFilter =
			 * FileFilterUtils.orFileFilter( JickerDirFilter, JickerFileFilter);
			 */
			// Neue Variante des FileFilters, Suffix ist nun ein List
			java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
					JickerDirFilter, sf);

			// Erstelle eine Liste
			List results = new DirBrowser(JickerFilter, -1).find(dir);
			for (int n = 0; n < results.size(); n++) {
				System.out.print(n + "\t" + results.get(n));
				if (((File) results.get(n)).isFile()) {
					long csum = FileUtils.checksum((File) results.get(n),
							new CRC32()).getValue();
					System.out.print("\t" + csum + "\n");
				} else {
					System.out.println();
				}
			}

			for (int n = 0; n < results.size(); n++) {
				if (((File) results.get(n)).isFile()) {
					long csum = FileUtils.checksum((File) results.get(n),
							new CRC32()).getValue();
					runDb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ results.get(n).toString().replace("'",
											"''") + "'," + n + "," + csum + ")");
				} else {
					runDb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ results.get(n).toString().replace("'",
											"''") + "'," + n + ", 0 )");
				}
			}
			runDb.shutdown();

			// Daten als Datei sichern
			Writer fw = null;

			try {
				fw = new FileWriter("test.lst");
				for (int n = 0; n < results.size(); n++)
					fw.write(n + "\t" + results.get(n).toString() + "\n");
			} catch (IOException e) {
				System.err.println("Konnte Datei nicht erstellen");
			} finally {
				if (fw != null)
					try {
						fw.close();
					} catch (IOException e) {
					}
			}
		} else {
			System.out.println("Start abgebrochen.");
		}
	}

}
