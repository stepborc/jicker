package org.jicker.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.lobobrowser.html.*;
import org.lobobrowser.html.gui.*;
import org.lobobrowser.html.parser.*;
import org.lobobrowser.html.test.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.awt.*;

/**
 * Minimal rendering example: google.com.
 */
public class BarebonesTest {
	public static void main(String[] args) throws Exception {
	//public BarebonesTest(String uri) throws IOException, SAXException{
		String uri = "http://www.flickr.com:80/services/auth?api_key=6fe409e0413a2a5e03d54c30ca6a27c4&frob=72157603781017414-bc1c84152ca76055-310345&perms=read&api_sig=44abae89be9fe6079e06d6c2bcd712cd";
		URL url = new URL(uri);
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		// A Reader should be created with the correct charset,
		// which may be obtained from the Content-Type header
		// of an HTTP response.
		Reader reader = new InputStreamReader(in);

		// InputSourceImpl constructor with URI recommended
		// so the renderer can resolve page component URLs.
		InputSource is = new InputSourceImpl(reader, uri);
		HtmlPanel htmlPanel = new HtmlPanel();
		HtmlRendererContext rendererContext = new LocalHtmlRendererContext(htmlPanel);
		
		// Set a preferred width for the HtmlPanel,
		// which will allow getPreferredSize() to
		// be calculated according to block content.
		// We do this here to illustrate the 
		// feature, but is generally not
		// recommended for performance reasons.
		htmlPanel.setPreferredWidth(800);
		
		// This example does not perform incremental
		// rendering. 
		DocumentBuilderImpl builder = new DocumentBuilderImpl(rendererContext.getUserAgentContext(), rendererContext);
		Document document = builder.parse(is);
		in.close();

		// Set the document in the HtmlPanel. This
		// is what lets the document render.
		htmlPanel.setDocument(document, rendererContext);

		// Create a JFrame and add the HtmlPanel to it.
		final JFrame frame = new JFrame();
		frame.getContentPane().add(htmlPanel);
		
		// We pack the JFrame to demonstrate the
		// validity of HtmlPanel's preferred size.
		// Normally you would want to set a specific
		// JFrame size instead.
		
		// This should be done in the GUI dispatch
		// thread since the document is scheduled to
		// be rendered in that thread.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	private static class LocalHtmlRendererContext extends SimpleHtmlRendererContext {
		// Override methods here to implement browser functionality
		public LocalHtmlRendererContext(HtmlPanel contextComponent) {
			super(contextComponent);
		}
	}
}
