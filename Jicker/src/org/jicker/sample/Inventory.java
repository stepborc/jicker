/*
 * 
 */
package org.jicker.sample;

// TODO: Auto-generated Javadoc
/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new inventory.
	 * 
	 * @param id the id
	 * @param name the name
	 */
	public Inventory(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name+"/"+id;
		}
}
