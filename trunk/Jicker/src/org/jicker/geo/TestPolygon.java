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
	 */
	public static void main(String[] args) {
		final Point point = new Point();
		List<Coordinate> coord = new ArrayList<Coordinate>();
		point.setCoordinates(coord);
		coord.add(new Coordinate(-90.86948943473118,48.25450093195546));
        try {
			point.marshal(new File("HelloKml.kml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
