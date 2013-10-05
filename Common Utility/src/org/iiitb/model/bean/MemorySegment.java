package org.iiitb.model.bean;

import org.iiitb.model.consts.ResourceType;

public class MemorySegment extends MemoryUnit {

	protected long segmentNumber;
	protected String segmentName;

	public MemorySegment(int rid, String resourceName, boolean availability,
			int ownerPid, ResourceType rType, long startAddress, long size,
			long segmentNumber, String segmentName) {
		super(rid, resourceName, availability, ownerPid, rType, startAddress,
				size);
		this.segmentNumber = segmentNumber;
		this.segmentName = segmentName;
	}

	public long getSegmentNumber() {
		return segmentNumber;
	}

	public void setSegmentNumber(long segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

}
