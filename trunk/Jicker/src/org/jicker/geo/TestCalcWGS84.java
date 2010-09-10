package org.jicker.geo;

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
        for(double sRichtung = 0.0; sRichtung<360.0;sRichtung++){
        c.calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sRichtung, sEntfernung);
		System.out.println(sRichtung +"°: " +c.getLaenge() + " - " + c.getBreite());
        }

	}

}
