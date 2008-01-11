package org.jicker.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jicker.flickrj.db4o.DbSets;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class SetTree{
	public SetTree() {
	}
	public static TreeNode createTree(){
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Alben");
		ObjectContainer db = Db4o.openFile("flickrDb.db4o");
		// Datenbank auslesen
		ObjectSet<DbSets> setlist = db.get(DbSets.class);
		while (setlist.hasNext()) {
			DefaultMutableTreeNode album = new DefaultMutableTreeNode(setlist.next().getTitle());
			tree.add(album);
		}
		db.close();
		return tree;
	}
}