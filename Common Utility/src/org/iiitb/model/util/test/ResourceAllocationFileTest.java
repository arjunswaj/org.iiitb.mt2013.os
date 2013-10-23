package org.iiitb.model.util.test;

import java.io.File;
import java.io.IOException;

import org.iiitb.controller.util.ResourceGraphVisualiser;

public class ResourceAllocationFileTest {
	
	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {
		File fileName = new File("resourceSnapshot .csv");
		ResourceGraphVisualiser snap = new ResourceGraphVisualiser();
		snap.plotGraph(fileName);
		
	}

}
