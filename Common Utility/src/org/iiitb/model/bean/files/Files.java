package org.iiitb.model.bean.files;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.model.bean.Resource;

public class Files extends Resource {

	private int fileNumber;

	private List<FileUnit> fileUnits = new ArrayList<FileUnit>();

	

	public Files(int fileNumber) {
		super();
		this.fileNumber = fileNumber;		
	}

	public List<FileUnit> getFileUnits() {
		return fileUnits;
	}

	public void setFileUnits(List<FileUnit> fileUnits) {
		this.fileUnits = fileUnits;
	}

	public int getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(int fileNumber) {
		this.fileNumber = fileNumber;
	}

}
