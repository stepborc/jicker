package org.jicker.mp3.dbobject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Mp3File {
	File mp3Dir;
	File mp3File;
	File mp3Complett;
	long mp3CRC32;

	// Konstruktor wenn alle Parametern des Objekts bekannt sind
	public Mp3File(File mp3Dir, File mp3File, File mp3Complett) {
		this.setMp3Dir(mp3Dir);
		this.setMp3File(mp3File);
		this.setMp3Complett(mp3Complett);
		this.setMp3CRC32(mp3Complett);
	}

 
	// Konstruktor wenn nur kompletter Dateiname bekannt ist
	public Mp3File(File mp3Complett) {
		this.setMp3Complett(mp3Complett);
		this.mp3File = new File(mp3Complett.toString().substring(
				mp3Complett.toString().lastIndexOf("\\") + 1,
				mp3Complett.toString().length()));
		this.mp3Dir = new File(mp3Complett.toString().substring(0,
				mp3Complett.toString().lastIndexOf("\\")));
		this.setMp3CRC32(mp3Complett);
	}
	
	public File getMp3Dir() {
		return mp3Dir;
	}

	public void setMp3Dir(File mp3Dir) {
		this.mp3Dir = mp3Dir;
	}

	public File getMp3File() {
		return mp3File;
	}

	public long getMp3CRC32(){
		return mp3CRC32;
	}
	public void setMp3File(File mp3File) {
		this.mp3File = mp3File;
	}

	public File getMp3Complett() {
		return mp3Complett;
	}

	public void setMp3Complett(File mp3Complett2) {
		this.mp3Complett = mp3Complett2;
	}

	public void setMp3CRC32(File mp3Complett) {
		/*try {
			this.mp3CRC32 = FileUtils.checksumCRC32(new File(mp3Complett));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.mp3CRC32 = 0;
		}*/
	}
}
