package org.jicker.gc1r3r6;

public class Addition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Addition folgender Formel
		//  CACHEN
		// + SUCHE
		// -------
		// SCHATZ
		// Jeder Buchstabe stellt eine Zahl dar
		// Gleiche Buchstaben, gleiche Zahl
		// C und S sind ungleich 0
		// Folgende Buchstaben kommen in der Addition vor: A C E H N S U
		// Folgende Buchstaben sind zusätzlich im Ergebnsi vorhanden: T Z
		//Variable A hochzählen
		for (int a = 0; a <= 9; a++) {
			//Variable C hochzählen, wobei C nicht 0 ist
			for (int c = 1; c <= 9; c++) {
				//C darh nicht A sein
				if (c != a) {
					//Variable E hochzählen
					for (int e = 0; e <= 9; e++) {
						// e darf nicht A bzw C sein
						if (e != a & e != c) {
							//Variable H hochzählen
							for (int h = 0; h <= 9; h++) {
								//H darh nicht A bzw. C bzw. E sein
								if (h != a & h != c & h != e) {
									//Variable N hochzäheln 
									for (int n = 0; n <= 9; n++) {
										//N darh nicht A bzw. C bzw. E bzw. H
										if (n != a & n != c & n != e & n != h) {
											//Variable S hochzählen, S darh nicht 0 sein
											for (int s = 1; s <= 9; s++) {
												// S darf nicht A bzw. C bzw. E bzw. H bzw. N sein
												if (s != a & s != c & s != e & s != h & s != n) {
													//Variable U hochzählen
													for (int u = 0; u <= 9; u++) {
														//U darh nicht A bzw. C bzw. E bzw. H bzw. N bzw. S sein
														if (u != a & u != c	& u != e & u != h & u != n & u != s) {
															//Wenn alle Stellen (Variablen) belegt, einen Long-Wert bilden
															//Dazu das Wrapper-Objekt 
															Long fSummand = Long.valueOf((c * 100000) + (a * 10000) + (c * 1000) + (h * 100) + (e * 10) + n);
															Long sSummand = Long.valueOf((s * 10000) + (u * 1000) + (c * 100) + (h * 10) + e);
															Long summe = Long.valueOf(fSummand + sSummand);
															if ((Long.valueOf(summe.toString().substring(0,1)) == s)
																	& (Long.valueOf(summe.toString().substring(1,2)) == c)
																	& (Long.valueOf(summe.toString().substring(2,3)) == h)
																	& (Long.valueOf(summe.toString().substring(3,4)) == a)) {
																if ((Long.valueOf(summe.toString().substring(4,5)) != a)
																		& (Long.valueOf(summe.toString().substring(4,5)) != c)
																		& (Long.valueOf(summe.toString().substring(4,5)) != e)
																		& (Long.valueOf(summe.toString().substring(4,5)) != h)
																		& (Long.valueOf(summe.toString().substring(4,5)) != n)
																		& (Long.valueOf(summe.toString().substring(4,5)) != s)
																		& (Long.valueOf(summe.toString().substring(4,5)) != u)
																		& (Long.valueOf(summe.toString().substring(5,6)) != a)
																		& (Long.valueOf(summe.toString().substring(5,6)) != c)
																		& (Long.valueOf(summe.toString().substring(5,6)) != e)
																		& (Long.valueOf(summe.toString().substring(5,6)) != h)
																		& (Long.valueOf(summe.toString().substring(5,6)) != n)
																		& (Long.valueOf(summe.toString().substring(5,6)) != s)
																		& (Long.valueOf(summe.toString().substring(5,6)) != u)) {
																	if ((Long.valueOf(summe.toString().substring(5,6)) != (Long.valueOf(summe.toString().substring(4,5))))) {
																		System.out.println(fSummand.toString() + "+" 
																				+ sSummand.toString() + "=" 
																				+ summe 
																				+ " --> A=" + a + " C=" + c + " E=" + e + " H=" + h + " N=" + n + " S=" + s + " U=" + u + " T=" 
																				+ summe.toString().substring(4,5) + " Z=" 
																				+ summe.toString().substring(5,6));
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
	}
}
