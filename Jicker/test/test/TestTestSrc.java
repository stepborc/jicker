/*
 * 
 */
package test;

import static org.junit.Assert.assertTrue;

import org.jicker.test.TestSrc;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestTestSrc.
 */
public class TestTestSrc {

	/**
	 * Test my method.
	 */
	@Test
	public final void testMyMethod() {
		//TestSrc ts = new TestSrc("test");
		//assertTrue("TEST".equals(ts.myMethod()));
		//Kompakte Darstellung der beiden oberen Zeilen
		assertTrue("TEST".equalsIgnoreCase(new TestSrc("test").myMethod()));
	}

}
