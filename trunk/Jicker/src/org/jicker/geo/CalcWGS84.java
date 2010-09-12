package org.jicker.geo;

public class CalcWGS84 {
	private static double laengeMinute;
	private static int laengeGrad;
	private static double breiteMinute;
	private static int breiteGrad;
	public CalcWGS84(){
	}
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

	public void calcNewCoords(int sBreiteGrad, double sBreiteMinute,
			int sLaengeGrad, double sLaengeMinute, double sRichtung,
			double sEntfernung) {

		double lat = Math.asin(Math.sin(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.cos(decEntfernung(sEntfernung))
				+ Math.cos(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.sin(decEntfernung(sEntfernung))
				* Math.cos(decRichtung(sRichtung)));
		// Gegenüber Excel sind x und y in der Methode atan2 vertauscht
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
		//System.out.println("Latidude: " + lat);
		//System.out.println("dlon: " + dlon);
		//System.out.println("lon: " + lon);

		Double tmpNeueBreiteGrad = 180 / Math.PI * lat;
		breiteGrad = tmpNeueBreiteGrad.intValue();
		breiteMinute = (tmpNeueBreiteGrad - breiteGrad) * 60;
		//System.out.println("Neue Breite: " + breiteGrad + "° " + breiteMinute);

		Double tmpNeueLaengeGrad = 180 / Math.PI * lon;
		//Double tmpNeueLaengeGrad = decRichtung(lon);
		laengeGrad = tmpNeueLaengeGrad.intValue();
		laengeMinute = (tmpNeueLaengeGrad - laengeGrad) * 60;
		//System.out.println("Neue Länge: " + laengeGrad + "° " + laengeMinute);
	}
	public String getBreite(){
		return laengeGrad + "° " + laengeMinute;
	}
	public String getLaenge(){
		return breiteGrad + "° " + breiteMinute;
	}
}
