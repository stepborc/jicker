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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lobobrowser.html.HtmlRendererContext;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.gui.SelectionChangeEvent;
import org.lobobrowser.html.gui.SelectionChangeListener;

// TODO: Auto-generated Javadoc
/**
 * A Swing frame that can be used to test the
 * Cobra HTML rendering engine.
 */
public class TestFrame extends JFrame {	
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TestFrame.class.getName());
	
	/** The rcontext. */
	private final SimpleHtmlRendererContext rcontext;
	
	/** The tree. */
	private final JTree tree;
	
	/** The html panel. */
	private final HtmlPanel htmlPanel;
	
	/** The text area. */
	private final JTextArea textArea;
	
	/** The address field. */
	private final JTextField addressField;
	
	/**
	 * Instantiates a new test frame.
	 * 
	 * @throws HeadlessException the headless exception
	 */
	public TestFrame() throws HeadlessException {
		this("");
	}
	
	/**
	 * Instantiates a new test frame.
	 * 
	 * @param title the title
	 * 
	 * @throws HeadlessException the headless exception
	 */
	public TestFrame(String title) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		final JTextField textField = new JTextField();
		this.addressField = textField;
		JButton button = new JButton("Parse & Render");
		final JTabbedPane tabbedPane = new JTabbedPane();
		final JTree tree = new JTree();
		final JScrollPane scrollPane = new JScrollPane(tree);
		
		this.tree = tree;
		
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(bottomPanel, BorderLayout.CENTER);
		
		topPanel.add(new JLabel("URL: "), BorderLayout.WEST);
		topPanel.add(textField, BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.EAST);
		
		bottomPanel.add(tabbedPane, BorderLayout.CENTER);
		
		final HtmlPanel panel = new HtmlPanel();
		panel.addSelectionChangeListener(new SelectionChangeListener() {
			public void selectionChanged(SelectionChangeEvent event) {
				if(logger.isLoggable(Level.INFO)) {
					logger.info("selectionChanged(): selection node: " + panel.getSelectionNode());
				}
			}
		});
		this.htmlPanel = panel;	
		UserAgentContext ucontext = new SimpleUserAgentContext();
		this.rcontext = new LocalHtmlRendererContext(panel, ucontext);
		
		final JTextArea textArea = new JTextArea();
		this.textArea = textArea;
		textArea.setEditable(false);
		final JScrollPane textAreaSp = new JScrollPane(textArea);
		
		tabbedPane.addTab("HTML", panel);
		tabbedPane.addTab("Tree", scrollPane);
		tabbedPane.addTab("Source", textAreaSp);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Component component = tabbedPane.getSelectedComponent();
				if(component == scrollPane) {
					tree.setModel(new NodeTreeModel(panel.getRootNode()));
				}
				else if(component == textAreaSp) {
					textArea.setText(rcontext.getSourceCode());
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				process(textField.getText());
			}
		});
	}
	
	/**
	 * Gets the html renderer context.
	 * 
	 * @return the html renderer context
	 */
	public HtmlRendererContext getHtmlRendererContext() {
		return this.rcontext;
	}
	
	/**
	 * Navigate.
	 * 
	 * @param uri the uri
	 */
	public void navigate(String uri) {
		this.addressField.setText(uri);
		this.process(uri);
	}
	
	/**
	 * Process.
	 * 
	 * @param uri the uri
	 */
	private void process(String uri) {
		try {
			URL url;
			try {
				url = new URL(uri);
			} catch(java.net.MalformedURLException mfu) {
				int idx = uri.indexOf(':');
				if(idx == -1 || idx == 1) {
					// try file
					url = new URL("file:" + uri);
				}
				else {
					throw mfu;
				}
			}
			// Call SimpleHtmlRendererContext.navigate()
			// which implements incremental rendering.
			this.rcontext.navigate(url, null);
		} catch(Exception err) {
			logger.log(Level.SEVERE, "Error trying to load URI=[" + uri + "].", err);
		}
	}
	
	/**
	 * The Class LocalHtmlRendererContext.
	 */
	private class LocalHtmlRendererContext extends SimpleHtmlRendererContext {
		
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
		 * @see org.jicker.util.cobra.SimpleHtmlRendererContext#open(java.net.URL, java.lang.String, java.lang.String, boolean)
		 */
		public HtmlRendererContext open(URL url, String windowName, String windowFeatures, boolean replace) {
			TestFrame frame = new TestFrame("Cobra Test Tool");
			frame.setSize(600, 400);
			frame.setExtendedState(TestFrame.NORMAL);
			frame.setVisible(true);
			HtmlRendererContext ctx = frame.getHtmlRendererContext();
			ctx.setOpener(this);
			frame.navigate(url.toExternalForm());
			return ctx;
		}
	}
}
