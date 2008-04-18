/*
 * 
 */
package org.jicker.util.dirbrowser;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class JickerFilter.
 */
public class JickerFilter {
	
	/**
	 * Liefert ein FileFilter zurück.<p>
	 * Der Filter wird erstellt aus den übergebenen Dateiendungen (suffix),
	 * welche in Groß- und Kleinschreibung konvertiert werden.
	 * 
	 * @param suffix - Dateiendung inklusive . (z.B. .jpg)
	 * 
	 * @return         FileFilter bestehed aus den Dateiendungen in Groß- und Kleinschreibung
	 */
	public FileFilter createFilter(String[] suffix){
		
	IOFileFilter JickerDirFilter = FileFilterUtils.andFileFilter(
			FileFilterUtils.directoryFileFilter(),
			HiddenFileFilter.VISIBLE);

	/*
	 * Alte Variante, neue verwendet SuffixFileFilter // Erstelle Filter
	 * für Dateien mit der Endung ".mp3" String suffix = ".jpg";
	 * 
	 * String[] suffixFilter = new String[2];
	 * suffixFilter[0]=suffix.toLowerCase();
	 * suffixFilter[1]=suffix.toUpperCase();
	 * 
	 * //IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter( //
	 * FileFilterUtils.fileFileFilter(), FileFilterUtils //
	 * .suffixFileFilter(".JPG"));
	 * 
	 * IOFileFilter JickerFileFilter = FileFilterUtils.andFileFilter(
	 * FileFilterUtils.fileFileFilter(), FileFilterUtils
	 * .suffixFileFilter(".mp3"));
	 */
	// Erstelle Filter für Dateien mit der bestimmnten Endungen
	//String suffix = ".jpg";
	List<String> suffixFilter = new ArrayList<String>();
	for (int n = 0;n < suffix.length;n++){
	suffixFilter.add(suffix[n].toLowerCase());
	suffixFilter.add(suffix[n].toUpperCase());
	}
	SuffixFileFilter sf = new SuffixFileFilter(suffixFilter);
	/*
	 * Alte Variante des FileFilters // Verbinde die Filter mit dem
	 * or-Filter java.io.FileFilter JickerFilter =
	 * FileFilterUtils.orFileFilter( JickerDirFilter, JickerFileFilter);
	 */
	// Neue Variante des FileFilters, Suffix ist nun ein List
	java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
			JickerDirFilter, sf);
	return JickerFilter;
	}
}
