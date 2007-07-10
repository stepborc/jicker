package org.jicker.util.filebrowser;

import java.io.File;

public class DirectoryPrintVisitor implements DirectoryVisitor {
	String indent = "";
	public void enterDirectory(File dir){
		System.out.println(indent + "[" + dir.getName() + "]");
		indent += "  ";
	}
	public void leaveDirectory(File dir){
		indent = indent.substring(2);
	}
	public void visitFile(File file){
		System.out.println(indent + file.getName());
	}

}
