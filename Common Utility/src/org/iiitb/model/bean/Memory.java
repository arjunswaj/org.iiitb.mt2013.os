package org.iiitb.model.bean;

/**
 * Simulation of memory management in contiguous, paging and segmentation
 * schemes all require an abstraction of the main memory.
 * <p>
 * This class models the above mentioned abstraction. It can be logically seen
 * as being composed of several <b>memory units</b>({@link MemoryUnit}).
 * <p>
 * The main memory model is internally implemented as an ordered symbol table of
 * these {@link MemoryUnit}'s.
 * 
 * 
 */
public class Memory
{
	private OrderedST<MemoryUnit> st;

	/**
	 * Size of memory model.
	 */
	protected long size;

	/**
	 * Creates a memory model of the specified size. The entire memory now
	 * consists of a single unallocated memory unit({@link MemoryUnit}).
	 * 
	 * @param size The specified size of the memory model.
	 */
	public Memory(long size)
	{
		this.size = size;
		st = new OrderedST<MemoryUnit>(size);
	}

	/**
	 * Insert a memory unit into the memory model.
	 * 
	 * @param mu The memory unit to be inserted
	 * @throws InvalidMemoryUnitAllocationRequestException The specified
	 *             {@link MemoryUnit} is invalid. One of the following things
	 *             could be the cause <br>
	 *             1. No holes(free chunks) at required address<br>
	 *             2. The hole at the required address is not big enough<br>
	 */
	public void insert(MemoryUnit mu)
			throws InvalidMemoryUnitAllocationRequestException
	{
		st.put(mu);
	}

	/**
	 * Deallocates the memory unit associated with a start address and
	 * collates it with neighbouring unallocated memory units.
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
	 * Marks an associated {@link MemoryUnit} as free without collating it with
	 * neighbouring free memory units.
	 * 
	 * @param startAddress The address of the memory unit that is to be marked
	 *            as
	 *            unallocated
	 */
	public void unallocate(long startAddress)
	{
		st.get(startAddress).allocated = MemoryUnit.FREE;
	}

	/**
	 * Retrieves the memory unit associated with a start address.
	 * 
	 * @param startAddress The start address of the required memory unit.
	 * @return The associated memory unit object or {@code null} if there is no
	 *         memory unit associated with the specified start address.
	 */
	public MemoryUnit get(long startAddress)
	{
		return st.get(startAddress);
	}

	/**
	 * Returns an iterator over the list of all memory units within the
	 * specified address range of this memory model. The memory units are
	 * ordered by their start addresses.
	 * 
	 * @param startAddress The start address of the range.
	 * @param endAddress The end address of the range
	 * @return An iterable list of memory units of this memory model within the
	 *         specified address range.
	 */
	public Iterable<MemoryUnit> getAll(long startAddress, long endAddress)
	{
		return st.keys(startAddress, endAddress);
	}

	/**
	 * Returns an iterator over the list of all memory units of this memory
	 * model. The memory units are ordered by their start addresses.
	 * 
	 * @return An iterable list of memory units of this memory model.
	 */
	public Iterable<MemoryUnit> getAll()
	{
		return st.keys(0, size);
	}
}
