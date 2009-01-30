package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

public class TestJAudioTagger {
	//JAudioTagger logger ausschalten
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
		File dir = new File("e:/musik/cd/");
		JickerFilter filter = new JickerFilter();
		List<File> browse = new DirBrowser(filter
				.createFilter(new String[] { ".mp3" }), -1).find(dir);
		for (int i = 0; i < browse.size(); i++) {
			if (browse.get(i).isFile()) {
				AudioFile f = null;
				try {
					f = AudioFileIO.read(browse.get(i));
				} catch (InvalidAudioFrameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CannotReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Tag tag = f.getTag();
				// AudioHeader ah = f.getAudioHeader();
				// System.out.print(tag.getFirstTrack() + " - ");
				// System.out.print(browse.get(i) + " - ");
				String firstArtist = null;
				String firstAlbum = null;
				String firstTitle = null;
				String firstTrack = null;
				try {
					firstArtist = tag.getFirstArtist();
				} catch (NullPointerException e) {
					firstArtist = "Artist leer";
				}
				try {
					firstAlbum = tag.getFirstAlbum();
				} catch (NullPointerException e) {
					firstArtist = "Album leer";
				}
				try {
					firstTrack = tag.getFirstTrack();
				} catch (UnsupportedOperationException e) {
					firstTrack = "xxx";
				} catch (NullPointerException e){
					firstTrack = "Track leer";
				}
				try {
					firstTitle = tag.getFirstTitle();
				} catch (NullPointerException e) {
					firstTrack = "Title leer";
				}
				
				System.out.println(firstTrack + " " + firstArtist + " - " + firstAlbum + " - " + firstTitle );

			}
		}
		System.out.println("Fertig");
	}

}
