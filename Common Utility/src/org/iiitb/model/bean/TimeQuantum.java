package org.iiitb.model.bean;

import org.iiitb.model.consts.BurstType;

public class TimeQuantum {

	BurstType type;
	long quantum;
	
	public long getQuantum()
	{
		return quantum;
	}
	
	public void setType(BurstType type)
	{
		this.type = type;
	}
	
	public void setQuantum(long quantum)
	{
		this.quantum = quantum;
	}
}
