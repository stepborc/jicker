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
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//ArrayList<Long> dividend = new Dividend().getDividend();
		Dividend d = new Dividend();
		ArrayList<Long> ld = d.getDividend();
		for(int n = 0; n<ld.size();n++){
			System.out.println(ld.get(n));

		}
	}
	public ArrayList<Long> getDividend(){
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
							right = (y * 100000) + (x * 10000) + (w * 1000) + (v * 100) + (r * 10) + 3;
							left = (3 * 100000) + (r * 10000) + (v * 1000) + (w * 100) + (x * 10) + (y);
							result = Long.valueOf(right * left);

							String stringResult = result.toString().substring(2, 3);
							int laenge = result.toString().length();
							if(stringResult.equals("3") & laenge == 12){
								//System.out.println(stringResult + "||"+ "3" + r + v + w +x+ y +"*"+y+x+w+v+r+"3"+" || "+left + " * " + right + " = " + result);
								d.add(result);
							}
						}
					}
				}
			}
		}
		return d;
	}
}
