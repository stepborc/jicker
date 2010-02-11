package org.jicker.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class NestingHandler extends DefaultHandler {
	// Zähler für Schachtelungstiefe
	int nestingLevel = 0;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		// Am Anfang eines Elements die Ausgabe einrücken
		for (int i = 0; i < nestingLevel * 2; i++) {
			System.out.print(' ');
		}

		System.out.println(nestingLevel + "_o: " + qName);
		// Den nestingLevel hochzählen
		nestingLevel++;

	}

	public void endElement(String uri, String localName, String qName) {
		System.out.println(nestingLevel + "_c: " + qName);
		nestingLevel--;
	}

}
