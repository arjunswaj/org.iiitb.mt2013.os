package org.iiitb.model.bean;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Simulation of memory management in contiguous, paging and segmentation
 * schemes all require an abstraction of the main memory.
 * <p>
 * This class models the above mentioned abstraction. It can be logically seen
 * as being composed of several <b>memory partitions</b>({@link MemoryUnit} ).
 */
public class Memory<MU extends MemoryUnit>
{
	private TreeMap<Long, MU> st;
	private long size;

	public long getSize() {
		return size;
	}
	/**
	 * Creates a memory model of the specified size.
	 * 
	 * @param size The specified size of the memory model.
	 */
	public Memory(long size)
	{
		st = new TreeMap<Long, MU>();
		this.size = size;
	}

	/**
	 * Adds memory units({@link MemoryUnit}) to this memory model.
	 * 
	 * @param mu The memory unit to be added
	 * @throws InvalidMemoryUnitException Thrown if {@code mu} overlaps any of
	 *             the existing memory units in the memory model.
	 */
	public void add(MU mu) throws InvalidMemoryUnitException
	{
		Entry<Long, MU> p = st.lowerEntry(mu.address);
		if (p != null) // There is a MU previous to mu
		{
			MU prev = p.getValue();
			if (prev.address + prev.size > mu.address)
				throw new InvalidMemoryUnitException(mu, toString());
		}
		if (mu.address < 0)
			throw new InvalidMemoryUnitException(mu, toString());
		// Now 'mu' does not overlap with the previous partition

		Entry<Long, MU> n = st.higherEntry(mu.address + mu.size - 1);
		if (n != null)
		{
			MU next = n.getValue();
			if (next.address < mu.address + mu.size)
				throw new InvalidMemoryUnitException(mu, toString());
		}
		if (mu.address + mu.size > size)
			throw new InvalidMemoryUnitException(mu, toString());
		// 'mu' does not overlap with the next partition either

		// Thus can insert
		st.put(mu.address, mu);
	}

	/**
	 * Removes a memory unit({@link MemoryUnit}) from this memory model.
	 * 
	 * @param address Address of the memory unit to be removed.
	 * @return The memory unit that was just removed from the memory model.
	 * @throws InvalidMemoryUnitException Thrown if the specified address
	 *             specifies a non-existent memory model.
	 */
	public MU remove(long address) throws InvalidMemoryUnitException
	{
		MU mu = st.remove(address);
		if (mu == null)
			throw new InvalidMemoryUnitException(address, toString());
		return mu;
	}

	/**
	 * Retrieves memory unit({@link MemoryUnit}) associated with an address.
	 * @param address The address of a memory unit.
	 * @return The memory unit associated with the specified address.
	 */
	public MU get(long address)
	{
		return st.get(address);
	}

	/**
	 * Returns an iterator over the list of all memory units(
	 * {@link MemoryUnit}) of this memory
	 * model, ordered by their addresses.
	 * 
	 * @return An iterable list of memory units of this memory model.
	 */
	public Iterable<MU> getAll()
	{
		return st.values();
	}

	/**
	 * Returns a string representation of this memory model. The string
	 * representation consists of string representations of all memory
	 * units contained in this memory model in the order they are returned
	 * by its iterator.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (MemoryUnit mu : getAll())
			sb.append(mu.toString());
		return sb.toString();
	}
}