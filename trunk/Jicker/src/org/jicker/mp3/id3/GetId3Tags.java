package org.jicker.mp3.id3;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class GetId3Tags {

	/**
	 * @param args
	 * @throws InvalidAudioFrameException 
	 * @throws ReadOnlyFileException 
	 * @throws TagException 
	 * @throws IOException 
	 * @throws CannotReadException 
	 */
	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		// gesehen in http://www.impressive-artworx.de/tutorials.php?kat=java&id=16
		File testFile = new File("Z:\\CD_old\\Garden Ruin\\01 Calexico - Garden Ruin - Cruel.mp3");
		AudioFile f = AudioFileIO.read(testFile);
		Tag tag = f.getTag();
		System.out.println(f.displayStructureAsPlainText());
		System.out.println(f.displayStructureAsXML());
		System.out.println(f.getAudioHeader().getTrackLength());
		
		AudioHeader header = f.getAudioHeader();
		System.out.println(header.getBitRate());
		//Iterator iterator = tag.getFields();
		//while(iterator.hasNext()) {
		//   System.out.println(iterator.next());
		//} 

	}

}
