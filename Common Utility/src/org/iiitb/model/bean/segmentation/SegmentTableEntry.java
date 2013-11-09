package org.iiitb.model.bean.segmentation;

public class SegmentTableEntry
{
    int pid;
    int sid;
    String sName;
    long bAddress;
    long size;
    
    public SegmentTableEntry(int pid, int sid, String sName, long bAddress, long size)
    {
	this.pid = pid;
	this.sid = sid;
	this.sName = sName;
	this.bAddress = bAddress;
	this.size = size;
    }

}
