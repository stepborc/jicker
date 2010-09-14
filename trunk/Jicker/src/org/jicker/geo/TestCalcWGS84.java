package org.jicker.geo;

import java.io.File;
import java.io.FileNotFoundException;


import de.micromata.opengis.kml.v_2_2_0.Kml;

public class TestCalcWGS84 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
        for(float sRichtung = 0.0f; sRichtung<360.0;sRichtung=sRichtung + 0.1f){
        c.calcNewCoords(sRichtung);
		System.out.println(sRichtung +"°: " +c.getBreite() + " - " + c.getLaenge());
		System.out.println(sRichtung +"°: " +c.getBreiteDezimalGrad() + " - " + c.getLaengeDezimalGrad());
        }
        final Kml kml = new Kml();
        kml.createAndSetPlacemark()
           .withName("London, UK").withOpen(Boolean.TRUE)
           .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
        try {
			kml.marshal(new File("HelloKml.kml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
