package org.jicker.gcm9mm;

public class ConvertBin2Ascii {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String binText = "00110101 00110011 00100000 00110111 00110101 00100000 00110111 00110000 00100000 00110110 00110101 00100000 00110111 00110010 00100000 00110010 01100011 00100000 00110010 00110000 00100000 00110100 00111001 00100000 00110110 00111000 00100000 00110111 00110010 00100000 00110010 00110000 00100000 00110110 00111000 00100000 00110110 00110001 00100000 00110110 00110010 00100000 00110111 00110100 00100000 00110010 00110000 00100000 00110110 00110101 00100000 00110111 00110011 00100000 00110010 00110000 00100000 00110110 00110111 00100000 00110110 00110101 00100000 00110111 00110011 00100000 00110110 00110011 00100000 00110110 00111000 00100000 00110110 00110001 00100000 00110110 00110110 00100000 00110110 00110110 00100000 00110111 00110100 00100000 00110010 00110001 00100000 00110010 00110000 00100000 00110100 00110100 00100000 00110110 00111001 00100000 00110110 00110101 00100000 00110010 00110000 00100000 00110110 00110101 00100000 00110111 00110010 00100000 00110111 00110011 00100000 00110111 00110100 00100000 00110110 00110101 00100000 00110010 00110000 00100000 00110101 00110011 00100000 00110111 00110100 00100000 00110110 00110001 00100000 00110111 00110100 00100000 00110110 00111001 00100000 00110110 01100110 00100000 00110110 01100101 00100000 00110010 00110000 00100000 00110110 00110110 00100000 00110110 00111001 00100000 00110110 01100101 00100000 00110110 00110100 00100000 00110110 00110101 00100000 00110111 00110100 00100000 00110010 00110000 00100000 00110100 00111001 00100000 00110110 00111000 00100000 00110111 00110010 00100000 00110010 00110000 00100000 00110110 00110010 00100000 00110110 00110101 00100000 00110110 00111001 00100000 00110010 00110000 00100000 00110100 01100101 00100000 00110011 00110101 00100000 00110011 00110001 00100000 01100010 00110000 00100000 00110010 00110000 00100000 00110011 00110010 00100000 00110011 00110011 00100000 00110010 01100011 00100000 00110011 00111001 00100000 00110011 00111001 00100000 00110011 00110010 00100000 00110010 00110000 00100000 00110100 00110101 00100000 00110011 00110110 00100000 01100010 00110000 00100000 00110010 00110000 00100000 00110011 00110101 00100000 00110011 00110110 00100000 00110010 01100011 00100000 00110011 00110110 00100000 00110011 00110101 00100000 00110011 00110010 00100000 00110010 01100101 00100000 00110010 00110000 00100000 00110101 00110000 00100000 00110110 00110001 00100000 00110111 00110010 00100000 00110110 01100010 00100000 00110110 00110101 00100000 00110110 01100101 00100000 00110010 00110000 00100000 00110110 01100010 00100000 01100110 00110110 00100000 00110110 01100101 00100000 00110110 01100101 00100000 00110111 00110100 00100000 00110010 00110000 00100000 00110100 00111001 00100000 00110110 00111000 00100000 00110111 00110010 00100000 00110010 00110000 00100000 00110110 00110010 00100000 00110110 00110101 00100000 00110110 00111001 00100000 00110010 00110000 00100000 00110100 01100101 00100000 00110011 00110101 00100000 00110011 00110001 00100000 01100010 00110000 00100000 00110010 00110000 00100000 00110011 00110010 00100000 00110011 00110100 00100000 00110010 01100011 00100000 00110011 00110000 00100000 00110011 00111000 00100000 00110011 00110010 00100000 00110010 00110000 00100000 00110100 00110101 00100000 00110011 00110110 00100000 01100010 00110000 00100000 00110010 00110000 00100000 00110011 00110101 00100000 00110011 00110110 00100000 00110010 01100011 00100000 00110011 00111001 00100000 00110011 00110110 00100000 00110011 00111000 00100000 00110010 01100101 00100000 00110010 00110000 00100000 00110101 00110110 00100000 00110110 00111001 00100000 00110110 00110101 00100000 00110110 01100011 00100000 00110010 00110000 00100000 00110101 00110011 00100000 00110111 00110000 00100000 00110110 00110001 00100000 01100100 01100110 00100000 00110010 00110001";
        String dezText = "";
        String hexText = "";
        String resultText = "";
        int value = 0;
        int i = 0;

        while (i < binText.length()) {
            dezText = dezText
                    + String.valueOf(Integer.parseInt(
                            binText.substring(i, i + 8), 2)
                            + " ");
            i = i + 9;
        }
        i = 0;
        while (i < dezText.length()) {
            value = (Integer.valueOf(dezText.substring(i,
                    dezText.indexOf(" ", i))));
            char c = (char) value;
            if (c != ' ') {
                hexText = hexText + c;
            }
            i = i + (dezText.substring(i, dezText.indexOf(" ", i))).length()
                    + 1;
        }
        i = 0;
        while (i < hexText.length()) {
            // hex = hexText.substring(i, i + 2);
            char c = (char) Integer.parseInt(hexText.substring(i, i + 2), 16);
            resultText = resultText + c;
            i = i + 2;
        }
        System.out.println(resultText);
    }
}
