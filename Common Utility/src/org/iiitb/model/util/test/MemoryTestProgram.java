package org.iiitb.model.util.test;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemoryUnit;

/**
 * Test program to create a memory model.
 *
 */
public class MemoryTestProgram
{

	public static void main(String s[]) throws InvalidMemoryUnitException
	{
		Memory<MemoryUnit> m = new Memory<MemoryUnit>(1024);

		// Test cases
		// m.add(new MemoryUnit(0, 1024));

		// Add
		 m.add(new MemoryUnit(0, 256));
		 m.add(new MemoryUnit(256, 256));
		 m.add(new MemoryUnit(512, 256));
		 m.add(new MemoryUnit(768, 256));

		// Lesser than 0
		// m.add(new MemoryUnit(-1, 255));

		// Greater than size
		// m.add(new MemoryUnit(1, 1024));

		// Lesser and greater
		// m.add(new MemoryUnit(-100, 924));

		// Overlapping with previous
		// m.add(new MemoryUnit(0, 256));
		// m.add(new MemoryUnit(255, 256));

		// Overlapping with next
		// m.add(new MemoryUnit(0, 256));
		// m.add(new MemoryUnit(512, 256));
		// m.add(new MemoryUnit(256, 300));

		// Overlapping with previous and next
		// m.add(new MemoryUnit(0, 256));
		// m.add(new MemoryUnit(512, 256));
		// m.add(new MemoryUnit(128, 512));

		// Remove
		// m.add(new MemoryUnit(0, 256));
		// m.add(new MemoryUnit(512, 256));
		// m.remove(512);

		// Removing non-existant
		// m.add(new MemoryUnit(0, 256));
		 //m.add(new MemoryUnit(512, 256));
		 //m.remove(768);

		System.out.println(m);
	}

}
