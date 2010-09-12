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
        double sBreiteMinute = 23.260;
        int sLaengeGrad = 6;
        double sLaengeMinute = 48.372;
        //double sRichtung = 90.0;
        double sEntfernung = 0.180;
        //N51°23.339 E006°48.564
        System.out.println("Berechnet");
		//CalcWGS84 c = new CalcWGS84(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sRichtung, sEntfernung);
        CalcWGS84 c = new CalcWGS84();
        for(double sRichtung = 0.0; sRichtung<360.0;sRichtung=sRichtung + 0.1){
        c.calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sRichtung, sEntfernung);
		System.out.println(sRichtung +"°: " +c.getLaenge() + " - " + c.getBreite());
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
