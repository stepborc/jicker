package org.jicker.mp3.object;

import java.io.File;

public class Mp3File {
	File mp3Dir;
	File mp3File;
	File mp3Complett;
	//Konstruktor wenn alle Parametern des Objekts bekannt sind
	public Mp3File(File mp3Dir, File mp3File, File mp3Complett) {
		this.mp3Dir = mp3Dir;
		this.mp3File = mp3File;
		this.mp3Complett = mp3Complett;
	}
	//Konstruktor wenn nur kompletter Dateiname bekannt ist
	public Mp3File(File Mp3Complett){
		this.mp3Complett = mp3Complett;
		this.mp3File = new File(mp3Complett.toString().substring(mp3Complett.toString().lastIndexOf("\\")+1, mp3Complett.toString().length()));
		this.mp3Dir = new File(mp3Complett.toString().substring(0, mp3Complett.toString().lastIndexOf("\\"))); 
	}
	public Mp3File(Object mp3Complett) {
		this.mp3Complett = new File(mp3Complett.toString());
		this.mp3File = new File(mp3Complett.toString().substring(mp3Complett.toString().lastIndexOf("\\")+1, mp3Complett.toString().length()));
		this.mp3Dir = new File(mp3Complett.toString().substring(0, mp3Complett.toString().lastIndexOf("\\"))); 
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
	public void setMp3File(File mp3File) {
		this.mp3File = mp3File;
	}
	public File getMp3Complett() {
		return mp3Complett;
	}
	public void setMp3Complett(File mp3Complett) {
		this.mp3Complett = mp3Complett;
	}
}
