package org.jicker.mp3.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.jicker.util.dirbrowser.DirBrowser;

public class GetMp3File {
	private List results;

	public GetMp3File(String verzeichnis){
		File dir = new File(verzeichnis);

		// Erstelle Filter für sichtbare Verzeichnisse
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
		String suffix = ".mp3";
		List<String> suffixFilter = new ArrayList<String>();
		suffixFilter.add(suffix.toLowerCase());
		suffixFilter.add(suffix.toUpperCase());
		SuffixFileFilter sf = new SuffixFileFilter(suffixFilter);

		/*
		 * Alte Variante des FileFilters // Verbinde die Filter mit dem
		 * or-Filter java.io.FileFilter JickerFilter =
		 * FileFilterUtils.orFileFilter( JickerDirFilter, JickerFileFilter);
		 */
		// Neue Variante des FileFilters, Suffix ist nun ein List
		java.io.FileFilter JickerFilter = FileFilterUtils.orFileFilter(
				JickerDirFilter, sf);

		// Erstelle eine Verzeichnisliste
		results = new DirBrowser(JickerFilter, 4).find(dir);
		/*
		 * Nur zu Testzwecken for (int n = 0; n < results.size(); n++) {
		 * System.out.print(n + "\t" + results.get(n)); if (((File)
		 * results.get(n)).isFile()) { long csum = FileUtils.checksum((File)
		 * results.get(n), new CRC32()).getValue(); System.out.print("\t" +
		 * csum + "\n"); } else { System.out.println(); } }
		 */
	}

	public List getMp3List(){
		return results;
		
	}

}
