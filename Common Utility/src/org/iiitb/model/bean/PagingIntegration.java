package org.iiitb.model.bean;

public class PagingIntegration {
	
	SwapSpace swapSpace;
	public PagingIntegration()
	{
		// Create a list of processes
		// Create a swap space
		// Initialize paged memory // To do : tie up memory view of page table modules and page replacement modules
		// Initiate the scheduling algorithm with this list of process objects.
	}
	
	/**
	 * This method is registered with the scheduling module to be called when a process is set to run
	 */
	public void simulateExecutionOfProcess(ProcessBean p)
	{
		// Create a file mimicking the size of the logical address space(p.logicalAddressSpaceSize.
		// Create an object of type page table with this file as the input.
		// Get the pages from the page table object. Populate the swap space with them
		
		// Mimic the execution of the process from the paging system perspective, i.e., generate valid memory references(Random Number generator).
		// For each memory reference
			// Make a query to the page table
			// if (HIT)
				// I have the physical address.
				// Update the memory view. Blink the associated memory location(line/byte)
			// else // MISS – 4
				// mark the associated page in the swap space object as not being in the swap space.
				// Call page replacement algorithm with this page

	}
	
	

}
