package org.iiitb.model.bean;

/**
 * A {@code MemoryUnit} models a logical sub-unit of main memory({@link Memory}
 * ).
 * 
 */
public class MemoryUnit
{
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

	/**
	 * Create a {@code MemoryUnit} object
	 * 
	 * @param address Start address of the memory unit.
	 * @param size Size of memory unit
	 * @param allocated Flag indicating whether this memory unit has been
	 *            allocated or not.
	 */
	MemoryUnit(long address, long size, boolean allocated)
	{
		this.size = size;
		this.address = address;
		this.allocated = allocated;
	}
}
