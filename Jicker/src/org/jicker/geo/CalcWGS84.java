package org.jicker.geo;

public class CalcWGS84 {

	private int nLaengeMinute;
	private String nLaengeGrad;
	public CalcWGS84(int sBreiteGrad, double sBreiteMinute, int sLaengeGrad,
			double sLaengeMinute, double sRichtung, double sEntfernung) {
		calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute,
				sRichtung, sEntfernung);
	}

	private static double decBreite(int sBreiteGrad, double sBreiteMinute) {
		return Math.PI / 180 * (sBreiteGrad + (sBreiteMinute / 60));
	}

	private static double decLaenge(int sLaengeGrad, double sLaengeMinute) {
		return Math.PI / 180 * (sLaengeGrad + (sLaengeMinute / 60));
	}

	private static double decRichtung(Double lon) {
		return Math.PI / 180 * lon;
	}

	private static double decEntfernung(double sEntfernung) {
		return (Math.PI / (180 * 60)) * sEntfernung / 1.852;
	}

	private static void calcNewCoords(int sBreiteGrad, double sBreiteMinute,
			int sLaengeGrad, double sLaengeMinute, double sRichtung,
			double sEntfernung) {

		double lat = Math.asin(Math.sin(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.cos(decEntfernung(sEntfernung))
				+ Math.cos(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.sin(decEntfernung(sEntfernung))
				* Math.cos(decRichtung(sRichtung)));
		// Gegen�ber Excel sind x und y in der Methode atan2 vertauscht
		Double dlon = Math.atan2(
				Math.sin(decRichtung(sRichtung))
						* Math.sin(decEntfernung(sEntfernung))
						* Math.cos(decBreite(sBreiteGrad, sBreiteMinute)),
				Math.cos(decEntfernung(sEntfernung))
						- Math.sin(decBreite(sBreiteGrad, sBreiteMinute))
						* Math.sin(lat))
				* -1;
		Double pLon = decLaenge(sLaengeGrad, sLaengeMinute) - dlon + Math.PI;
		Double pLonn = pLon / 2 / Math.PI;

		Double lon = pLon - pLonn.intValue() - Math.PI;
		System.out.println("Latidude: " + lat);
		System.out.println("dlon: " + dlon);
		System.out.println("lon: " + lon);

		Double tmpNeueBreiteGrad = 180 / Math.PI * lat;
		int nBreiteGrad = tmpNeueBreiteGrad.intValue();
		Double nBreiteMinute = (tmpNeueBreiteGrad - nBreiteGrad) * 60;
		System.out
				.println("Neue Breite: " + nBreiteGrad + "� " + nBreiteMinute);

		Double tmpNeueLaengeGrad = 180 / Math.PI * lon;
		//Double tmpNeueLaengeGrad = decRichtung(lon);
		int nLaengeGrad = tmpNeueLaengeGrad.intValue();
		Double nLaengeMinute = (tmpNeueLaengeGrad - nLaengeGrad) * 60;
		System.out.println("Neue L�nge: " + nLaengeGrad + "� " + nLaengeMinute);
	}
	public String getBreite(){
		return nLaengeGrad + "� " + nLaengeMinute;
		
	}
}
