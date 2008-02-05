package org.jicker.test;

public class TestSrc {
String test;
	public TestSrc(String test){
		this.test = test.toUpperCase();
		System.out.println( test + "\t" + this.test );
	}
	public String myMethod(){
		return this.test;
	}

}
