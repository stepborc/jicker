package org.jicker.gcm9mm;

public class EncodeHexString {
	public static String encodeHexString(String sourceText) {

		Byte[] rawData = sourceText.getBytes();
		StringBuffer hexText= new StringBuffer();
		String initialHex = null;
		int initHexLength=0;

		for(int i=0; i<rawData.length; i++) {
		int positiveValue = rawData[i] & 0x000000FF;
		initialHex = Integer.toHexString(positiveValue);
		initHexLength=initialHex.length();
		while(initHexLength++ < 2) {
		hexText.append("0");
		}
		hexText.append(initialHex);
		}
		return hexText.toString();
		}
		public static String decodeHexString(String hexText) {

		String decodedText=null;
		String chunk=null;

		if(hexText!=null && hexText.length()>0) {
		int numBytes = hexTextlength()/2;

		byte[] rawToByte = new byte[numBytes];
		int offset=0;
		int bCounter=0;
		for(int i =0; i <numBytes; i++) {
		chunk = hexText.substring(offset,offset+2);
		offset+=2;
		rawToByte[i] = (byte) (Integer.parseInt(chunk,16) & 0x000000FF);
		}
		decodedText= new String(rawToByte);
		}
		return decodedText;
		}
}
