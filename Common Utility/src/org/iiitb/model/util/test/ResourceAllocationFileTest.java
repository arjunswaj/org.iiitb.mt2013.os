package org.iiitb.model.util.test;

import java.io.File;
import java.io.IOException;

import org.iiitb.controller.util.ResourceGraphVisualiser;
//import org.iiitb.controller.util.ResourceGraphVisualiser.exceptionList;


public class ResourceAllocationFileTest {
	
	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {
		File rfileName = new File("resourceSnapshot.csv");
		//File pfileName = new File("processSnapshot.csv");
		ResourceGraphVisualiser snap = new ResourceGraphVisualiser();
		snap.plotGraph(3,3,rfileName);
		
	}

}
