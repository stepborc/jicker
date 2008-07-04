/*
 *
 */
package org.jicker.jgoodies.looks;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import org.jicker.gui.SetTree;

import com.jeta.forms.components.image.ImageComponent;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class MyForm.
 */
public class MyForm extends JPanel {

	/** The m_photo panel. */
	JPanel m_photoPanel = new JPanel();

	/** The m_image. */
	ImageComponent m_image = new ImageComponent();

	/** The m_imagecomponent1. */
	ImageComponent m_imagecomponent1 = new ImageComponent();

	/** The m_tree tab. */
	JTabbedPane m_treeTab = new JTabbedPane();

	/** The model. */
	SetTree model = new SetTree();

	/** The m_flickr tree. */
	JTree m_flickrTree = new JTree(model.createTree());

	/** The m_own tree. */
	JTree m_ownTree = new JTree();

	/**
	 * Default constructor.
	 */
	public MyForm() {
		initializePanel();
	}

	/**
	 * Main method for panel.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 400);
		frame.setLocation(100, 100);
		frame.getContentPane().add(new MyForm());
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}

	/**
	 * Adds fill components to empty cells in the first row and first column of
	 * the grid. This ensures that the grid spacing will be the same as shown in
	 * the designer.
	 *
	 * @param cols
	 *            an array of column indices in the first row where fill
	 *            components should be added.
	 * @param rows
	 *            an array of row indices in the first column where fill
	 *            components should be added.
	 * @param panel
	 *            the panel
	 */
	void addFillComponents(Container panel, int[] cols, int[] rows) {
		Dimension filler = new Dimension(10, 10);

		boolean filled_cell_11 = false;
		CellConstraints cc = new CellConstraints();
		if (cols.length > 0 && rows.length > 0) {
			if (cols[0] == 1 && rows[0] == 1) {
				/** add a rigid area */
				panel.add(Box.createRigidArea(filler), cc.xy(1, 1));
				filled_cell_11 = true;
			}
		}

		for (int index = 0; index < cols.length; index++) {
			if (cols[index] == 1 && filled_cell_11) {
				continue;
			}
			panel.add(Box.createRigidArea(filler), cc.xy(cols[index], 1));
		}

		for (int index = 0; index < rows.length; index++) {
			if (rows[index] == 1 && filled_cell_11) {
				continue;
			}
			panel.add(Box.createRigidArea(filler), cc.xy(1, rows[index]));
		}

	}

	/**
	 * Helper method to load an image file from the CLASSPATH.
	 *
	 * @param imageName
	 *            the package and name of the file to load relative to the
	 *            CLASSPATH
	 *
	 * @return an ImageIcon instance with the specified image file
	 *
	 * @throws IllegalArgumentException
	 *             if the image resource cannot be loaded.
	 */
	public ImageIcon loadImage(String imageName) {
		try {
			ClassLoader classloader = getClass().getClassLoader();
			java.net.URL url = classloader.getResource(imageName);
			if (url != null) {
				ImageIcon icon = new ImageIcon(url);
				return icon;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Unable to load image: " + imageName);
	}

	/**
	 * Method for recalculating the component orientation for right-to-left
	 * Locales.
	 *
	 * @param orientation
	 *            the component orientation to be applied
	 */
	public void applyComponentOrientation(ComponentOrientation orientation) {
		// Not yet implemented...
		// I18NUtils.applyComponentOrientation(this, orientation);
		super.applyComponentOrientation(orientation);
	}

	/**
	 * Creates the panel.
	 *
	 * @return the j panel
	 */
	public JPanel createPanel() {
		JPanel jpanel1 = new JPanel();
		FormLayout formlayout1 = new FormLayout(
				"FILL:144PX:NONE,FILL:DEFAULT:GROW(1.0)",
				"FILL:465PX:GROW(1.0)");
		CellConstraints cc = new CellConstraints();
		jpanel1.setLayout(formlayout1);

		jpanel1.add(createphotoPanel(), new CellConstraints(2, 1, 1, 1,
				CellConstraints.FILL, CellConstraints.FILL));
		m_treeTab.setAutoscrolls(true);
		m_treeTab.setName("treeTab");
		m_treeTab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		m_treeTab.addTab("Flickr", null, createPanel1());
		m_treeTab.addTab("Eigene", null, createPanel2());
		jpanel1.add(m_treeTab, cc.xy(1, 1));

		addFillComponents(jpanel1, new int[] { 2 }, new int[0]);
		return jpanel1;
	}

	/**
	 * Createphoto panel.
	 *
	 * @return the j panel
	 */
	public JPanel createphotoPanel() {
		m_photoPanel.setName("photoPanel");
		FormLayout formlayout1 = new FormLayout(
				"FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE",
				"TOP:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE");
		CellConstraints cc = new CellConstraints();
		m_photoPanel.setLayout(formlayout1);

		m_image.setName("image");
		m_photoPanel.add(m_image, cc.xy(1, 1));

		m_photoPanel.add(m_imagecomponent1, cc.xy(2, 1));

		addFillComponents(m_photoPanel, new int[] { 3, 4, 5, 6, 7, 8, 9, 10 },
				new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		return m_photoPanel;
	}

	/**
	 * Creates the panel1.
	 *
	 * @return the j panel
	 */
	public JPanel createPanel1() {
		JPanel jpanel1 = new JPanel();
		FormLayout formlayout1 = new FormLayout("FILL:DEFAULT:GROW(1.0)",
				"CENTER:DEFAULT:GROW(1.0)");
		CellConstraints cc = new CellConstraints();
		jpanel1.setLayout(formlayout1);

		m_flickrTree.setName("flickrTree");
		JScrollPane jscrollpane1 = new JScrollPane();
		jscrollpane1.setViewportView(m_flickrTree);
		jscrollpane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrollpane1
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpanel1.add(jscrollpane1, new CellConstraints(1, 1, 1, 1,
				CellConstraints.FILL, CellConstraints.FILL));

		addFillComponents(jpanel1, new int[0], new int[0]);
		return jpanel1;
	}

	/**
	 * Creates the panel2.
	 *
	 * @return the j panel
	 */
	public JPanel createPanel2() {
		JPanel jpanel1 = new JPanel();
		FormLayout formlayout1 = new FormLayout("FILL:DEFAULT:GROW(1.0)",
				"CENTER:DEFAULT:GROW(1.0)");
		CellConstraints cc = new CellConstraints();
		jpanel1.setLayout(formlayout1);

		m_ownTree.setName("ownTree");
		JScrollPane jscrollpane1 = new JScrollPane();
		jscrollpane1.setViewportView(m_ownTree);
		jscrollpane1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrollpane1
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpanel1.add(jscrollpane1, new CellConstraints(1, 1, 1, 1,
				CellConstraints.FILL, CellConstraints.FILL));

		addFillComponents(jpanel1, new int[0], new int[0]);
		return jpanel1;
	}

	/**
	 * Initializer.
	 */
	protected void initializePanel() {
		setLayout(new BorderLayout());
		add(createPanel(), BorderLayout.CENTER);
	}

}
