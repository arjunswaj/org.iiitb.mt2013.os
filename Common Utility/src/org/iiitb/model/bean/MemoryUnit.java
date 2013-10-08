package org.iiitb.model.bean;

import org.iiitb.model.consts.ResourceType;

/**
 * A {@code MemoryUnit} models a logical partition of main memory(
 * {@link Memory} ).
 * <p>
 * Typically this implementation should be sub classed to suit the specific
 * memory management scheme being implemented, viz, paging, segmentation or
 * contiguous memory management schemes.
 * 
 */
public class MemoryUnit extends Resource
{
	/**
	 * Start address of the memory partition.
	 */
	protected long address;

	/**
	 * Size of the memory partition.
	 */
	protected long size;
	
	public long getAddress()
	{
		return address;
	}

	public long getSize()
	{
		return size;
	}

	public MemoryUnit(int rid, String resourceName, boolean availability,
			int ownerPid, ResourceType rType, long startAddress, long size)
	{
		super(rid, resourceName, availability, ownerPid, rType);
		this.address = startAddress;
		this.size = size;
	}
	/**
	 * Create a {@code MemoryUnit} object representing an unallocated memory
	 * partition.
	 * 
	 * @param address Address of the memory partition.
	 * @param size Size of memory partition
	 * 
	 */
	public MemoryUnit(long address, long size)
	{
		this.address = address;
		this.size = size;
	}

	/**
	 * Returns a string representation of this memory partition.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(address).append("\n");
		sb.append("\t").append(size).append("\n");
		sb.append((address + size - 1)).append("\n");
		sb.append("++++++++++++++++++\n");
		return sb.toString();
	}
}