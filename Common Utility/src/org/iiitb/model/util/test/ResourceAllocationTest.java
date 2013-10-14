package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.ResourceInstances;
import org.iiitb.view.ResourceSnapshotView;

public class ResourceAllocationTest {
	
	/**
	 * @author anvith
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main( String[] args) throws InterruptedException{
		
		/**
		 * create resources along with the number of instances of each resource
		 */
		
		ResourceInstances r1 = new ResourceInstances(1,"processor",true,3);
		ResourceInstances r2 = new ResourceInstances(2,"memory",false,0);
		ResourceInstances r3 = new ResourceInstances(3, "CD-ROM", true, 12);
		ResourceInstances r4 = new ResourceInstances(4, "Floppy", true, 1);
		
		/**
		 * create processes that would be using the above resources
		 */
		ProcessBean p1 = new ProcessBean(1,"process 1");
		ProcessBean p2 = new ProcessBean(2,"process 2");

		/**
		 * add created resources and processes to their respective lists
		 */
		List<ResourceInstances> rlist = new ArrayList<ResourceInstances>();
		List<ProcessBean> plist = new ArrayList<ProcessBean>();
		
		rlist.add(r1);
		rlist.add(r2);
		rlist.add(r3);
		rlist.add(r4);
		plist.add(p1);
		plist.add(p2);
		
		/**
		 * create the snapshot of the resources and process
		 */
		SnapshotRenderer render = new SnapshotRenderer();
		ResourceSnapshotView snap = new ResourceSnapshotView(rlist,plist);
		render.plotResource(snap);
		Thread.sleep(1000);
		
		r1.addInstance();
				
		r3.issueInstance(p1);
				
		r4.issueInstance(p1);
		
		r2.issueInstance(p2);
		
		r2.issueInstance(p1);
		
		r4.issueInstance(p1);
		
		r2.addInstance();
		
		r4.issueInstance(p2);
		
		r4.addInstance();
		
		r1.addInstance();


		render.plotResource(snap);
	}

}
