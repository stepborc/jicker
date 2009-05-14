package org.jicker.viralpetal.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppendText2File {
private final static Logger log = LoggerFactory.getLogger(AppendText2File.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Start ...");
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("AppendText.txt",true));
			out.newLine();
			out.write("Teststring");
			out.close();
		} catch (IOException e) {
			log.info("Fehler");
			e.printStackTrace();
		}
		log.info("Ende");

	}

}
