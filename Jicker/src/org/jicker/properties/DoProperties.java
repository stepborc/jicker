package org.jicker.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DoProperties {

	public DoProperties(){
	//Properties p = new Properties();
	new Properties();
    // Laden der Properties
	String propertiesFile = "gda.properties";
	//2009-01-30: propOutFile auskommentiert
	//FileOutputStream propOutFile = null;
	FileInputStream propInFile = null;
/*	try {
		propOutFile = new FileOutputStream( propertiesFile );
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	try {
		propInFile = new FileInputStream( propertiesFile );
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	Properties pIn = new Properties();
	try {
		pIn.load(propInFile);
	} catch (IOException e1) {
		System.out.println("Properties File nicht gefunden.");
		e1.printStackTrace();
	}
	pIn.list(System.out);
	System.out.println(pIn.getProperty("BaseDir"));
	
	try {
		propInFile.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Properties pout = new Properties();
	
	
    /*
     * try {
    	p.load(getClass().getResourceAsStream(propertiesFile));
	} catch (IOException e) {
		System.out.println("Datei " + propertiesFile + " nicht gefunden.");
		e.printStackTrace();
	}
    // Lesen der Prperties 
    String imgName = p.getProperty("background.image");
    System.out.println("background.image = " + imgName);
    
    p.setProperty("background.image", "geschrieben");
    
    imgName = p.getProperty("background.image");
    System.out.println("background.image = " + imgName);
    
    p.setProperty("background.image", "Test");
    imgName = p.getProperty("background.image");
    System.out.println("background.image = " + imgName);
    */
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DoProperties();

	}

}
