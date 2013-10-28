package org.iiitb.model.bean.files;

public class LinkedFileUnit extends FileUnit {

	private int nextAddress;

	public LinkedFileUnit(int index, int nextAddress) {
		super(index);
		this.nextAddress = nextAddress;
	}

	public int getNextAddress() {
		return nextAddress;
	}

	public void setNextAddress(int nextAddress) {
		this.nextAddress = nextAddress;
	}

}
