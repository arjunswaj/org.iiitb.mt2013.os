package org.iiitb.model.bean;

/**
 * Models an indivisible logical sub-unit of main memory({@link Memory}).
 * <p>
 * Typically this implementation should be sub classed to suit the
 * specific memory management scheme being implemented, viz, contiguous,
 * paging or plain segmentation.
 * 
 */
public class IndivisibleMemoryUnit extends MemoryUnit
{
	/**
	 * Number of used bytes in the memory unit.
	 */
	protected long usedBytes;

	/**
	 * Creates an {@code IndivisibleMemoryUnit} object.
	 * @param address The starting address of the memory unit.
	 * @param size The size of the memory unit.
	 * @param allocated Indicates if the memory unit has been allocated or not.
	 * @param usedBytes Number of used bytes in the memory unit.
	 */
	public IndivisibleMemoryUnit(long address, long size, boolean allocated,
			long usedBytes)
	{
		super(address, size, allocated);
		this.usedBytes = usedBytes;
	}
}