/*
 * 
 */
package org.jicker.util.cobra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.domimpl.NodeImpl;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.parser.InputSourceImpl;
import org.lobobrowser.html.renderer.DelayedPair;
import org.lobobrowser.html.renderer.FrameContext;
import org.lobobrowser.html.renderer.RBlock;
import org.lobobrowser.html.renderer.RenderableContainer;
import org.lobobrowser.html.renderer.RenderableSpot;
import org.lobobrowser.util.io.IORoutines;
import org.w3c.dom.Document;

// TODO: Auto-generated Javadoc
/**
 * Checks for memory leaks.
 */
public class MemoryTest {
	// JVM setting -Xmx150m tried with:
	// - 500K file with fairly complex markup.
	// - 1.5M file with simple markup.
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(MemoryTest.class.getName());
	
	/**
	 * The main method.
	 * 
	 * @param args the args
	 * 
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		MemoryTest mt = new MemoryTest();
		//mt.testParserLoop();
		//mt.testRendererLoop();
		mt.testRendererGUILoop();
	}

	/** The TES t_ url. */
	private static String TEST_URL = "file:c:\\temp\\html\\long.html";
	
	/**
	 * Test parser loop.
	 * 
	 * @throws Exception the exception
	 */
	public void testParserLoop() throws Exception {
		URL url = new URL(TEST_URL);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;) Cobra/0.96.1+");
		connection.setRequestProperty("Cookie", "");
		if(connection instanceof HttpURLConnection) {
			HttpURLConnection hc = (HttpURLConnection) connection;
			hc.setInstanceFollowRedirects(true);
			int responseCode = hc.getResponseCode();
			logger.info("process(): HTTP response code: " + responseCode);
		}
		InputStream in = connection.getInputStream();
		byte[] content;
		try {
			content = IORoutines.load(in, 8192);
		} finally {
			in.close();
		}
		//String source = new String(content, "ISO-8859-1");
		//long time1 = System.currentTimeMillis();
		logger.info("Content size: " + content.length + " bytes.");
		UserAgentContext context = new SimpleUserAgentContext();
		DocumentBuilderImpl builder = new DocumentBuilderImpl(context);
		for(int i = 0; i < 200; i++) {
			logger.info("Starting parse # " + i + ": freeMemory=" + Runtime.getRuntime().freeMemory());
			InputStream bin = new ByteArrayInputStream(content);
			Document document = builder.parse(new InputSourceImpl(bin, url.toExternalForm(), "ISO-8859-1"));
			logger.info("Finished parsing: freeMemory=" + Runtime.getRuntime().freeMemory() + ",document=" + document);
			document = null;
			System.gc();
		 	logger.info("After GC: freeMemory=" + Runtime.getRuntime().freeMemory());
		 	Thread.sleep(2);
		}				
	}

	/**
	 * Test renderer loop.
	 * 
	 * @throws Exception the exception
	 */
	public void testRendererLoop() throws Exception {
		URL url = new URL(TEST_URL);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;) Cobra/0.96.1+");
		connection.setRequestProperty("Cookie", "");
		if(connection instanceof HttpURLConnection) {
			HttpURLConnection hc = (HttpURLConnection) connection;
			hc.setInstanceFollowRedirects(true);
			int responseCode = hc.getResponseCode();
			logger.info("process(): HTTP response code: " + responseCode);
		}
		InputStream in = connection.getInputStream();
		byte[] content;
		try {
			content = IORoutines.load(in, 8192);
		} finally {
			in.close();
		}
		//String source = new String(content, "ISO-8859-1");
		//long time1 = System.currentTimeMillis();
		logger.info("Content size: " + content.length + " bytes.");
		final UserAgentContext ucontext = new SimpleUserAgentContext();
		final HtmlPanel panel = new HtmlPanel();
		final HtmlRendererContext rcontext = new SimpleHtmlRendererContext(panel);
		DocumentBuilderImpl builder = new DocumentBuilderImpl(ucontext, rcontext);
		InputStream bin = new ByteArrayInputStream(content);
		final FrameContext frameContext = new LocalFrameContext();
		final RenderableContainer renderableContainer = new LocalRenderableContainer();
		for(int i = 0; i < 100; i++) {
			logger.info("Starting parse # " + i + ": freeMemory=" + Runtime.getRuntime().freeMemory());
			bin = new ByteArrayInputStream(content);
			Document document = builder.parse(new InputSourceImpl(bin, url.toExternalForm(), "ISO-8859-1"));
			logger.info("Finished parsing: freeMemory=" + Runtime.getRuntime().freeMemory());
			{
				final Document doc = document;
				EventQueue.invokeAndWait(new Runnable() {
					public void run() {
						RBlock block = new RBlock((NodeImpl) doc, 0, rcontext.getUserAgentContext(), rcontext, frameContext, renderableContainer, RBlock.OVERFLOW_NONE);
						block.layout(100, 100);
					}	
				});
				//panel.setDocument(doc, rcontext, pcontext);
				Thread.sleep(50);
				//panel.clearDocument();
			}
			document = null;
			System.gc();
		 	logger.info("After GC: freeMemory=" + Runtime.getRuntime().freeMemory());
		 	Thread.sleep(2);
		}				
	}
	
	/**
	 * Test renderer gui loop.
	 * 
	 * @throws Exception the exception
	 */
	public void testRendererGUILoop() throws Exception {
		URL url = new URL(TEST_URL);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;) Cobra/0.96.1+");
		connection.setRequestProperty("Cookie", "");
		if(connection instanceof HttpURLConnection) {
			HttpURLConnection hc = (HttpURLConnection) connection;
			hc.setInstanceFollowRedirects(true);
			int responseCode = hc.getResponseCode();
			logger.info("process(): HTTP response code: " + responseCode);
		}
		InputStream in = connection.getInputStream();
		byte[] content;
		try {
			content = IORoutines.load(in, 8192);
		} finally {
			in.close();
		}
		//String source = new String(content, "ISO-8859-1");
		//long time1 = System.currentTimeMillis();
		logger.info("Content size: " + content.length + " bytes.");
		final UserAgentContext ucontext = new SimpleUserAgentContext();
		final HtmlPanel panel = new HtmlPanel();
		final HtmlRendererContext rcontext = new SimpleHtmlRendererContext(panel);
		DocumentBuilderImpl builder = new DocumentBuilderImpl(ucontext, rcontext);
		JFrame testFrame = new JFrame("Testing...");
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.getContentPane().setLayout(new java.awt.BorderLayout());
		testFrame.getContentPane().add(panel, BorderLayout.CENTER);
		InputStream bin = new ByteArrayInputStream(content);
		testFrame.setSize(new java.awt.Dimension(600, 400));
		testFrame.setVisible(true);
		for(int i = 0; i < 20; i++) {
			logger.info("Starting parse # " + i + ": freeMemory=" + Runtime.getRuntime().freeMemory());
			bin = new ByteArrayInputStream(content);
			Document document = builder.parse(new InputSourceImpl(bin, url.toExternalForm(), "ISO-8859-1"));
			logger.info("Finished parsing: freeMemory=" + Runtime.getRuntime().freeMemory());
			panel.setDocument(document, rcontext);
			EventQueue.invokeAndWait(new Runnable() { public void run() {} });
			// Without these sleeps, it does apparently run out of memory.
			Thread.sleep(3000);
			panel.clearDocument();
			Thread.sleep(1000);
			document = null;
			System.gc();
		 	logger.info("After GC: freeMemory=" + Runtime.getRuntime().freeMemory());
			Thread.sleep(2000);
		}				
	}

	/**
	 * The Class LocalRenderableContainer.
	 */
	private class LocalRenderableContainer implements RenderableContainer {
		
		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#invalidateLayoutUpTree()
		 */
		public void invalidateLayoutUpTree() {
			// nop
		}
		
		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#addComponent(java.awt.Component)
		 */
		public Component addComponent(Component component) {
			//nop
			return null;
		}
		
		/**
		 * Removes the.
		 * 
		 * @param c the c
		 */
		public void remove(Component c) {
			// nop
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#getPaintedBackgroundColor()
		 */
		public Color getPaintedBackgroundColor() {
			return Color.BLACK;
		}

		/**
		 * Gets the insets.
		 * 
		 * @return the insets
		 */
		public Insets getInsets() {
			return new Insets(0, 0, 0, 0);
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#repaint(int, int, int, int)
		 */
		public void repaint(int x, int y, int width, int height) {
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#relayout()
		 */
		public void relayout() {
			// nop
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#updateAllWidgetBounds()
		 */
		public void updateAllWidgetBounds() {
			// nop
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#getGUIPoint(int, int)
		 */
		public Point getGUIPoint(int clientX, int clientY) {
			return new Point(clientX, clientY);
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#focus()
		 */
		public void focus() {
			//nop
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#addDelayedPair(org.lobobrowser.html.renderer.DelayedPair)
		 */
		public void addDelayedPair(DelayedPair pair) {
			//nop
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#getParentContainer()
		 */
		public RenderableContainer getParentContainer() {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#getDelayedPairs()
		 */
		public Collection getDelayedPairs() {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.RenderableContainer#clearDelayedPairs()
		 */
		public void clearDelayedPairs() {
		}	
	}
	
	/**
	 * The Class LocalFrameContext.
	 */
	private class LocalFrameContext implements FrameContext {
		
		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.FrameContext#expandSelection(org.lobobrowser.html.renderer.RenderableSpot)
		 */
		public void expandSelection(RenderableSpot rpoint) {
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.FrameContext#resetSelection(org.lobobrowser.html.renderer.RenderableSpot)
		 */
		public void resetSelection(RenderableSpot rpoint) {
		}

		/* (non-Javadoc)
		 * @see org.lobobrowser.html.renderer.FrameContext#delayedRelayout(org.lobobrowser.html.domimpl.NodeImpl)
		 */
		public void delayedRelayout(NodeImpl node) {
		}
	}
}
