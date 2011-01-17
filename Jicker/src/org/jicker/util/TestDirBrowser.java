/*
 * 
 */
package org.jicker.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jicker.util.db.RunDatabase;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.log.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDirBrowser.
 */
public class TestDirBrowser {

	// FileHandler handler = new FileHandler("my.log");
	//static Logger logger = Logger.getLogger(TestDirBrowser.class);
	/** The logger. */
	private static Log logger = Log.getInstance();

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public static void main(String[] args) throws IOException, SQLException {
		//SimpleLayout layout = new SimpleLayout();
		//ConsoleAppender consoleAppender = new ConsoleAppender(layout);
	    //  FileAppender fileAppender = new FileAppender( layout, "MeineLogDatei.log", false );
	    //  logger.addAppender( fileAppender );
		//logger.addAppender(consoleAppender);
		// Datenbankobject initialiseren
		RunDatabase runDb = new RunDatabase();

		// Datenbank starten
		if (runDb.start()) {
			//logger.info("Datenbank gestartet...");
			// Wenn erfolgreich gestartet Tabelle löschen ...
			if (runDb.checkTable("MAIN")) {
				runDb.dropTable();
				runDb.createTable();
			} else {
				// ..Tabelle erstellen
				runDb.createTable();
			}
			// File dir = new File("e:/" + "Bilder/S45-Bilder");
			File dir = new File(args[0] + args[1]);
			//File dir = new File("C:\\Daten\\Bilder\\2008_02_25\\");
			
			// Erstelle Filter für sichtbare Verzeichnisse
			IOFileFilter JickerDirFilter = FileFilterUtils.andFileFilter(
					FileFilterUtils.directoryFileFilter(),
					HiddenFileFilter.VISIBLE);

			/*
			 * Alte Variante, neue verwendet SuffixFileFilter // Erstelle Filter
			 * für Dateien miot der Endung ".mp3" String suffix = ".jpg";
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
			// Erstelle Filter für Dateien mit der bestimmnten Endungen
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

			// Erstelle eine Verzeichnisliste
			List results = new DirBrowser(JickerFilter, 1).find(dir);
			/*
			 * Nur zu Testzwecken for (int n = 0; n < results.size(); n++) {
			 * System.out.print(n + "\t" + results.get(n)); if (((File)
			 * results.get(n)).isFile()) { long csum = FileUtils.checksum((File)
			 * results.get(n), new CRC32()).getValue(); System.out.print("\t" +
			 * csum + "\n"); } else { System.out.println(); } }
			 */
			// Kopieren in die Datenbank
			for (int n = 0; n < results.size(); n++) {
				if (((File) results.get(n)).isFile()) {
					long csum = FileUtils.checksum((File) results.get(n),
							new CRC32()).getValue();
					runDb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ results.get(n).toString().replace("'",
											"''") + "'," + n + "," + csum + ")");
				} else {
					//logger.info("Untersuche Verzeichnis " + results.get(n));
					// System.out.println(results.get(n));
					runDb
							.update("INSERT INTO main (str_col,num_col,crc) VALUES('"
									+ results.get(n).toString().replace("'",
											"''") + "'," + n + ", 0 )");
				}
			}

			// runDb.showTable();

			// Datenbank schliessen
			runDb.shutdown();
			/*
			 * Nur zu Testzwecken // Daten als Datei sichern Writer fw = null;
			 * 
			 * try { fw = new FileWriter("test.lst"); for (int n = 0; n <
			 * results.size(); n++) fw.write(n + "\t" +
			 * results.get(n).toString() + "\n"); } catch (IOException e) {
			 * System.err.println("Konnte Datei nicht erstellen"); } finally {
			 * if (fw != null) try { fw.close(); } catch (IOException e) { } }
			 */
		} else {
			//logger.log(Level.ALL, "Programmstart abgebrochen");
			System.out.println("Start abgebrochen.");
		}
	}

}
