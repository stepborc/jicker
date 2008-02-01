package org.jicker.sample;

public class Inventory {
	private int id;
	private String name;
	
	public Inventory(int id, String name){
		this.id = id;
		this.name = name;
	}
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String toString() {
		return name+"/"+id;
		}
}
