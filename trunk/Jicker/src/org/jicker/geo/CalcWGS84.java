package org.jicker.geo;

public class CalcWGS84 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Start");
        int sBreiteGrad = 51;
        double sBreiteMinute = 23.260;
        int sLaengeGrad = 6;
        double sLaengeMinute = 48.372;
        int sRichtung = 90;
        double sEntfernung = 0.180;

        calcNewCoords(sBreiteGrad, sBreiteMinute, sLaengeGrad, sLaengeMinute, sRichtung, sEntfernung);

    }

    private static void calcNewCoords(int sBreiteGrad, double sBreiteMinute, int sLaengeGrad, double sLaengeMinute, int sRichtung, double sEntfernung) {
        double tmpBreite =  Math.PI/180*(sBreiteGrad+(sBreiteMinute/60));
        double tmpLaenge = Math.PI/180*(sLaengeGrad+(sLaengeMinute/60));
        double tmpRichtung = Math.PI/180*sRichtung;
        double tmpEntfernung = (Math.PI/(180*60))*sEntfernung/1.852;
        System.out.println("Breite: " + tmpBreite);
        System.out.println("Länge: " + tmpLaenge);
        System.out.println("Richtung: " + tmpRichtung);
        System.out.println("Entfernung: "+tmpEntfernung);

        Double lat = Math.asin(Math.sin(tmpBreite)*Math.cos(tmpEntfernung)+Math.cos(tmpBreite)*Math.sin(tmpEntfernung)*Math.cos(tmpRichtung));

        System.out.println("Latidude: " + lat);



    }

}
