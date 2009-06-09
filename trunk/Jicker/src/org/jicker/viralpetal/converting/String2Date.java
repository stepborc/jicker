package org.jicker.viralpetal.converting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sborcher
 * @version 1.0
 * @see
 */
public class String2Date {
	private final static Logger log = LoggerFactory
			.getLogger(String2Date.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Eine Möglichkeit
			Date date = java.text.DateFormat.getDateInstance().parse(
					"10.12.2009");
			log.info(date.toString());
			// Zweite Möglichkeit
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			Date date2 = format.parse("10.12.2009");
			log.info(date2.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
