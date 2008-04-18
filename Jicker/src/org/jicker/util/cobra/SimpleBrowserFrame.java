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
 * Created on Jan 29, 2006
 */
package org.jicker.util.cobra;

import java.awt.Component;
import java.net.URL;

import org.lobobrowser.html.BrowserFrame;
import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.w3c.dom.Document;

// TODO: Auto-generated Javadoc
/**
 * The <code>SimpleBrowserFrame</code> class implements
 * the {@link org.lobobrowser.html.BrowserFrame} interface.
 * It represents a browser frame component.
 * 
 * @author J. H. S.
 */
public class SimpleBrowserFrame extends HtmlPanel implements BrowserFrame {
	
	/** The rcontext. */
	private final SimpleHtmlRendererContext rcontext;
	
	/** The parent rcontext. */
	private final HtmlRendererContext parentRcontext;
	
	/**
	 * Instantiates a new simple browser frame.
	 * 
	 * @param parentRcontext the parent rcontext
	 */
	public SimpleBrowserFrame(HtmlRendererContext parentRcontext) {
		this.parentRcontext = parentRcontext;
		this.rcontext = new SimpleHtmlRendererContext(this, parentRcontext);
	}
	
	/* (non-Javadoc)
	 * @see org.lobobrowser.html.BrowserFrame#getHtmlRendererContext()
	 */
	public HtmlRendererContext getHtmlRendererContext() {
		return this.rcontext;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.BrowserFrame#getComponent()
	 */
	public Component getComponent() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.BrowserFrame#loadURL(java.net.URL)
	 */
	public void loadURL(URL url) {
		this.rcontext.navigate(url, "_this");
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.BrowserFrame#getContentDocument()
	 */
	public Document getContentDocument() {
		return (Document) this.getRootNode();
	}
	
	/**
	 * Gets the parent html renderer context.
	 * 
	 * @return the parent html renderer context
	 */
	public HtmlRendererContext getParentHtmlRendererContext() {
		return this.parentRcontext;
	}
}
