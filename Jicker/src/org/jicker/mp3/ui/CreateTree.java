/*
 * 
 */
package org.jicker.mp3.ui;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jicker.mp3.JickerMp3Globals;
import org.jicker.mp3.dbobject.Mp3Dir;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

// TODO: Auto-generated Javadoc
/**
 * The Class SetTree.
 */
public class CreateTree {

	private static DefaultMutableTreeNode album;

	/**
	 * Instantiates a new sets the tree.
	 */
	public CreateTree() {
	}

	/**
	 * Creates the tree.
	 * 
	 * @return the tree node
	 */
	public static TreeNode createTree() {
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode("Alben");
		ObjectContainer db = Db4o.openFile(JickerMp3Globals.dbName);
		// Datenbank auslesen
		Mp3Dir mp3Dir = new Mp3Dir(null, 0, null, 1);
		ObjectSet<Mp3Dir> setlist = db.queryByExample(mp3Dir);
		Mp3Dir s = null;
		while (setlist.hasNext()) {
			s = setlist.next();
			DefaultMutableTreeNode album = new DefaultMutableTreeNode(s
					.getDirName());

			/*
			 * DbPhotos photoProto = new DbPhotos(null, s.getId());
			 * //ObjectSet<DbPhotos> photolist = db.get(DbPhotos.class);
			 * ObjectSet<DbPhotos> photolist = db.get(photoProto); int laenge =
			 * photolist.size(); DbPhotos p = null; while (photolist.hasNext()){
			 * p = photolist.next(); DefaultMutableTreeNode bilder = new
			 * DefaultMutableTreeNode(p.getId()); album.add(bilder); }
			 */
			tree.add(album);

		}
		db.close();
		return tree;
	}
}