package org.iiitb.model.bean;
/**
 * 
 */

/**
 * @author kempa
 *
 */
public class SegmentedMemoryReference
{
	int segmentId;
	long offset;
	
	public SegmentedMemoryReference(int segmentId, long offset)
	{
		this.segmentId = segmentId;
		this.offset = offset;
	}

}
