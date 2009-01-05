package test;

import java.io.IOException;

import de.vdheide.mp3.FrameDamagedException;
import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.MP3File;
import de.vdheide.mp3.NoMP3FrameException;

public class Java_mp3 {

	/**
	 * @param args
	 * @throws NoMP3FrameException
	 * @throws IOException
	 * @throws ID3v2IllegalVersionException
	 * @throws ID3v2DecompressionException
	 * @throws ID3v2WrongCRCException
	 * @throws FrameDamagedException
	 */
	public static void main(String[] args) throws ID3v2WrongCRCException,
			ID3v2DecompressionException, ID3v2IllegalVersionException,
			IOException, NoMP3FrameException, FrameDamagedException {

		MP3File test = new MP3File("E:/Musik/_cd/tmp1/Genesis/03 - Anyway..mp3");

		String separator = " - ";

		String newFileName = "00" + test.getTrack().getTextContent();
		newFileName = newFileName.substring(newFileName.length() - 2) + " "
				+ test.getArtist().getTextContent() + separator
				+ test.getAlbum().getTextContent() + separator
				+ test.getTitle().getTextContent();

		System.out.println(newFileName);
	}

}
