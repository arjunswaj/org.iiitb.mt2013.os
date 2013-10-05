package org.iiitb.model.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Models a logical sub-unit of memory{@link Memory}) that is further divisible.
 * It is composed of sub-units({@link IndivisibleMemoryUnit}) which are
 * indivisible.
 * <p>
 * Typically this class should be sub classed to suit the specific memory
 * management scheme being designed, viz, paged segmentation, segmented paging
 * or kernel memory management scheme
 */
public class DivisibleMemoryUnit extends MemoryUnit
{
	private OrderedST<IndivisibleMemoryUnit> st;

	/**
	 * Initialises a {@code DivisibleMemoryUnit} object.
	 * 
	 * The memory unit this object represents can be seen as capable of further
	 * division into {@code IndivisibleMemoryUnit}'s. Right now, it consists of
	 * just one {@link IndivisibleMemoryUnit} object which spans
	 * the entire size
	 * 
	 * @param address Start address
	 * @param size Size
	 * @param allocated Indicates if this memory unit has been allocated or not.
	 */
	public DivisibleMemoryUnit(long address, long size, boolean allocated)
	{
		super(address, size, allocated);
		st = new OrderedST<IndivisibleMemoryUnit>(size);
	}

	/**
	 * Insert an indivisible memory unit into this divisible memory unit.
	 * 
	 * @param mu The memory unit to be inserted.
	 * @throws InvalidMemoryUnitAllocationRequestException The specified
	 *             {@link MemoryUnit} is invalid. One of the following things
	 *             could be the cause <br>
	 *             1. No holes(free chunks) at required address<br>
	 *             2. The hole at the required address is not big enough<br>
	 */
	public void insert(IndivisibleMemoryUnit mu)
			throws InvalidMemoryUnitAllocationRequestException
	{
		st.put(mu);
	}

	/**
	 * Deallocates the indivisible memory unit associated with a start address
	 * and
	 * collates it with neighbouring unallocated indivisible memory units.
	 * 
	 * @param startAddress The start address of the memory unit that is to be
	 *            deallocated
	 * @throws InvalidMemoryUnitAddressException The given start address does
	 *             not specify the start of a memory unit.
	 */
	public void remove(long startAddress)
			throws InvalidMemoryUnitAddressException
	{
		st.delete(startAddress);
	}

	/**
	 * Marks an associated {@link IndivisibleMemoryUnit} as free without
	 * collating it with
	 * neighbouring free indivisible memory units.
	 * 
	 * @param startAddress The address of the indivisible memory unit that is to
	 *            be marked as unallocated
	 */
	public void unallocate(long startAddress)
	{
		st.get(startAddress).allocated = MemoryUnit.FREE;
	}

	/**
	 * Retrieves the indivisible memory unit associated with a start address.
	 * 
	 * @param startAddress The start address of the required indivisible memory
	 *            unit.
	 * @return The associated indivisible memory unit object or {@code null} if
	 *         there is no
	 *         indivisible memory unit associated with the specified start
	 *         address.
	 */
	public IndivisibleMemoryUnit get(long startAddress)
	{
		return (IndivisibleMemoryUnit) st.get(startAddress);
	}

	/**
	 * Returns an iterator over the list of all indivisible memory units within
	 * the
	 * specified address range of this object. The memory units are
	 * ordered by their start addresses.
	 * 
	 * @param startAddress The start address of the range.
	 * @param endAddress The end address of the range
	 * @return An iterable list of indivisible memory units of this object
	 *         within the
	 *         specified address range.
	 */
	public Iterable<IndivisibleMemoryUnit> getAll(long startAddress,
			long endAddress)
	{
		List<IndivisibleMemoryUnit> lst = new LinkedList<IndivisibleMemoryUnit>();
		for (MemoryUnit mu : st.keys(startAddress, endAddress))
			lst.add((IndivisibleMemoryUnit) mu);
		return lst;
	}

	/**
	 * Returns an iterator over the list of all indivisible memory units of this
	 * object. The memory units are ordered by their start addresses.
	 * 
	 * @return An iterable list of indivisible memory units.
	 */
	public Iterable<IndivisibleMemoryUnit> getAll()
	{
		return getAll(0, size);
	}

}
