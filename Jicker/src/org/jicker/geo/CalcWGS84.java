package org.jicker.geo;




// TODO: Auto-generated Javadoc
/**
 * The Class CalcWGS84.
 * 
 * Breitengrad: Noerdliche bzw. suedliche Position 
 * 				eines Punktes vom Aequator
 * 				engl. Latitude. Abk. Lat.
 * 				Werte: 0° am Aequator,
 * 				       +90° am Nord- und (bezeichnet als + oder N)
 * 				       -90° am Suedpol (bezeichnet als - oder S)
 * 				
 * Laengengrad: Oestliche bzw. westliche Position
 *              eines Punktes om Null-Meridian
 *              engl. Longitude Abk. Lon oder auch Long.
 *              Formelzeichen: Lambda
 *              Werte: 0° Null-Meridian (Greenwich)
 *                     +180° in Richtung Osten (bezeichnet als + oder O)
 *                     -180° in Richtung Westen (bezeichnet als - oder W)
 *                     
 * Dezimal-Grad > Grad, Minuten, Sekunden
 * Stellen hinter dem Komma mit 60 multiplizieren:
 * Beispiel: 49,6019° in Dezimalgrad entspricht
 * in Dezimal-Minuten: 49°[0,6019x60]' = 49°36,114';
 * in Dezimal-Sekunden: 49°36'[0,114x60]" = 49°36'6,84".
 *
 * Grad, Minuten, Sekunden > Dezimalgrad
 * Die Sekunden bzw. Minuten durch 60 dividieren:
 * Beispiel: 50°35'25" entspricht
 * in Dezimal-Minuten: 50°35,[25":60]' = 50°35,417';
 * in Dezimalgrad: 50,[35,417':60]° = 50,59028° 
 */
public class CalcWGS84 {
	
	/** The laenge minute. */
	private static Double laengeMinute;
	
	/** The laenge grad. */
	private static int laengeGrad;
	
	/** The breite minute. */
	private static Double breiteMinute;
	
	/** The breite grad. */
	private static int breiteGrad;
	
	/** The entfernung. */
	private Double entfernung;
	
	/**
	 * Instantiates a new calc wg s84.
	 */
	public CalcWGS84(){
	}
	
	/**
	 * Instantiates a new calc wg s84.
	 *
	 * @param sBreiteGrad the s breite grad
	 * @param sBreiteMinute the s breite minute
	 * @param sLaengeGrad the s laenge grad
	 * @param sLaengeMinute the s laenge minute
	 * @param sRichtung the s richtung
	 * @param sEntfernung the s entfernung
	 */
	public CalcWGS84(int sBreiteGrad, float sBreiteMinute, int sLaengeGrad,
			float sLaengeMinute, float sRichtung, float sEntfernung) {
		calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute,
				sRichtung, sEntfernung);
	}
	
	/**
	 * Instantiates a new calc wg s84.
	 *
	 * @param sBreiteGrad the s breite grad
	 * @param sBreiteMinute the s breite minute
	 * @param sLaengeGrad the s laenge grad
	 * @param sLaengeMinute the s laenge minute
	 * @param sEntfernung the s entfernung
	 */
	public CalcWGS84(int sBreiteGrad, double sBreiteMinute, int sLaengeGrad,
			double sLaengeMinute, double sEntfernung) {
		//calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sEntfernung);
		breiteGrad = sBreiteGrad;
		breiteMinute = sBreiteMinute;
		laengeGrad = sLaengeGrad;
		laengeMinute = sLaengeMinute;
		entfernung = sEntfernung;
	}

	/**
	 * Dec breite.
	 *
	 * @param sBreiteGrad the s breite grad
	 * @param sBreiteMinute the s breite minute
	 * @return the float
	 */
	private static float decBreite(int sBreiteGrad, double sBreiteMinute) {
		return (float) (Math.PI / 180 * (sBreiteGrad + (sBreiteMinute / 60)));
	}

	/**
	 * Dec laenge.
	 *
	 * @param sLaengeGrad the s laenge grad
	 * @param sLaengeMinute the s laenge minute
	 * @return the float
	 */
	private static float decLaenge(int sLaengeGrad, double sLaengeMinute) {
		return (float) (Math.PI / 180 * (sLaengeGrad + (sLaengeMinute / 60)));
	}

	/**
	 * Dec richtung.
	 *
	 * @param sRichtung the lon
	 * @return the float
	 */
	private static float decRichtung(double sRichtung) {
		return (float) (Math.PI / 180 * sRichtung);
	}

	/**
	 * Dec entfernung.
	 *
	 * @param sEntfernung the s entfernung
	 * @return the float
	 */
	private static float decEntfernung(double sEntfernung) {
		return (float) ((Math.PI / (180 * 60)) * sEntfernung / 1.852);
	}
	
	/**
	 * Calc new coords.
	 *
	 * @param sRichtung the s richtung
	 */
	public void calcNewCoords(double sRichtung){
		calcNewCoords(breiteGrad, breiteMinute, laengeGrad, laengeMinute, sRichtung, entfernung);
	}

	/**
	 * Calc new coords.
	 *
	 * @param sBreiteGrad the s breite grad
	 * @param sBreiteMinute the s breite minute
	 * @param sLaengeGrad the s laenge grad
	 * @param sLaengeMinute the s laenge minute
	 * @param sRichtung the s richtung
	 * @param sEntfernung the s entfernung
	 */
	public void calcNewCoords(int sBreiteGrad, double sBreiteMinute,
			int sLaengeGrad, double sLaengeMinute, double sRichtung,
			double sEntfernung) {

		float lat = (float) Math.asin(Math.sin(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.cos(decEntfernung(sEntfernung))
				+ Math.cos(decBreite(sBreiteGrad, sBreiteMinute))
				* Math.sin(decEntfernung(sEntfernung))
				* Math.cos(decRichtung(sRichtung)));
		// Gegenüber Excel sind x und y in der Methode atan2 vertauscht
		float dlon = (float) (Math.atan2(
				Math.sin(decRichtung(sRichtung))
						* Math.sin(decEntfernung(sEntfernung))
						* Math.cos(decBreite(sBreiteGrad, sBreiteMinute)),
				Math.cos(decEntfernung(sEntfernung))
						- Math.sin(decBreite(sBreiteGrad, sBreiteMinute))
						* Math.sin(lat))
				* -1);
		double pLon = (decLaenge(sLaengeGrad, sLaengeMinute) - dlon + Math.PI);
		Double pLonn =  (pLon / 2 / Math.PI);

		double lon = (pLon - pLonn.intValue() - Math.PI);
		//System.out.println("Latidude: " + lat);
		//System.out.println("dlon: " + dlon);
		//System.out.println("lon: " + lon);

		Double tmpNeueBreiteGrad = 180 / Math.PI * lat;
		breiteGrad = tmpNeueBreiteGrad.intValue();
		breiteMinute = (tmpNeueBreiteGrad - breiteGrad) * 60;
		//System.out.println("Neue Breite: " + breiteGrad + "° " + breiteMinute);

		Double tmpNeueLaengeGrad = 180.0f / Math.PI * lon;
		//float tmpNeueLaengeGrad = decRichtung(lon);
		laengeGrad = tmpNeueLaengeGrad.intValue();
		laengeMinute = (tmpNeueLaengeGrad - laengeGrad) * 60;
		//System.out.println("Neue Länge: " + laengeGrad + "° " + laengeMinute);
	}
	
	/**
	 * Gets the breite.
	 *
	 * @return the breite
	 */
	public String getLaenge(){
		return laengeGrad + "° " + laengeMinute;
	}
	
	/**
	 * Gets the laenge.
	 *
	 * @return the laenge
	 */
	public String getBreite(){
		return breiteGrad + "° " + breiteMinute;
	}
	public String getBreiteDezimalGrad(){
		Double breiteMinuteFloat;
		breiteMinuteFloat = breiteMinute/60.0f;
		return breiteGrad + "," + breiteMinuteFloat.toString().substring(2);
	}
	public String getLaengeDezimalGrad(){
		Double laengeMinuteFloat;
		laengeMinuteFloat = laengeMinute/60.0f;
		return laengeGrad + "," + laengeMinuteFloat.toString().substring(2);
	}
}
