/*
 * 
 */
package org.jicker.util;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenBrowser.
 */
public class OpenBrowser {
	
	/** The WI n_ id. */
	final String WIN_ID = "Win";

	//public OpenBrowser(java.io.File file) {
		/**
	 * Instantiates a new open browser.
	 * 
	 * @param file the file
	 */
	public OpenBrowser(String file) {
		if (isWindowsPlatform()) {
			try {
				Runtime.getRuntime().exec(tryCommand1() + file);
			} catch (Exception exc1) {
				try {
					Runtime.getRuntime().exec(tryCommand2() + file);				
					} catch (Exception exc2) {
					try {
						Runtime.getRuntime().exec(tryCommand3() + file);			
						} catch (Exception exc3) {
						// if(MainEditor.getOpenBrowserHelper() ) new
						// OpenBrowserHelper(file);
					}
				}
			}
		} else
			javax.swing.JOptionPane
					.showMessageDialog(
							null,
							"<html><center>"
									+ "Not a Windows Operating System. Contact software author for details"
									+ "</html></center>", "Not Using Windows",
							javax.swing.JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Checks if is windows platform.
	 * 
	 * @return true, if is windows platform
	 */
	private boolean isWindowsPlatform() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith(WIN_ID))
			return true;
		return false;
	}

	/**
	 * Try command1.
	 * 
	 * @return the string
	 */
	private String tryCommand1() {
		//return "C:\\Programme\\Internet Explorer\\Iexplore.exe file://";
		return "C:\\Programme\\Mozilla Firefox\\firefox.exe -new-window ";
		//return "C:\\Programme\\Internet Explorer\\Iexplore.exe ";
	}

	/**
	 * Try command2.
	 * 
	 * @return the string
	 */
	private String tryCommand2() {
		return "start rundll32 url.dll,FileProtocolHandler file://";
	}

	/**
	 * Try command3.
	 * 
	 * @return the string
	 */
	private String tryCommand3() {
		return "file://";
	}
}
