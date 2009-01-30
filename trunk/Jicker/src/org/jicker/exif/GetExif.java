/*
 *
 */
package org.jicker.exif;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;

// TODO: Auto-generated Javadoc
/**
 * The Class GetExif.
 */
public class GetExif {

	/**
	 * Instantiates a new gets the exif.
	 *
	 * @throws MetadataException
	 *             the metadata exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GetExif() {
		File jpegFile = new File("DSCF3635.JPG");
		/*
		 * Metadata metadata = null; try { metadata =
		 * JpegMetadataReader.readMetadata(jpegFile); } catch (Exception e) {
		 * e.printStackTrace(System.err); System.exit(1); }
		 * System.out.println(metadata..toString());
		 */
		Metadata metadata = null;
		try {
			metadata = JpegMetadataReader.readMetadata(jpegFile);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

		// iterate over the exif data and print to System.out
		Iterator directories = metadata.getDirectoryIterator();
		while (directories.hasNext()) {
			Directory directory = (Directory) directories.next();
			Iterator tags = directory.getTagIterator();
			while (tags.hasNext()) {
				Tag tag = (Tag) tags.next();
				try {
					System.out.println("[" + directory.getName() + "] "
							+ tag.getTagName() + " = " + tag.getDescription());
					// System.out.println("\[" + directory.getName() + "] " +
					// tag.getTagName() + " = " + tag.getDescription());
				} catch (MetadataException e) {
					System.err.println(e.getMessage());
					System.err.println(tag.getDirectoryName() + " "
							+ tag.getTagName() + " (error)");
				}
			}
			if (directory.hasErrors()) {
				Iterator errors = directory.getErrors();
				while (errors.hasNext()) {
					System.out.println("ERROR: " + errors.next());
				}
			}
		}

		if (jpegFile.equals("/thumb")) {
			ExifDirectory directory = (ExifDirectory) metadata
					.getDirectory(ExifDirectory.class);
			if (directory.containsThumbnail()) {
				System.out.println("Writing thumbnail...");
				try {
					directory.writeThumbnail(jpegFile.toString().trim()
							+ ".thumb.jpg");
				} catch (MetadataException e) {
					System.out.println("Jpeg Datei steht nicht zur Verfügung");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Datei nicht im Filesystem gefunden");
					e.printStackTrace();
				}
			} else {
				System.out.println("No thumbnail data exists in this image");
			}
		}
	}
}