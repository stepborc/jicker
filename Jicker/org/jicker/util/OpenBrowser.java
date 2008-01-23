package org.jicker.util;

public class OpenBrowser {
	final String WIN_ID = "Win";

	//public OpenBrowser(java.io.File file) {
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

	private boolean isWindowsPlatform() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith(WIN_ID))
			return true;
		return false;
	}

	private String tryCommand1() {
		//return "C:\\Programme\\Internet Explorer\\Iexplore.exe file://";
		return "C:\\Programme\\Mozilla Firefox\\firefox.exe -new-window ";
		//return "C:\\Programme\\Internet Explorer\\Iexplore.exe ";
	}

	private String tryCommand2() {
		return "start rundll32 url.dll,FileProtocolHandler file://";
	}

	private String tryCommand3() {
		return "file://";
	}
}
