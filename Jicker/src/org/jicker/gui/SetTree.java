/*
 * 
 */
package org.jicker.gui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jicker.JickerGlobals;
import org.jicker.flickrj.db4o.DbPhotos;
import org.jicker.flickrj.db4o.DbSets;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SetTree.
 */
public class SetTree {

	/**
	 * Instantiates a new sets the tree.
	 */
	public SetTree() {
	}

	/**
	 * Creates the tree.
	 * 
	 * @return the tree node
	 */
	public static TreeNode createTree() {
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Alben");
		ObjectContainer db = Db4o.openFile(JickerGlobals.DATA_BASE);
		// Datenbank auslesen
		ObjectSet<DbSets> setlist = db.get(DbSets.class);
		DbSets s = null;
		while (setlist.hasNext()) {
			s = setlist.next();
			DefaultMutableTreeNode album = new DefaultMutableTreeNode(s
					.getTitle());

			DbPhotos photoProto = new DbPhotos(null, s.getId());
			// ObjectSet<DbPhotos> photolist = db.get(DbPhotos.class);
			ObjectSet<DbPhotos> photolist = db.get(photoProto);
			// 2009-01-30: Nur zu Testzwecken
			// int laenge = photolist.size();
			DbPhotos p = null;
			while (photolist.hasNext()) {
				p = photolist.next();
				DefaultMutableTreeNode bilder = new DefaultMutableTreeNode(p
						.getId());
				album.add(bilder);
			}
			tree.add(album);
		}
		db.close();
		return tree;
	}
}