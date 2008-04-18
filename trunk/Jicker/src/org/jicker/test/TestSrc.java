/*
 * 
 */
package org.jicker.test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSrc.
 */
public class TestSrc {

/** The test. */
String test;
	
	/**
	 * Instantiates a new test src.
	 * 
	 * @param test the test
	 */
	public TestSrc(String test){
		this.test = test.toUpperCase();
		System.out.println( test + "\t" + this.test );
	}
	
	/**
	 * My method.
	 * 
	 * @return the string
	 */
	public String myMethod(){
		return this.test;
	}

}
