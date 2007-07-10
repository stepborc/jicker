package org.jicker.util.filebrowser;

import java.io.*;

public class filebrowser {
	static void traverse(File dir, DirectoryVisitor visitor){
		if (!dir.isDirectory()){
			throw new IllegalArgumentException("Kein Verzeichnis:" + dir.getName());
		}
		visitor.enterDirectory(dir);
		File[] entries = dir.listFiles(
				new FileFilter(){
					public boolean accept(File pathname){
						return true;
					}
				});
		for (int i = 0; i < entries.length;++i){
			if (entries[i].isDirectory()){
				traverse(entries[i], visitor);
			}else{
				visitor.visitFile(entries[i]);
			}
		}
		visitor.leaveDirectory(dir);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("d:/");
		// Consolenausgabe
		traverse(file, new DirectoryPrintVisitor());
		// Verzeichnisgroesse
		DirectorySizeVisitor visitor = new DirectorySizeVisitor();
		traverse(file, visitor);
		System.out.println("Vereichnisse: " + visitor.getDirs());
		System.out.println("Dateien     : " + visitor.getFiles());
		System.out.println("Groesse     : " + visitor.getSize());
	}

}
