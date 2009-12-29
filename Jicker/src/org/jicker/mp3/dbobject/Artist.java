package org.jicker.mp3.dbobject;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Artist {
	String artist;

	public Artist(String artist) {
		this.setArtist(artist);
	}

	public Artist(File mp3Complett) {
		AudioFile f = null;
		try {
			f = AudioFileIO.read(mp3Complett);
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
			//Ab Version 2.0.1 von jaudiotagger nicht mehr m�glich
			//artist = tag.getFirstArtist();
			//Neu...
			artist = tag.getFirst(FieldKey.ARTIST);
		} catch (NullPointerException ex) {
			artist = "Ohne K�nstler";
			// ex.printStackTrace();
		}
		this.setArtist(artist);

	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
