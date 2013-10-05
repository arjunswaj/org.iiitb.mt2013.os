package org.iiitb.model.bean;

import org.iiitb.model.consts.ResourceType;

public class MemoryUnit extends Resource {

	protected long startAddress;
	protected long size;	

	
	public MemoryUnit(int rid, String resourceName, boolean availability,
			int ownerPid, ResourceType rType, long startAddress, long size) {
		super(rid, resourceName, availability, ownerPid, rType);
		this.startAddress = startAddress;
		this.size = size;		
	}

	public long getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(long startAddress) {
		this.startAddress = startAddress;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
