package test;

import static org.junit.Assert.assertTrue;

import org.junit.Assert.*;
import junit.framework.Assert;

import org.jicker.test.TestSrc;
import org.junit.Test;

public class TestTestSrc {

	@Test
	public final void testMyMethod() {
		//TestSrc ts = new TestSrc("test");
		//assertTrue("TEST".equals(ts.myMethod()));
		//Kompakte Darstellung der beiden oberen Zeilen
		assertTrue("TEST".equalsIgnoreCase(new TestSrc("test").myMethod()));
	}

}
