package org.jicker.geo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class TestCalcWGS84 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

        System.out.println("Gegeben");
        int sBreiteGrad = 51;
        Float sBreiteMinute = 23.260f;
        int sLaengeGrad = 6;
        Float sLaengeMinute = 48.372f;
        //double sRichtung = 90.0;
        Float sEntfernung = 0.180f;
        //N51°23.339 E006°48.564
        System.out.println("Berechnet");
		//CalcWGS84 c = new CalcWGS84(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sRichtung, sEntfernung);
        CalcWGS84 c = new CalcWGS84(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sEntfernung);

        BufferedWriter csvFile = null;
        csvFile = new BufferedWriter(new FileWriter("gpsfile.csv"));
        
        
		final Kml kml = new Kml();
		final Document document = new Document();
		kml.setFeature(document);
		document.setName("LineString.kml");
		document.setOpen(true);

//		final LookAt lookat = new LookAt();
//		lookat.setLongitude(-122.36415);
//		lookat.setLatitude(37.824553);
//		lookat.setRange(150.0d);
//		lookat.setTilt(50.0d);
//		lookat.setHeading(0.0d);
//
//		document.setAbstractView(lookat);

		final Placemark placemark2 = new Placemark();
		document.getFeature().add(placemark2);
		placemark2.setName("extruded");
		final LineString linestring2 = new LineString();
		placemark2.setGeometry(linestring2);
		linestring2.setExtrude(true);
		linestring2.setTessellate(true);
		List<Coordinate> coord2 = new ArrayList<Coordinate>();
		linestring2.setCoordinates(coord2);
		String strCoord = null;
        for(Float sRichtung = 0.0f; sRichtung<360.0;sRichtung=sRichtung + 0.1f){
        c.calcNewCoords(sRichtung);
		System.out.println(sRichtung +"°: " +c.getBreite() + " - " + c.getLaenge());
		System.out.println(sRichtung +"°: " +c.getBreiteDezimalGrad() + " - " + c.getLaengeDezimalGrad());
		
		//coord2.add(new Coordinate(-122.364167,37.824787,50));
		//coord2.add(new Coordinate(-122.363917,37.824423,50));
		strCoord = c.getBreiteDezimalGrad().toString().replaceAll(",", ".") + "," + c.getLaengeDezimalGrad().toString().replaceAll(",", ".");
		csvFile.write(strCoord);
		csvFile.newLine();
		coord2.add(new Coordinate(strCoord));
		
        }

        System.out.println("Ende");
        csvFile.flush();
        csvFile.close();
        


//		final Placemark placemark1 = new Placemark();
//		document.getFeature().add(placemark1);
//		placemark1.setName("unextruded");
//		final LineString linestring1 = new LineString();
//		placemark1.setGeometry(linestring1);
//		linestring1.setExtrude(false);
//		linestring1.setTessellate(true);
//		List<Coordinate> coord1 = new ArrayList<Coordinate>();
//		linestring1.setCoordinates(coord1);
//		coord1.add(new Coordinate(-122.364383,37.824664,0));
//		coord1.add(new Coordinate(-122.364152,37.824322,0));



		kml.marshal(new File(document.getName()));     

	}

}
