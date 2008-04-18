/*
 * 
 */
package org.jicker.util.cobra;

import java.awt.EventQueue;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;

import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.parser.InputSourceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.html2.HTMLElement;
import org.xml.sax.InputSource;

// TODO: Auto-generated Javadoc
/**
 * Minimal rendering example: google.com.
 */
public class BarebonesTest {
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		String uri = "http://google.com";
		//String uri = "file:C:\\opt\\XAMJ_Project\\HTML_Renderer\\testing\\table2.html";
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
		UserAgentContext ucontext = new LocalUserAgentContext();
		HtmlRendererContext rendererContext = new LocalHtmlRendererContext(htmlPanel, ucontext);
		
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

	/**
	 * The Class LocalUserAgentContext.
	 */
	private static class LocalUserAgentContext extends SimpleUserAgentContext {
		// Override methods from SimpleUserAgentContext to
		// provide more accurate information about application.
		
		/**
		 * Instantiates a new local user agent context.
		 */
		public LocalUserAgentContext() {
		}

		/* (non-Javadoc)
		 * @see org.jicker.util.cobra.SimpleUserAgentContext#getAppMinorVersion()
		 */
		public String getAppMinorVersion() {
			return "0";
		}

		/* (non-Javadoc)
		 * @see org.jicker.util.cobra.SimpleUserAgentContext#getAppName()
		 */
		public String getAppName() {
			return "BarebonesTest";
		}

		/* (non-Javadoc)
		 * @see org.jicker.util.cobra.SimpleUserAgentContext#getAppVersion()
		 */
		public String getAppVersion() {
			return "1";
		}

		/* (non-Javadoc)
		 * @see org.jicker.util.cobra.SimpleUserAgentContext#getUserAgent()
		 */
		public String getUserAgent() {
			return "Mozilla/4.0 (compatible;)";
		}
	}

	/**
	 * The Class LocalHtmlRendererContext.
	 */
	private static class LocalHtmlRendererContext extends SimpleHtmlRendererContext {
		// Override methods from SimpleHtmlRendererContext to provide
		// browser functionality to the renderer.
		
		/**
		 * Instantiates a new local html renderer context.
		 * 
		 * @param contextComponent the context component
		 * @param ucontext the ucontext
		 */
		public LocalHtmlRendererContext(HtmlPanel contextComponent, UserAgentContext ucontext) {
			super(contextComponent, ucontext);
		}

		/* (non-Javadoc)
		 * @see org.jicker.util.cobra.SimpleHtmlRendererContext#linkClicked(org.w3c.dom.html2.HTMLElement, java.net.URL, java.lang.String)
		 */
		public void linkClicked(HTMLElement linkNode, URL url, String target) {
			super.linkClicked(linkNode, url, target);
			System.out.println("## Link clicked: " + linkNode);
		}
	}
}
