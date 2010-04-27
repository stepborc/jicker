/*
 *
 */
package org.jicker.gc1r3r6;

import java.util.ArrayList;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class Divisor.
 */

public class ValueOfQuotient {
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ArrayList<Long> d = new ValueOfQuotient().voq();
		for (int index = 0; index < d.size(); index++)
			System.out.println(d.get(index));
		System.out.println(d.size());

	}

	/**
	 * Der Divisor kann die Form yxwvr3 annehmen
	 * Erstelle eine ArrayList<Long> mit allen möglichen Werten,
	 * die an der 3 Position von links eine 3 haben und .
	 *
	 * @return the array list
	 */
	public ArrayList<Long> voq() {
		long right;
		ArrayList<Long> voq = new ArrayList<Long>();
		for (int r = 0; r <= 9; r++) {
			for (int v = 0; v <= 9; v++) {
				for (int w = 0; w <= 9; w++) {
					for (int x = 0; x <= 9; x++) {
						for (int y = 0; y <= 9; y++) {
							right = (y * 100000) + (x * 10000) + (w * 1000)
									+ (v * 100) + (r * 10) + 3;
							Long lRight = null;
							for (int n = 2; n <= 9; n++) {
								lRight = Long.valueOf(right * n);
								if (lRight.toString().length() == 7) {
									if (lRight.toString().substring(2, 3)
											.equals("3")) {
										voq.add(lRight);
									}
								}
							}
						}
					}
				}
			}
		}
		Collections.sort(voq);
		return voq;
	}
}
