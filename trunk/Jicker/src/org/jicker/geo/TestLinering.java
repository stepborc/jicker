package org.jicker.geo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;

public class TestLinering {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
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
		coord.add(new Coordinate(-122.365662,37.826988,0));
		coord.add(new Coordinate(-122.365202,37.826302,0));
		coord.add(new Coordinate(-122.364581,37.82655,0));
		coord.add(new Coordinate(-122.365038,37.827237,0));
		coord.add(new Coordinate(-122.365662,37.826988,0));
		kml.marshal(new File("testlinering.kml"));

	}

}
