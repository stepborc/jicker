package org.jicker.gc1r3r6;

public class M2 {

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
							for (int l=1;l<=9;l++){
								if(l!=b || l!=d || l!=f||l!=g||l!=0){
									Long z2 = Long.valueOf(multiplikator*l);
									//System.out.println(multiplikator + "*"+ x2 +"=" + (multiplikator*x2));
									if( z2.toString().length()==5 & z2.toString().substring(2, 3).equals("0") & z2.toString().substring(4, 5).equals("0")){
										//System.out.println(z2 + " - " + l+":" + l);
										for(int m = 1;m<=9;m++){
											if(m!=l){
												Long z3 = Long.valueOf(multiplikator * m);
												if(z3.toString().length()==6 & z3.toString().substring(0, 3).equals("101")){
													//System.out.println("\t" + z3+":" + l + " " + m);
													for(int p= 1;p<=9;p++){
														if(p!=m & p!=m){
															Long z4 = Long.valueOf(multiplikator*p);
															if(z4.toString().length()==6 & z4.toString().substring(0, 2).equals("11")){
															//System.out.println("\t\t"+z4+":" + l + " " + m + " " + p);
															for(int q=1;q<=9;q++){
																if(q!=p & q!=m & q!=l){
																	Long z5 = Long.valueOf(multiplikator*q);
																	if(z5.toString().length()==6 & z5.toString().substring(0, 1).equals("1")){
																		System.out.println("\t\t\t" +z5 + " -> " +"B="+b+" D="+d+" F="+f+" G="+g+" I=0 "+ "K=? L="+l+" M="+m+" P="+p+" Q=" +q);
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

						}
					}
				}
			}
			multiplikator = null;
		}

	}

}