package org.iiitb.model.bean;

/**
 * Thrown to indicate that an attempt to allocate memory has failed.
 */
public class InvalidMemoryUnitAllocationRequestException extends Exception
{
	private static final long serialVersionUID = 5389194961126301981L;

	/**
	 * Constructs an {@code InvalidMemoryUnitAllocationRequestException} with a
	 * specified detail message.
	 * 
	 * @param mu The memory unit whose allocation resulted in this exception.
	 * @param memAllocationStatus A string containing the current memory
	 *            allocation status.
	 */
	public InvalidMemoryUnitAllocationRequestException(MemoryUnit mu,
			String memAllocationStatus)
	{
		super("\nRequested memory allocation : " + mu.address + " to "
				+ (mu.address + mu.size) + "\n" + memAllocationStatus);

	}

}
