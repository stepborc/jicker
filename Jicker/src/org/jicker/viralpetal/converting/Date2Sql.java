package org.jicker.viralpetal.converting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Date2Sql {
	private final static Logger log = LoggerFactory.getLogger(Date2Sql.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		log.info(sqlDate.toString());
	}

}
