package org.iiitb.model.util.test;

import org.iiitb.controller.util.DiskVisualiser;
import org.iiitb.view.DiskSnapshot;

public class DiskVisualizerTest {
	
	public static void main(String[] args) throws Exception{
		
	
	DiskSnapshot snap = new DiskSnapshot(10, 12, 1024);
	DiskVisualiser visulaizer = new DiskVisualiser();
	snap.getDisk().occupySector(1, 1);
		snap.getDisk().occupySector(2, 5);
		
	visulaizer.plot(snap);
	Thread.sleep(2000);
	snap.getDisk().releaseSector(1, 2);
	visulaizer.plot(snap);
	}

}
