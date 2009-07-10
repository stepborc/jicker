package org.jicker.mp3.id3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
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
		String suffix = ".mp3";
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

		List results = new DirBrowser(JickerFilter, 5).find(new File("E:"));

		// gesehen in
		// http://www.impressive-artworx.de/tutorials.php?kat=java&id=16
		for (int n = 0; n < results.size(); n++) {
			File testFile = new File(results.get(n).toString());
			if (testFile.isFile()){
			AudioFile f = null;
			try {
				f = AudioFileIO.read(testFile);
			} catch (CannotReadException e) {
				System.out.println("CannotReadException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			} catch (TagException e) {
				System.out.println("TagException");
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				System.out.println("ReadOnlyException");
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				System.out.println("InvalidAudioFrameException");
				e.printStackTrace();
			}
			Tag tag = f.getTag();
			String artist = null;
			try {
				artist = tag.getFirstArtist();
			} catch (NullPointerException ex) {
				artist = "Name nicht gespeichert";
				// ex.printStackTrace();
			}
			//System.out.println( testFile + " | " + artist);
			String key = String.format("%-"+96+"s", testFile);
			System.out.println( key + " | " + artist);
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
