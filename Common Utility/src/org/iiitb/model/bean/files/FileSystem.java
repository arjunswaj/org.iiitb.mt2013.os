package org.iiitb.model.bean.files;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.model.consts.FileAllocationType;

public class FileSystem {

	private int diskSize;

	private int fileUnitSize;

	private FileAllocationType fileAllocationType;

	private List<Files> files = new ArrayList<Files>();

	public FileSystem(int diskSize, int fileUnitSize,
			FileAllocationType fileAllocationType) throws java.lang.Exception {
		super();
		this.diskSize = diskSize;
		this.fileUnitSize = fileUnitSize;
		this.fileAllocationType = fileAllocationType;
		if (this.diskSize % this.fileUnitSize != 0) {
			throw new Exception("Invalid File");
		}
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public int getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(int diskSize) {
		this.diskSize = diskSize;
	}

	public int getFileUnitSize() {
		return fileUnitSize;
	}

	public void setFileUnitSize(int fileUnitSize) {
		this.fileUnitSize = fileUnitSize;
	}

	public FileAllocationType getFileAllocationType() {
		return fileAllocationType;
	}

	public void setFileAllocationType(FileAllocationType fileAllocationType) {
		this.fileAllocationType = fileAllocationType;
	}

}
