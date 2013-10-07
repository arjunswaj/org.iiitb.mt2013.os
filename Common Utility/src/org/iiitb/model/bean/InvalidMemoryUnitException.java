package org.iiitb.model.bean;

/**
 * Memory partitions in a memory model start at specific addresses which
 * uniquely identify them.
 * <p>
 * This exception is thrown to indicate that there is no memory partition
 * starting at the specified address.
 * 
 */
public class InvalidMemoryUnitException extends Exception
{
	private static final long serialVersionUID = 4288499871706370378L;

	/**
	 * Constructs an {@code InvalidMemoryUnitAddressException} with a
	 * specified detailed message.
	 * 
	 * @param startAddress The address used to access a memory partition.
	 */
	public InvalidMemoryUnitException(MemoryUnit mu,
			String memoryPartitionStatus)
	{
		super("A partition cannot span from " + mu.address + " to "
				+ (mu.address + mu.size) + "\nCurrent memory model status\n"
				+ memoryPartitionStatus);
	}

	public InvalidMemoryUnitException(long address, String memoryPartitionStatus)
	{
		super("No partition exists at " + address
				+ "\nCurrent memory model status\n" + memoryPartitionStatus);

	}

}
