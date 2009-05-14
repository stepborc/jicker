package org.jicker.viralpetal.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetNameOfCurrentMethod {
	private final static Logger log = LoggerFactory
			.getLogger(GetNameOfCurrentMethod.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String methodName = Thread.currentThread().getStackTrace()[1]
				.getMethodName();
		log.info(methodName);
		GetNameOfCurrentMethod a = new GetNameOfCurrentMethod();
		a.testMethod();
	}

	public void testMethod() {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());
	}
}
