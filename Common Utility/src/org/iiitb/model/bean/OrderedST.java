package org.iiitb.model.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * A linked list based ordered symbol table. The keys must be of type
 * {@link MemoryUnit}. The plan is to upgrade this to a red-black tree
 * 
 */
class OrderedST<T extends MemoryUnit>
{

	private Node head;

	/**
	 * Creates an empty unallocated area spanning 'size'
	 */
	OrderedST(long size)
	{
		head = new Node(0, size, MemoryUnit.FREE, null);
	}

	/**
	 * Inserts a memory unit in the memory model
	 * 
	 * @param m The memory unit to be inserted
	 * @throws InvalidMemoryUnitAllocationRequestException If the memory at the
	 *             requested address is already allocated
	 */
	void put(MemoryUnit m) throws InvalidMemoryUnitAllocationRequestException
	{
		Node mNode = new Node(m, null);
		boolean inserted = false;

		for (Node cNode = head, pNode = null; cNode != null; cNode = cNode.next)
		{
			MemoryUnit c = cNode.memUnit;
			if (c.allocated == MemoryUnit.ALLOCATED)
			{
				pNode = cNode;
				continue;
			}
			if (m.address >= c.address + c.size)
			{
				pNode = cNode;
				continue;
			}
			if (m.address < c.address)
				break; // No holes at required address. Have pulled ahead of
						// that in search of free memory chunks
			if (m.address >= c.address && m.address <= c.address + c.size
					&& m.address + m.size > c.address + c.size)
				break; // No holes. The available chunk at the required address
						// is not big enough

			/**
			 * 'c' can accommodate 'm'
			 */

			// Will there be a hole after 'm'?
			Node tNode = cNode.next;
			cNode.next = mNode;
			if ((c.address + c.size) - (m.address + m.size) > 0)
				mNode.next = new Node(new MemoryUnit(m.address + m.size,
						(c.address + c.size) - (m.address + m.size),
						MemoryUnit.FREE), tNode);
			else
				mNode.next = tNode;

			// Will there be a hole before 'm'?
			if (m.address == c.address)
			{// No hole before 'm'
				if (pNode != null)
					pNode.next = mNode;
				else
					head = mNode;
			}
			else
				c.size -= (c.address + c.size - m.address);

			inserted = true;
			break;
		}

		if (!inserted)
			throw new InvalidMemoryUnitAllocationRequestException(
					mNode.memUnit, toString());
	}

	/**
	 * Deallocates a memory unit in the memory model and collates it with
	 * neighbouring free memory units
	 * 
	 * @param address The handler to the memory unit that is to be deallocated
	 * @throws InvalidMemoryUnitAddressException
	 */
	public void delete(long address) throws InvalidMemoryUnitAddressException
	{
		boolean muAddress = false;
		for (Node c = head, p = null, pp = null; c != null; pp = p, p = c, c = c.next)
		{
			if (c.memUnit.address != address)
				continue;

			muAddress = true;

			c.memUnit.allocated = MemoryUnit.FREE;

			// merge with previous memory unit if it is free
			if (p != null)
			{
				if (p.memUnit.allocated)
					break;
				if (pp != null)
				{
					assert (pp.memUnit.allocated);
					pp.next = c;
				}
				else
				{
					assert (head == p);
					head = c;
				}
				c.memUnit.address = p.memUnit.address;
				c.memUnit.size += p.memUnit.size;
			}
			else
				head = c;

			// Merge with next memory unit if possible
			if (c.next == null)
				break;
			if (c.next.memUnit.allocated)
				break;
			c.memUnit.size += c.next.memUnit.size;
			c.next = c.next.next;
		}

		if (!muAddress)
			throw new InvalidMemoryUnitAddressException(address);
	}

	/**
	 * Retrieves a specific memory unit.
	 * 
	 * @param address The start address at which the required memory unit has
	 *            been allocated
	 * @return The associated memory unit
	 */
	public MemoryUnit get(long address)
	{
		for (Node nd = head; nd != null; nd = nd.next)
		{
			MemoryUnit m = nd.memUnit;
			if (m.address == address)
			{
				if (m.allocated == MemoryUnit.ALLOCATED)
					return nd.memUnit;
				break;
			}
		}

		return null;
	}

	/**
	 * Constructs the list of all memory units within the specified
	 * range
	 * 
	 * @param startAddress The start address of the range
	 * @param endAddress The end address of the range
	 * @return An iterable list of allocated memory units
	 */
	public Iterable<MemoryUnit> keys(long startAddress, long endAddress)
	{
		List<MemoryUnit> c = new LinkedList<MemoryUnit>();
		for (Node nd = head; nd != null; nd = nd.next)
		{
			MemoryUnit m = nd.memUnit;
			if (m.address >= startAddress)
				c.add(m);
			if (m.address > endAddress)
				break;
		}
		return c;
	}

	public Iterable<MemoryUnit> keys()
	{
		List<MemoryUnit> c = new LinkedList<MemoryUnit>();
		for (Node nd = head; nd != null; nd = nd.next)
			c.add(nd.memUnit);
		return c;
	}

	// Could have had a linked list of "MemoryUnit". But that would have meant
	// exposing the ordered symbol table('next') to classes which subclass
	// 'Memoryunit'
	private static class Node
	{
		private Node next;
		private MemoryUnit memUnit;

		private Node(long address, long size, boolean allocated, Node next)
		{
			memUnit = new MemoryUnit(address, size, allocated);
			this.next = next;
		}

		private Node(MemoryUnit m, Node next)
		{
			memUnit = m;
			this.next = next;
		}
	}

	public String toString()
	{
		String s = new String("Current Memory allocation status\n");
		s += "-----------------\n";
		for (MemoryUnit mu : keys())
		{
			s += mu.address + "\n";

			if (mu.allocated)
				s += "\tALLOCATED\n";
			else
				s += "\tFREE\n";
			s += (mu.address + mu.size - 1) + "\n";
			s += "-----------------\n";
		}
		return s;
	}

	public static void main(String s[])
			throws InvalidMemoryUnitAllocationRequestException
	{
		int size = 1024;
		OrderedST<MemoryUnit> st = new OrderedST<MemoryUnit>(size);

		// st.put(new MemoryUnit(0, 1024, MemoryUnit.ALLOCATED));

		/*
		 * // No holes at required address
		 * st.put(new MemoryUnit(0, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(256, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(512, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(768, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(1000, 100, MemoryUnit.ALLOCATED));
		 */

		/*
		 * // Available chunk at address is not big enough
		 * st.put(new MemoryUnit(0, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(512, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(256, 512, MemoryUnit.ALLOCATED));
		 */

		/*
		 * // No holes behind
		 * // No hole after
		 * // Holes before and after
		 * st.put(new MemoryUnit(0, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(256, 44, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(350, 100, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(700, 68, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(768, 256, MemoryUnit.ALLOCATED));
		 */

		/*
		 * 
		 * //delete
		 * st.put(new MemoryUnit(0, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(256, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(512, 256, MemoryUnit.ALLOCATED));
		 * st.put(new MemoryUnit(768, 256, MemoryUnit.ALLOCATED));
		 * // st.delete(0);
		 * st.delete(256);
		 * st.delete(512);
		 * st.delete(768);
		 */

		System.out.println(st);
	}

}
