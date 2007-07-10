package org.jicker.util.filebrowser;

import java.io.*;

public interface DirectoryVisitor {
	public void enterDirectory(File dir);
	public void leaveDirectory(File dir);
	public void visitFile(File file);
}
