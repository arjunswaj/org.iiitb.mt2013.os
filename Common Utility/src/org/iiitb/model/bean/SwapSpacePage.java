package org.iiitb.model.bean;

/**
 * Models a page in the swap space
 * @author prathyusha
 *
 */
public class SwapSpacePage {
	int swapSpaceIdentifier;
	int pageNumber;
	boolean inSwapSpace;
	static int count = 0;
	public SwapSpacePage(int pageNumber)
	{
		swapSpaceIdentifier = ++count;
		this.pageNumber = pageNumber;
	}

}
