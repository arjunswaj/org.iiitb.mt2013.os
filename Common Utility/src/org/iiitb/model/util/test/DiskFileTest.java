package org.iiitb.model.util.test;

import java.io.File;
import java.io.IOException;

import org.iiitb.controller.util.DiskVisualiser;

public class DiskFileTest {

	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		File DiskFile = new File("DiskFile.csv");
		DiskVisualiser snap = new DiskVisualiser();
		snap.readDiskFromFile(DiskFile);
	
	}
}
