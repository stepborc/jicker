package org.jicker.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class NestingHandler extends DefaultHandler {

	// Zähler für Schachtelungstiefe
	int nestingLevel = 0;
	// Zähler für Anzahl Caches
	int cacheCount = 0;

	public void startDocument() {
	}

	public void endDocument() {
		System.out.println("Anzahl der Caches: " + cacheCount);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		// Anzahl der Caches zählen
		if (qName.equals("wpt")) {
			cacheCount++;
		}
		// Am Anfang eines Elements die Ausgabe einrücken
		for (int i = 0; i < nestingLevel * 2; i++) {
			System.out.print(' ');
		}

		System.out.println(nestingLevel + "_o: " + qName + " -> "
				+ attributes.getValue(0));
		// Den nestingLevel hochzählen
		nestingLevel++;

	}

	public void endElement(String uri, String localName, String qName) {
		for (int i = 0; i < nestingLevel * 2 - 2; i++) {
			System.out.print(' ');
		}
		nestingLevel--;
		System.out.println(nestingLevel + "_c: " + qName);

	}

	public void characters(char[] ch, int start, int length) {
		for (int i = start; i < (start + length); i++)
			System.out.print(ch);

		System.out.println();
	}

}
