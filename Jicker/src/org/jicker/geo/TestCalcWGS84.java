package org.jicker.geo;

import java.io.File;
import java.io.FileNotFoundException;


import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;

public class TestCalcWGS84 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

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
        final Kml kml = new Kml();
        final Document document = new Document();
        kml.setFeature(document);

        final Placemark placemark = new Placemark();
        document.getFeature().add(placemark);
        placemark.setName("LinearRing.kml");

        final Polygon polygon = new Polygon();
        placemark.setGeometry(polygon);
        final Boundary boundary = new Boundary();
        polygon.setOuterBoundaryIs(boundary);

        final LinearRing linearring = new LinearRing();
        boundary.setLinearRing(linearring);

        List<Coordinate> coord = new ArrayList<Coordinate>();
        linearring.setCoordinates(coord);
//        coord.add(new Coordinate(-122.365662,37.826988,0));
//        coord.add(new Coordinate(-122.365202,37.826302,0));
//        coord.add(new Coordinate(-122.364581,37.82655,0));
//        coord.add(new Coordinate(-122.365038,37.827237,0));
//        coord.add(new Coordinate(-122.365662,37.826988,0));

        for(Float sRichtung = 0.0f; sRichtung<360.0;sRichtung=sRichtung + 0.1f){
        c.calcNewCoords(sRichtung);
		System.out.println(sRichtung +"°: " +c.getBreite() + " - " + c.getLaenge());
		System.out.println(sRichtung +"°: " +c.getBreiteDezimalGrad() + " - " + c.getLaengeDezimalGrad());
		
		coord.add(new Coordinate((c.getBreiteDezimalGrad() + "," + c.getLaengeDezimalGrad())));
        }
        kml.marshal(new File("HelloKml.kml"));
        System.out.println("Ende");


	}

}
