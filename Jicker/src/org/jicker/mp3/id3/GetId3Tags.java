package org.jicker.mp3.id3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.reference.Tagger;
import org.jicker.util.dirbrowser.DirBrowser;

public class GetId3Tags {
	// Disable Jaudiotagger logs
	static {
		try {
			LogManager.getLogManager().readConfiguration(
					new ByteArrayInputStream("org.jaudiotagger.level = OFF"
							.getBytes()));
		} catch (Exception e) {
			// Log.error(e);
		}
	}

	public String[][] getMP3Array(String retArray[][]) {

		retArray[0][0] = "a";
		retArray[0][1] = "b";
		return retArray;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Liste aller MP3 Files erstellen
		// Erstelle Filter für sichtbare Verzeichnisse
		IOFileFilter JickerDirFilter = FileFilterUtils
				.andFileFilter(FileFilterUtils.directoryFileFilter(),
						HiddenFileFilter.VISIBLE);

		// Erstelle Filter für Dateien mit der bestimmnten Endungen
		String suffix = ".ogg";
		List<String> suffixFilter = new ArrayList<String>();
		suffixFilter.add(suffix.toLowerCase());
		suffixFilter.add(suffix.toUpperCase());
		suffixFilter.add(".mp3".toLowerCase());
		suffixFilter.add(".mp3".toUpperCase());
		suffixFilter.add(".flac".toLowerCase());
		suffixFilter.add(".flac".toUpperCase());
		SuffixFileFilter sf = new SuffixFileFilter(suffixFilter);

		/*
		 * Alte Variante des FileFilters // Verbinde die Filter mit dem
		 * or-Filter java.io.FileFilter JickerFilter =
		 * FileFilterUtils.orFileFilter( JickerDirFilter, JickerFileFilter);
		 */
		// Neue Variante des FileFilters, Suffix ist nun ein List
		java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
				JickerDirFilter, sf);

		List results = new DirBrowser(JickerFilter, 5).find(new File(args[0]));
		// Array für die Rückgabe des Ergebnisses
		String[][] dataValue = null;
		/*
		 * Wurde zur berechnung der Spaltenbreiten weiter unten verwendet. Wird
		 * erstmal nicht mehr gebraucht int spaltenBreite = 0; for (int n = 0; n
		 * < results.size(); n++) { String resultsElement =
		 * results.get(n).toString(); resultsElement =
		 * resultsElement.substring(resultsElement .lastIndexOf("\\") + 1,
		 * resultsElement.length()); if (resultsElement.length() >
		 * spaltenBreite) { spaltenBreite = resultsElement.length(); } }
		 */
		// gesehen in
		// http://www.impressive-artworx.de/tutorials.php?kat=java&id=16
		for (int n = 0; n < results.size(); n++) {
			File testFile = new File(results.get(n).toString());
			if (testFile.isFile()) {
				AudioFile f = null;
				MP3File mf = null;
				try {
					f = AudioFileIO.read(testFile);
					if (testFile.getName().endsWith(".mp3")) {
						mf = (MP3File) AudioFileIO.read(testFile);
					}
				} catch (CannotReadException e) {
					System.out.println("CannotReadException");
					e.printStackTrace();
					continue;
				} catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					continue;
				} catch (TagException e) {
					System.out.println("TagException");
					e.printStackTrace();
					continue;
				} catch (ReadOnlyFileException e) {
					System.out.println("ReadOnlyException");
					e.printStackTrace();
					continue;
				} catch (InvalidAudioFrameException e) {
					System.out.println("InvalidAudioFrameException");
					e.printStackTrace();
					continue;
				}
				if (testFile.getName().endsWith(".mp3")) {

					if (mf.getID3v1Tag() != null) {
						System.out.println("v1-->"
								+ mf.getID3v1Tag().getMajorVersion() + "."
								+ mf.getID3v1Tag().getRelease() + "."
								+ mf.getID3v1Tag().getRevision());

					}
					if (mf.getID3v2Tag() != null) {
						System.out.println("v2-->"
								+ mf.getID3v2Tag().getMajorVersion() + "."
								+ mf.getID3v2Tag().getRelease() + "."
								+ mf.getID3v2Tag().getRevision());

					}
					if (mf.getID3v2TagAsv24() != null) {
						System.out.println("v2..-->"
								+ mf.getID3v2TagAsv24().getMajorVersion() + "."
								+ mf.getID3v2TagAsv24().getRelease() + "."
								+ mf.getID3v2TagAsv24().getRevision());

					}
				}
				Tag tag = f.getTag();

				// Format des Audiofiles ausgeben
				// System.out.println(f.getAudioHeader().getFormat());
				String artist = null;
				FieldKey artistArray[] = null;
				// Schleife über alle FieldKeys (ARTIST, TRACK, etc...)
				String tagLine = "" + n + " | ";
				for (FieldKey c : FieldKey.values()) {
					try {
						tagLine = tagLine + " | " + c + ": " + tag.getFirst(c);
					} catch (UnsupportedOperationException ex) {
						tagLine = tagLine + " | " + c + ": "
								+ ex.getLocalizedMessage();
					} catch (KeyNotFoundException ex) {
						tagLine = tagLine + " | " + c + ": "
								+ ex.getLocalizedMessage();
					} catch (NullPointerException ex) {
						tagLine = tagLine + " | " + c + ": "
								+ ex.getLocalizedMessage();
					}
				}
				System.out.println(tagLine);
				// catch (UnsupportedOperationException ex){

				// }
				// System.out.println( testFile + " | " + artist);
				/*
				 * String testFileOut = testFile.toString().substring(
				 * testFile.toString().lastIndexOf("\\") + 1,
				 * testFile.toString().length()); String key =
				 * String.format("%-" + spaltenBreite + "s", testFileOut);
				 * System.out.println(key + " | " + artist);
				 */
				// dataValue[n][0] = key;
				// dataValue[n][1] = artist;
				// System.out.println(f.displayStructureAsPlainText());
				// System.out.println(f.displayStructureAsXML());
				// System.out.println(f.getAudioHeader().getChannels());
				// AudioHeader header = f.getAudioHeader();
				// System.out.println(header.getBitRate());
				// Iterator iterator = tag.getFields();
				// while(iterator.hasNext()) {
				// System.out.println(iterator.next());
				// }
			}
		}
	}
}
