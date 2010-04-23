package org.jicker;

import java.math.BigInteger;

public class Division {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Multipliziere 2 Zahlen, die Spiegelungsgleich sind
		// 3RVWXY * YXWVR3
		// Ergebnis muss eine Zahl sein, die 12stellig ist und
		// die 3. Stelle von Links muss 3 sein
		// ..3.........
		int n = 0;
		long left;
		long right;
		Long result = null;
		for (int r = 0; r <= 9; r++) {

			for (int v = 0; v <= 9; v++) {

				for (int w = 0; w <= 9; w++) {

					for (int x = 0; x <= 9; x++) {

						for (int y = 0; y <= 9; y++) {

							right = (y * 100000) + (x * 10000) + (w * 1000) + (v * 100) + (r * 10) + 3;
							left = (3 * 100000) + (r * 10000) + (v * 1000) + (w * 100) + (x * 10) + (y);
							result = result.valueOf(right * left);
//							System.out.println(left + "*" + right + "="	+ result);
							String stringResult = result.toString().substring(2, 3);
							int laenge = result.toString().length();
							//int n = 0;
							if(stringResult.equals("3") & laenge == 12){

							System.out.println(stringResult + "||"+ "3" + r + v + w +x+ y +"*"+y+x+w+v+r+"3"+" || "+left + " * " + right + " = " + result);
							n++;}

						}
					}
				}

			}

		}
		System.out.println(n);
	}

}
