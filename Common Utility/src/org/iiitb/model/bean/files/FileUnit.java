package org.iiitb.model.bean.files;

public abstract class FileUnit {

	protected int fileNumber;
	protected int index;

	public FileUnit(int index) {
		super();
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
