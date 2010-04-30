/*
 *
 */
package org.jicker.gc1r3r6;

import java.math.BigInteger;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Dividend.
 */
public class Dividend {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// ArrayList<Long> dividend = new Dividend().getDividend();
		Dividend d = new Dividend();
		ArrayList<Long> ld = d.getDividend();
		for (int n = 0; n < ld.size(); n++) {
			// System.out.println(ld.get(n));

		}
	}

	public ArrayList<Long> getDividend() {
		// Multipliziere 2 Zahlen, die Spiegelungsgleich sind
		// 3RVWXY * YXWVR3
		// Ergebnis muss eine Zahl sein, die 12stellig ist und
		// die 3. Stelle von Links muss 3 sein
		// ..3.........
		long left;
		long right;
		Long result = null;
		ArrayList<Long> d = new ArrayList<Long>();
		for (int r = 0; r <= 9; r++) {
			for (int v = 0; v <= 9; v++) {
				for (int w = 0; w <= 9; w++) {
					for (int x = 0; x <= 9; x++) {
						for (int y = 0; y <= 9; y++) {
							right = (y * 100000) + (x * 10000) + (w * 1000)
									+ (v * 100) + (r * 10) + 3;
							left = (3 * 100000) + (r * 10000) + (v * 1000)
									+ (w * 100) + (x * 10) + (y);
							result = Long.valueOf(right * left);

							String stringResult = result.toString().substring(
									2, 3);
							int laenge = result.toString().length();
							if (stringResult.equals("3") & laenge == 12) {
								/*
								 * System.out.println("3" + r + v + w + x + y +
								 * "*" + y + x + w + v + r + "3" + " || " + left
								 * + " * " + right + " = " + result);
								 */
								d.add(result);
								Long mLeft;
								for (int m = 0; m <= 9; m++) {
									mLeft = left * m;
									if (mLeft.toString().length() == 7) {
										if (mLeft.toString().substring(2, 3)
												.equals("3")) {
											// System.out.println(mLeft.toString().substring(2,
											// 3) + "||" + mLeft);
											Long sResult = Long
													.valueOf(result.toString()
															.substring(0, 7));
											Long zResult = Long.valueOf(sResult
													- mLeft);
											if (zResult > 0
													& zResult.toString()
															.length() == 6) {
												// System.out.println(sResult - mLeft);
												if (zResult.toString().indexOf("3") == 5) {
													System.out.println(result + " : " + left + " = " + right + " || " + sResult + "-" + mLeft + " = " + (sResult - mLeft) + " || " + m);
													for (int mm = 0; mm <= 9; mm++) {
														Long step3 = Long
																.valueOf((sResult - mLeft));
														String strStep3 = step3
																.toString()
																+ m;
														step3 = Long
																.valueOf(strStep3);
														for (int mmm = 0; mmm <= 9; mmm++) {
															Long mmmLeft = Long
																	.valueOf(left
																			* mmm);
															if (mmmLeft
																	.toString()
																	.length() == 6) {
																Long step4 = Long
																		.valueOf(step3
																				- mmmLeft);
																if (step4
																		.toString()
																		.length() == 6) {
																	if (step4.toString().substring(0,1).equals("3")) {
																		System.out.println(step3 + "-" + mmmLeft + "=" + step4);
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
		return d;
	}
}
