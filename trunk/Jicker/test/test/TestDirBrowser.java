package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jicker.util.db.db4o.Datei;
import org.jicker.util.db.db4o.Verzeichnis;
import org.jicker.util.dirbrowser.DirBrowser;
import org.jicker.util.dirbrowser.JickerFilter;

import de.vdheide.mp3.FrameDamagedException;
import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.MP3File;
import de.vdheide.mp3.NoMP3FrameException;

public class TestDirBrowser {

	/**
	 * @param args
	 * @throws NoMP3FrameException 
	 * @throws IOException 
	 * @throws ID3v2IllegalVersionException 
	 * @throws ID3v2DecompressionException 
	 * @throws ID3v2WrongCRCException 
	 * @throws FrameDamagedException 
	 */
	public static void main(String[] args) throws ID3v2WrongCRCException, ID3v2DecompressionException, ID3v2IllegalVersionException, IOException, NoMP3FrameException, FrameDamagedException {
		//Klasse DirBrowser
		//Startverzeichnis dir festlegen
		File dir = new File("z:/cd/");
		JickerFilter filter = new JickerFilter();
		List<File> browse = new DirBrowser(filter.createFilter(new String[] { ".mp3" }), -1).find(dir);
		for (int n = 0; n < browse.size(); n++) {
			if (browse.get(n).isFile()){
				System.out.println("      Datei: " + browse.get(n).getName());
				//MP3File test = new MP3File("E:/Musik/_cd/tmp1/Genesis/03 - Anyway..mp3");
				MP3File test = new MP3File(browse.get(n).toString());
				String separator = " - ";

				String newFileName = "00" + test.getTrack().getTextContent();
				newFileName = newFileName.substring(newFileName.length() - 2) + " "
						+ test.getArtist().getTextContent() + separator
						+ test.getAlbum().getTextContent() + separator
						+ test.getTitle().getTextContent();

				System.out.println(newFileName);
			}else{
				System.out.println("Verzeichnis: " + browse.get(n).getPath());
			}
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
