package org.jicker.geo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.Polygon;

public class TestPolygon {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		final Kml kml = new Kml();
		final Document document = new Document();
		kml.setFeature(document);
		document.setName("Polygon.kml");
		document.setOpen(false);
		final Placemark placemark = new Placemark();
		document.getFeature().add(placemark);
		placemark.setName("hollow box");

		final Polygon polygon = new Polygon();
		placemark.setGeometry(polygon);

		polygon.setExtrude(true);
		polygon.setAltitudeMode(AltitudeMode.RELATIVE_TO_GROUND);

		final Boundary outerboundary = new Boundary();
		polygon.setOuterBoundaryIs(outerboundary);

		final LinearRing outerlinearring = new LinearRing();
		outerboundary.setLinearRing(outerlinearring);

		List<Coordinate> outercoord = new ArrayList<Coordinate>();
		outerlinearring.setCoordinates(outercoord);
		outercoord.add(new Coordinate(-122.366278,37.818844,30));
		outercoord.add(new Coordinate(-122.365248,37.819267,30));
		outercoord.add(new Coordinate(-122.365640,37.819861,30));
		outercoord.add(new Coordinate(-122.366669,37.819429,30));
		outercoord.add(new Coordinate(-122.366278,37.818844,30));

		final Boundary innerboundary = new Boundary();
		polygon.getInnerBoundaryIs().add(innerboundary);

		final LinearRing innerlinearring = new LinearRing();
		innerboundary.setLinearRing(innerlinearring);

		List<Coordinate> innercoord = new ArrayList<Coordinate>();
		innerlinearring.setCoordinates(innercoord);
		innercoord.add(new Coordinate(-122.366212,37.818977,30));
		innercoord.add(new Coordinate(-122.365424,37.819294,30));
		innercoord.add(new Coordinate(-122.365704,37.819731,30));
		innercoord.add(new Coordinate(-122.366488,37.819402,30));
		innercoord.add(new Coordinate(-122.366212,37.818977,30));
		kml.marshal(new File("testpolygon.kml"));

	}
}
