package org.jicker.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;

public class Test {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		// Parser parser = new Parser();

		parser.parse(new File("C:/tmp/4138906/test.gpx"),
				new NestingHandler());
		// parser.parse(Resolver.createInputSource(new java.io.File(file)));

	}
}