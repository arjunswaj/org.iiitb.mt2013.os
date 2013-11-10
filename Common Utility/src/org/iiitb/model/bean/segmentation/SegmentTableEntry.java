package org.iiitb.model.bean.segmentation;

public class SegmentTableEntry
{
    private int pid;
    private int sid;
    String sName;
    private long bAddress;
    private long size;
    
    public SegmentTableEntry(int pid, int sid, String sName, long bAddress, long size)
    {
	this.setPid(pid);
	this.setSid(sid);
	this.sName = sName;
	this.setbAddress(bAddress);
	this.setSize(size);
    }

	/**
	 * @return the pid
	 */
	public int getPid()
	{
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid)
	{
		this.pid = pid;
	}

	/**
	 * @return the sid
	 */
	public int getSid()
	{
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid)
	{
		this.sid = sid;
	}

	/**
	 * @return the bAddress
	 */
	public long getbAddress()
	{
		return bAddress;
	}

	/**
	 * @param bAddress the bAddress to set
	 */
	public void setbAddress(long bAddress)
	{
		this.bAddress = bAddress;
	}

	/**
	 * @return the size
	 */
	public long getSize()
	{
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size)
	{
		this.size = size;
	}

}
