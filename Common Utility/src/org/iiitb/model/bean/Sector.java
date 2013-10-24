package org.iiitb.model.bean;

public class Sector extends Resource {
	int secNumber;
	int size;
	boolean inUse;

	public Sector(){
		
	}
	//Create a single Sector
	public Sector(int num, int size){
		this.secNumber= num;
		this.size = size;
		this.inUse = false;
		
		
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSecNumber() {
		return secNumber;
	}
	public void setSecNumber(int secNumber) {
		this.secNumber = secNumber;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
	
	
	
}
