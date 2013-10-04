package org.iiitb.model.bean;

/**
 * Memory units in the memory model start at specific address. These start addresses
 * uniquely identify the associated memory units.<p>
 * This exception is thrown to indicate that there is no memory unit starting at
 * a specified address.
 * 
 */
public class InvalidMemoryUnitAddressException extends Exception
{
	private static final long serialVersionUID = 4288499871706370378L;

	/**
	 * Constructs an {@code InvalidMemoryUnitAddressException} with a
	 * specified detailed message.
	 * 
	 * @param startAddress The address used to access a memory unit.
	 */
	public InvalidMemoryUnitAddressException(long startAddress)
	{
		super("No partition starts at " + startAddress);
	}

}
