/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2006 The Lobo Project

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Contact info: lobochief@users.sourceforge.net
*/
/*
 * Created on Oct 22, 2005
 */
package org.jicker.util.cobra;

import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.lobobrowser.html.BrowserFrame;
import org.lobobrowser.html.FormInput;
import org.lobobrowser.html.HtmlObject;
import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.domimpl.FrameNode;
import org.lobobrowser.html.domimpl.HTMLDocumentImpl;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.parser.DocumentBuilderImpl;
import org.lobobrowser.html.parser.InputSourceImpl;
import org.lobobrowser.util.Urls;
import org.lobobrowser.util.io.RecordedInputStream;
import org.w3c.dom.html2.HTMLCollection;
import org.w3c.dom.html2.HTMLElement;
import org.w3c.dom.html2.HTMLLinkElement;

/**
 * The <code>SimpleHtmlRendererContext</code> class implements
 * the {@link org.lobobrowser.html.HtmlRendererContext} interface.
 * Note that this class provides simple implementations
 * of most methods, which should be overridden to provide
 * real-world functionality. 
 */
public class SimpleHtmlRendererContext implements HtmlRendererContext {
	private static final Logger logger = Logger.getLogger(SimpleHtmlRendererContext.class.getName());
	
	private final HtmlPanel htmlPanel;
	private final HtmlRendererContext parentRcontext;
	
	/**
	 * Constructs a SimpleHtmlRendererContext.
	 * @param contextComponent The component that will render HTML.
	 * @deprecated Use constructor that takes <code>HtmlPanel</code> and <code>UserAgentContext</code>
	 */
	public SimpleHtmlRendererContext(HtmlPanel contextComponent) {
		this(contextComponent, (UserAgentContext) null);
	}

	/**
	 * Constructs a SimpleHtmlRendererContext.
	 * @param contextComponent The component that will render HTML.
	 */
	public SimpleHtmlRendererContext(HtmlPanel contextComponent, UserAgentContext ucontext) {
		super();
		this.htmlPanel = contextComponent;
		this.parentRcontext = null;
		this.bcontext = ucontext;
	}

	/**
	 * Constructs a SimpleHtmlRendererContext.
	 * @param contextComponent The component that will render HTML.
	 * @param parentRcontext The parent's renderer context. This is <code>null</code> for the root renderer context.
	 * Normally ony frame renderer contexts would have parents.
	 */
	public SimpleHtmlRendererContext(HtmlPanel contextComponent, HtmlRendererContext parentRcontext) {
		super();
		this.htmlPanel = contextComponent;
		this.parentRcontext = parentRcontext;
		this.bcontext = parentRcontext == null ? null : parentRcontext.getUserAgentContext();
	}

	private volatile String sourceCode;
	
	public String getSourceCode() {
		return this.sourceCode;
	}

	public HTMLCollection getFrames() {
		Object rootNode = this.htmlPanel.getRootNode();
		if(rootNode instanceof HTMLDocumentImpl) {
			return ((HTMLDocumentImpl) rootNode).getFrames();
		}
		else {
			return null;
		}
	}

	/**
	 * Implements reload as navigation to current URL.
	 * Override to implement a more robust reloading
	 * mechanism.
	 */
	public void reload() {
		HTMLDocumentImpl document = (HTMLDocumentImpl) this.htmlPanel.getRootNode();
		if(document != null) {
			try {
				URL url = new URL(document.getDocumentURI());
				this.navigate(url, null);
			} catch(java.net.MalformedURLException throwable) {
				this.warn("reload(): Malformed URL", throwable);
			}
		}
	}
	
	/**
	 * Implements the link click handler by invoking {@link #navigate(URL, String)}.
	 */
	public void linkClicked(HTMLElement linkNode, URL url, String target) {
		this.navigate(url, target);
	}

	/**
	 * Gets the connection proxy used in {@link #navigate(URL, String)}.
	 * This implementation calls {@link SimpleUserAgentContext#getProxy()}
	 * if {@link #getUserAgentContext()} returns an instance assignable to {@link SimpleUserAgentContext}.
	 * The method may be overridden to provide a different proxy setting.
	 */
	protected Proxy getProxy() {
		Object ucontext = this.getUserAgentContext();
		if(ucontext instanceof SimpleUserAgentContext) {
			return ((SimpleUserAgentContext) ucontext).getProxy();
		}
		return Proxy.NO_PROXY;
	}
	
	/**
	 * Implements simple navigation with incremental
	 * rendering by invoking {@link #submitForm(String, URL, String, String, FormInput[])}
	 * with a <code>GET</code> request method.
	 */
	public void navigate(final URL href, String target) {
		this.submitForm("GET", href, target, null, null);
	}
	
	/**
	 * Implements simple navigation and form submission with incremental
	 * rendering and target processing, including
	 * frame lookup. Should be overridden to allow for
	 * more robust browser navigation and form submission.
	 * <p>
	 * <b>Notes:</b>
	 * <ul>
	 * <li>Encoding ISO-8859-1 assumed always.
	 * <li>Caching is not implemented.
	 * <li>Cookies are not implemented.
	 * <li>Incremental rendering is not optimized for
	 *     ignorable document change notifications.
	 * <li>Other HTTP features are not implemented.
	 * <li>The only form encoding type supported is <code>application/x-www-form-urlencoded</code>.
	 * </ul>
	 */
	public void submitForm(final String method, final java.net.URL action, final String target, String enctype, final FormInput[] formInputs) {
		// This method implements simple incremental rendering.
		if(target != null) {
			HtmlRendererContext topCtx = this.getTop();
			HTMLCollection frames = topCtx.getFrames();
			if(frames != null) {
				org.w3c.dom.Node frame = frames.namedItem(target);
				if(frame instanceof FrameNode) {
					BrowserFrame bframe = ((FrameNode) frame).getBrowserFrame();
					if(bframe == null) {
						throw new IllegalStateException("Frame node without a BrowserFrame instance: " + frame);
					}
					if(bframe.getHtmlRendererContext() != this) {
						bframe.loadURL(action);
						return;	
					}
				}
			}
			String actualTarget = target.trim().toLowerCase();
			if("_top".equals(actualTarget)) {
				this.getTop().navigate(action, null);
				return;
			}
			else if ("_parent".equals(actualTarget)) {
				HtmlRendererContext parent = this.getParent();
				if(parent != null) {
					parent.navigate(action, null);
					return;
				}
			}
			else if("_blank".equals(actualTarget)) {
				this.open(action.toExternalForm(), "cobra.blank", "", false);
				return;
			}
			else {
				// fall through
			}
		}
		
		URL urlForLoading;
		if(action.getProtocol().equals("file")) {
			// Remove query so it works.
			try {
				urlForLoading = new URL(action.getProtocol(), action.getHost(), action.getPort(), action.getPath());
			} catch(java.net.MalformedURLException throwable) {
				this.warn("malformed", throwable);
				urlForLoading = action;
			}
		}
		else {
			urlForLoading = action;
		}
		final URL finalURLForLoading = urlForLoading;		
		// Make request asynchronously.
		new Thread() {
			public void run() {
				try {
					URL uri = action;
					logger.info("process(): Loading URI=[" + uri + "].");
					long time0 = System.currentTimeMillis();
					// Using potentially different URL for loading.
					Proxy proxy = SimpleHtmlRendererContext.this.getProxy();
					boolean isPost = "post".equalsIgnoreCase(method);
					URLConnection connection = proxy == null || proxy == Proxy.NO_PROXY ? finalURLForLoading.openConnection() : finalURLForLoading.openConnection(proxy);
					connection.setRequestProperty("User-Agent", getUserAgentContext().getUserAgent());
					connection.setRequestProperty("Cookie", "");
					if (connection instanceof HttpURLConnection) {
						HttpURLConnection hc = (HttpURLConnection) connection;
						hc.setRequestMethod(method);
						hc.setInstanceFollowRedirects(false);
					}
					if(isPost) {
						connection.setDoOutput(true);
				    	ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
				    	boolean firstParam = true;
				    	if(formInputs != null) {
				    		for(int i = 0; i < formInputs.length; i++) {
				    			FormInput parameter = formInputs[i];
				    			String name = parameter.getName();
			    				String encName = URLEncoder.encode(name, "UTF-8");
				    			if(parameter.isText()) {
				    				if(firstParam) {
				    					firstParam = false;
				    				}
				    				else {
				    					bufOut.write((byte) '&');				    					
				    				}
				    				String valueStr = parameter.getTextValue();
				    				String encValue = URLEncoder.encode(valueStr, "UTF-8");
				    				bufOut.write(encName.getBytes("UTF-8"));
				    				bufOut.write((byte) '=');
				    				bufOut.write(encValue.getBytes("UTF-8"));
				    			}
				    			else {
				    				logger.warning("postData(): Ignoring non-textual parameter " + name + " for POST.");
				    			}
				    		}
				    	}
				    	// Do not add a line break to post content. Some servers
				    	// can be picky about that (namely, java.net).
						byte[] postContent = bufOut.toByteArray();
						if(connection instanceof HttpURLConnection) {
							((HttpURLConnection) connection).setFixedLengthStreamingMode(postContent.length);
						}
						connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						//connection.setRequestProperty("Content-Length", String.valueOf(postContent.length));
				    	OutputStream postOut = connection.getOutputStream();
						postOut.write(postContent);
						postOut.flush();
					}					
					if (connection instanceof HttpURLConnection) {					
						HttpURLConnection hc = (HttpURLConnection) connection;
						int responseCode = hc.getResponseCode();
						if(logger.isLoggable(Level.INFO)) {
							logger.info("process(): HTTP response code: "
									+ responseCode);
						}
						if(responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
							String location = hc.getHeaderField("Location");
							if(location == null) {
								logger.warning("No Location header in redirect from " + action + ".");
							}
							else {
								java.net.URL href;
								href = Urls.createURL(action, location);
								SimpleHtmlRendererContext.this.navigate(href, target);
							}
							return;
						}
					}
					InputStream in = connection.getInputStream();
					try {
						SimpleHtmlRendererContext.this.sourceCode = null;
						long time1 = System.currentTimeMillis();
						RecordedInputStream rin = new RecordedInputStream(in);
						InputStream bin = new BufferedInputStream(rin, 8192);
						DocumentBuilderImpl builder = new DocumentBuilderImpl(getUserAgentContext(), SimpleHtmlRendererContext.this);
						String actualURI = uri.toExternalForm();
						// Only create document, don't parse.
						HTMLDocumentImpl document = (HTMLDocumentImpl) builder
						.createDocument(new InputSourceImpl(bin, actualURI, getDocumentCharset(connection)));
						// Set document in HtmlPanel. Safe to call outside GUI thread.
						SimpleHtmlRendererContext.this.htmlPanel.setDocument(document, SimpleHtmlRendererContext.this);
						// Now start loading.
						document.load();
						long time2 = System.currentTimeMillis();
						logger.info("Parsed URI=[" + uri + "]: Parse elapsed: "
								+ (time2 - time1) + " ms. Connection elapsed: "
								+ (time1 - time0) + " ms.");
						SimpleHtmlRendererContext.this.sourceCode = rin.getString("ISO-8859-1");
					} finally {
						in.close();
					}
				} catch (Exception err) {
					SimpleHtmlRendererContext.this.error(
							"navigate(): Error loading or parsing request.",
							err);
				}
			}
		}.start();
	}

	/**
	 * This method is invoked by {@link #submitForm(String, URL, String, String, FormInput[])}
	 * to determine the charset of a document. This simple implementation returns
	 * <code>ISO-8859-1</code> always. Override to use other charsets.
	 * @param connection A URL connection.
	 */
	protected String getDocumentCharset(URLConnection connection) {
		return "ISO-8859-1";
	}
	
	// Methods useful to Window below:
	
	public void alert(String message) {
		JOptionPane.showMessageDialog(this.htmlPanel, message);
	}
	
	public void back() {
		this.warn("back(): Not overridden");
	}
	
	public void blur() {
		this.warn("back(): Not overridden");
	}

	public void close() {
		this.warn("close(): Not overridden");
	}
	
	public boolean confirm(String message) {
		int retValue = JOptionPane.showConfirmDialog(htmlPanel, message, "Confirm", JOptionPane.YES_NO_OPTION);
		return retValue == JOptionPane.YES_OPTION;
	}
		
	public void focus() {
		this.warn("focus(): Not overridden");
	}
	
	public final HtmlRendererContext open(String url, String windowName, String windowFeatures, boolean replace) {
		URL urlObj;
		try {
			urlObj = new URL(url);
		} catch(MalformedURLException mfu) {
			throw new IllegalArgumentException("Malformed URL: " + url);
		}
		return this.open(urlObj, windowName, windowFeatures, replace);
	}

	public HtmlRendererContext open(java.net.URL url, String windowName, String windowFeatures, boolean replace) {
		this.warn("open(): Not overridden");
		return null;
	}

	public String prompt(String message, String inputDefault) {
		return JOptionPane.showInputDialog(htmlPanel, message);
	}

	public void scroll(int x, int y) {
		this.warn("scroll(): Not overridden");
	}
	
	public boolean isClosed() {
		this.warn("isClosed(): Not overridden");
		return false;
	}
	
	public String getDefaultStatus() {
		this.warn("getDefaultStatus(): Not overridden");
		return "";
	}
		
	public int getLength() {
		this.warn("getLength(): Not overridden");
		return 0;
	}
	
	public String getName() {
		this.warn("getName(): Not overridden");
		return "";
	}
	
	public HtmlRendererContext getParent() {
		return this.parentRcontext;
	}

	private volatile HtmlRendererContext opener;
	
	public HtmlRendererContext getOpener() {
		return this.opener;
	}
	
	public void setOpener(HtmlRendererContext opener) {
		this.opener = opener;
	}
	
	public String getStatus() {
		this.warn("getStatus(): Not overridden");
		return "";
	}
	
	public void setStatus(String message) {
		this.warn("setStatus(): Not overridden");
	}
	
	public HtmlRendererContext getTop() {
		HtmlRendererContext ancestor = this.parentRcontext;
		if(ancestor == null) {
			return this;
		}
		return ancestor.getTop();
	}
	
	/* (non-Javadoc)
	 * @see org.xamjwg.html.HtmlContext#createBrowserFrame()
	 */
	public BrowserFrame createBrowserFrame() {
		return new SimpleBrowserFrame(this);
	}		
	
	public void warn(String message, Throwable throwable) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message, throwable);
		}
	}
	
	public void error(String message, Throwable throwable) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message, throwable);
		}
	}
	
	public void warn(String message) {
		if(logger.isLoggable(Level.WARNING)) {
			logger.log(Level.WARNING, message);
		}		
	}
	
	public void error(String message) {
		if(logger.isLoggable(Level.SEVERE)) {
			logger.log(Level.SEVERE, message);
		}
	}

	/**
	 * Returns <code>null</code>. This method should be overridden
	 * to provide OBJECT, EMBED or APPLET functionality. 
	 */
	public HtmlObject getHtmlObject(HTMLElement element) {
		return null;
	}

	public void setDefaultStatus(String message) {
		this.warn("setDefaultStatus(): Not overridden.");
	}

	private UserAgentContext bcontext = null;
	
	/**
	 * If a {@link org.lobobrowser.html.UserAgentContext} instance
	 * was provided in the constructor, then that instance is returned.
	 * Otherwise, an instance of {@link SimpleUserAgentContext} is 
	 * created and returned.
	 * <p>
	 * The context returned by this method is used by local request 
	 * facilities and other parts of the renderer.
	 */
	public UserAgentContext getUserAgentContext() {
		synchronized(this) {
			if(this.bcontext == null) {
				this.warn("getUserAgentContext(): UserAgentContext not provided in constructor. Creating a simple one.");
				this.bcontext = new SimpleUserAgentContext();
			}
			return this.bcontext;
		}
	}
	
	/**
	 * Should be overridden to return true if the link
	 * has been visited.
	 */
	public boolean isVisitedLink(HTMLLinkElement link) {
		//TODO
		return false;
	}

	/**
	 * @deprecated This method has been moved to {@link UserAgentContext}.
	 *             Override method from SimpleUserAgentContext instead.
	 */
	public boolean isMedia(String mediaName) {
		return true;
	}

	/**
	 * This method must be overridden to implement a context menu.
	 */
	public void onContextMenu(HTMLElement element, MouseEvent event) {
		this.warn("onContextMenu(): Not overridden.");
	}

	/**
	 * This method can be overridden to receive notifications when the
	 * mouse leaves an element.
	 */
	public void onMouseOut(HTMLElement element, MouseEvent event) {
	}

	/**
	 * This method can be overridden to receive notifications when the
	 * mouse first enters an element.
	 */
	public void onMouseOver(HTMLElement element, MouseEvent event) {
	}
}
