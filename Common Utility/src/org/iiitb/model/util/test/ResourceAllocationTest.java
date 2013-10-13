package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ResourceInstances;
import org.iiitb.view.ResourceSnapshotView;

public class ResourceAllocationTest {
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main( String[] args) throws InterruptedException{
		SnapshotRenderer render = new SnapshotRenderer();
		
		ResourceInstances r1 = new ResourceInstances(1,"processor",true,3);
		ResourceInstances r2 = new ResourceInstances(2,"memory",false,0);
		ResourceInstances r3 = new ResourceInstances(3, "CD-ROM", true, 12);
		ResourceInstances r4 = new ResourceInstances(4, "Floppy", true, 1);

		
		List<ResourceInstances> rlist = new ArrayList<ResourceInstances>();
		
		rlist.add(r1);
		rlist.add(r2);
		rlist.add(r3);
		rlist.add(r4);
		ResourceSnapshotView snap = new ResourceSnapshotView(rlist);
		render.plotResource(snap);
		Thread.sleep(1000);
		
		r1.addInstance(1);
		render.plotResource(snap);

	}

}
