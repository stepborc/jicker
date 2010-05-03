package org.jicker.gc1r3r6;

public class Multiplikation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Eine Multiplikation ausführen, die nach dem Schritt für Schritt Prinzip abläuft.
		// Voraussetzung:
		// BDFGI*KLMPQ
		// -----------
		//   ....0
		//    ..0.0
		//    101..0
		//     11...0
		//      1....0
		//   ----------
		//   ..........
		//Erste Annahme:  I = 0 weil jeweils die letzte Stelle der Zwischenergebnisse 0 ist.
		//				  Keine der Variablen kann 0 sein
		//Vorgehensweise: Der Multiplikator wird fest gelegt, und mit dem Multiplikant multipliziert.
		//                Die Zwischenergebnisse werden auf die Übereinstimmung mit den Mustern geprüft.

Long multiplikator = null;
		for(int b=1;b<=9;b++){
			multiplikator = Long.valueOf(b);
			for(int d = 1;d<=9;d++){
				multiplikator = Long.valueOf(multiplikator.toString() + d);
				for(int f = 1;f<=9;f++){
					for(int g = 1;g<=9;g++){
						if(d != b && (f!=b & f!=d) && (g!=b & g!=d & g!=f) ){
							multiplikator = Long.valueOf((b*10000) + (d*1000) + (f*100) + (g*10));
							//System.out.println(multiplikator);
							//Long multiplikator = Long.valueOf(strMultiplikator);
							for (int x1=1;x1<=9;x1++){
								if(x1!=b || x1!=d || x1!=f||x1!=g){
									Long m5 = Long.valueOf(multiplikator*x1);
									//System.out.println(multiplikator + "*"+ x +"=" + (multiplikator*x));
									if(m5.toString().substring(0, 1).equals("1") & m5.toString().length()==6){
										//System.out.println(m5 + " - " + x1);
										for(int x2 = 1;x2<=9;x2++){
											if(x2!=x1){
												Long m4 = Long.valueOf(multiplikator * x2);
												if(m4.toString().length()==6 & m4.toString().substring(0, 2).equals("11") & m4.toString().substring(2, 5).indexOf("1")==0){
													System.out.println("\t" + m4);
													for(int x3= 1;x3<=9;x3++){
														if(x3!=x1 & x3!=x2){
															Long m3 = Long.valueOf(multiplikator*x3);
															System.out.println(m3);
														}
													}

												}
											}
										}
									}
								}
							}

						}
					}
				}
			}
			multiplikator = null;
		}

	}

}
