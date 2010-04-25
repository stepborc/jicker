package org.jicker.gc1r3r6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

public class Divisor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long left;
		long right;
		Long result = null;
		ArrayList divisor = new ArrayList<String>();
		for (int r = 0; r <= 9; r++) {
			for (int v = 0; v <= 9; v++) {
				for (int w = 0; w <= 9; w++) {
					for (int x = 0; x <= 9; x++) {
						for (int y = 0; y <= 9; y++) {
							right = (y * 100000) + (x * 10000) + (w * 1000)
									+ (v * 100) + (r * 10) + 3;
							Long lRight = null;
							for (int n = 1; n <= 9; n++) {
								lRight = lRight.valueOf(right * n);
								if (lRight.toString().length() == 7) {
									if (lRight.toString().substring(2, 3)
											.equals("3")) {
										divisor.add(lRight);
									}
								}
							}
						}
					}
				}
			}
		}
		Collections.sort(divisor);
		for (int index = 0; index < divisor.size(); index++)
			System.out.println(divisor.get(index));
	}
}
