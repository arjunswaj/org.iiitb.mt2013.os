package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.ResourceGraphVisualiser;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.model.bean.ResourceAllocation;
import org.iiitb.view.ResourceSnapshotView;

public class ResourceAllocationTest {

	/**
	 * @author anvith
	 * @param args
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {
		/**
		 * Create Process and Resource list
		 */
		List<Resource> rList = new ArrayList<Resource>();
		List<ProcessBean> pList = new ArrayList<ProcessBean>();

		/**
		 * Create processes that would be using the above resources
		 */
		ProcessBean p1 = new ProcessBean(1, "process 1");
		ProcessBean p2 = new ProcessBean(2, "process 2");

		/**
		 * Allocate resources to processes
		 */
		Resource rA = new Resource(123, "Memory", true, 2);
		Resource rB = new Resource(234, "Disk", true, 3);
		Resource rC = new Resource(345, "CD ROM", false, 3);
		Resource rD = new Resource(456, "Processor", true, 2);
		ResourceAllocation allocate = new ResourceAllocation();

		/**
		 * Add created resources and processes to their respective lists
		 */

		rList.add(rA);
		rList.add(rB);
		rList.add(rC);
		rList.add(rD);
		
		pList.add(p1);
		pList.add(p2);

		/*
		 * //** create the snapshot of the resources and process
		 */
		ResourceGraphVisualiser render = new ResourceGraphVisualiser();
		ResourceSnapshotView snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap);
		Thread.sleep(1000);

		// Allocate single instance of resource 123 to p1
		allocate.issueInstance(p1, rA);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap);

		Thread.sleep(1000);

		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap);

		Thread.sleep(1000);

		// Deallocate single instance of resource 234 from p1
		allocate.relinquishInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap);

		Thread.sleep(1000);

		rC.setAvailability(true);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap);

		/*
		 * Thread.sleep(1000);
		 * 
		 * rC.addInstance(); snap = new ResourceSnapshotView(rlist, plist);
		 * render.plotResource(snap);
		 * 
		 * Thread.sleep(1000);
		 * 
		 * rD.removeInstance(); snap = new ResourceSnapshotView(rlist, plist);
		 * render.plotResource(snap);
		 */
	}

}
