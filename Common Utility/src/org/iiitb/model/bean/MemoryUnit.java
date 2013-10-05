package org.iiitb.model.bean;

import org.iiitb.model.consts.ResourceType;

/**
 * A {@code MemoryUnit} models a logical sub-unit of main memory({@link Memory}
 * ).
 * 
 */
public class MemoryUnit extends Resource {

	protected long startAddress;

	/**
	 * Start address of the memory unit.
	 */
	protected long address;
	/**
	 * Indicates whether the memory unit has been allocated or not.
	 */
	protected boolean allocated;
	/**
	 * Size of the memory unit.
	 */
	protected long size;

	public final static boolean ALLOCATED = true;
	public final static boolean FREE = false;

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

	/**
	 * Create a {@code MemoryUnit} object
	 * 
	 * @param address
	 *            Start address of the memory unit.
	 * @param size
	 *            Size of memory unit
	 * @param allocated
	 *            Flag indicating whether this memory unit has been allocated or
	 *            not.
	 */
	MemoryUnit(long address, long size, boolean allocated) {
		this.size = size;
		this.address = address;
		this.allocated = allocated;
	}
}
