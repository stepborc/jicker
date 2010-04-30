/*
 *
 */
package org.jicker.gc1r3r6;

import java.math.BigInteger;
import java.util.ArrayList;

import sun.awt.SubRegionShowable;

// TODO: Auto-generated Javadoc
/**
 * The Class Dividend.
 */
public class AltOfDividend {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// ArrayList<Long> dividend = new Dividend().getDividend();
		AltOfDividend d = new AltOfDividend();
		ArrayList<Long> ld = d.getDividend();
		for (int n = 0; n < ld.size(); n++) {
			//System.out.println(ld.get(n));
		}
		System.out.println(ld.size());
	}

	public ArrayList<Long> getDividend() {
		// Multipliziere 2 Zahlen, die Spiegelungsgleich sind
		// 3RVWXY * YXWVR3
		// Ergebnis muss eine Zahl sein, die 12stellig ist und
		// die 3. Stelle von Links muss 3 sein
		// ..3.........
		System.out.println("Start");
		long left;
		long right;
		Long result = null;
		ArrayList<Long> d = new ArrayList<Long>();
		for (int r = 0; r <= 9; r++) {
			for (int v = 0; v <= 9; v++) {
				if(v != r & v!= 3){
				for (int w = 0; w <= 9; w++) {
					if(w!=r & w!=v&w!=3){
					for (int x = 0; x <= 9; x++) {
						if(x!=r & x!= v & x != w&x!=3){
						for (int y = 0; y <= 9; y++) {
							if(y!=r & y!=v & y!=w & y!=x & y!=3){
							right = (y * 100000) + (x * 10000) + (w * 1000)
									+ (v * 100) + (r * 10) + 3;
							left = (3 * 100000) + (r * 10000) + (v * 1000)
									+ (w * 100) + (x * 10) + (y);
							result = Long.valueOf(right * left);
							if(result.toString().length()==12){
								if (result.toString().indexOf("3")==2){
									if ((result.toString().substring(3, 11)).toString().indexOf("3") == -1){
										//Alle Werte result erfüllen die Regeln:
										// 12stellig
										// 3. Stelle ist = 3
										//System.out.println(left + " * " + right + " = " + result);
										Long partResult = Long.valueOf(result.toString().substring(0,7));
										//System.out.println(partResult);
										for (int nn = 1;nn<=9;nn++){
											Long vLeft = Long.valueOf(left*nn);
											//System.out.println(vLeft);
											if(vLeft.toString().length()==7 & vLeft.toString().indexOf("3")==2 & vLeft.toString().substring(3, 6).indexOf("3") == -1){
												Long subPartResult = Long.valueOf(partResult - vLeft);
												if (subPartResult > 0 & subPartResult.toString().length()==6){
													//System.out.println(subPartResult);
													if(subPartResult.toString().indexOf("3")==5){
														System.out.println(partResult +"-" + vLeft +"="+ (subPartResult));
													}
											}
											}
																					}
										if (partResult>= 0 & partResult.toString().indexOf("3") == 5){
											//System.out.println(partResult);
											d.add(result);
										}
									}
								}
							}
						}
					}}
				}}
			}}
		}}
		System.out.println("Ende");
		return d;
	}
}
