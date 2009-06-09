package org.jicker.test.ve;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class TestGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane0;
	private JMenuItem jMenuItem0;
	private JMenuItem jMenuItem3;
	private JMenu jMenu0;
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem4;
	private JMenu jMenu1;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem7;
	private JMenuItem jMenuItem6;
	private JMenu jMenu2;
	private JMenuBar jMenuBar0;
	private JMenuItem jMenuItem8;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public TestGui() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new GroupLayout());
		setJMenuBar(getJMenuBar0());
		setSize(391, 311);
	}

	private JMenuItem getJMenuItem8() {
		if (jMenuItem8 == null) {
			jMenuItem8 = new JMenuItem();
			jMenuItem8.setText("jMenuItem8");
		}
		return jMenuItem8;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getJMenu0());
			jMenuBar0.add(getJMenu1());
			jMenuBar0.add(getJMenu2());
		}
		return jMenuBar0;
	}

	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("jMenu2");
			jMenu2.add(getJMenuItem2());
			jMenu2.add(getJMenuItem5());
			jMenu2.add(getJMenuItem7());
			jMenu2.add(getJMenuItem6());
		}
		return jMenu2;
	}

	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("jMenuItem6");
		}
		return jMenuItem6;
	}

	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem();
			jMenuItem7.setText("jMenuItem7");
		}
		return jMenuItem7;
	}

	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("jMenuItem5");
		}
		return jMenuItem5;
	}

	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("jMenuItem2");
		}
		return jMenuItem2;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("jMenu1");
			jMenu1.add(getJMenuItem1());
			jMenu1.add(getJMenuItem4());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("jMenuItem4");
		}
		return jMenuItem4;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("jMenuItem1");
		}
		return jMenuItem1;
	}

	private JMenu getJMenu0() {
		if (jMenu0 == null) {
			jMenu0 = new JMenu();
			jMenu0.setText("jMenu0");
			jMenu0.add(getJMenuItem0());
			jMenu0.add(getJMenuItem3());
		}
		return jMenu0;
	}

	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("jMenuItem3");
		}
		return jMenuItem3;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("jMenuItem0");
		}
		return jMenuItem0;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(89);
			jSplitPane0.setAutoscrolls(true);
		}
		return jSplitPane0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TestGui frame = new TestGui();
				frame.setDefaultCloseOperation(TestGui.EXIT_ON_CLOSE);
				frame.setTitle("TestGui");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
