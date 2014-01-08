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
		// Resource rF = new Resource(567, "File", true, 2);
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
		render.plotResource(snap, "Resource and process snapshot of the system");
		Thread.sleep(2000);

		// Allocate single instance of resource 123 to p1
		allocate.issueInstance(p1, rA);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rA.getResourceName()
				+ " is allocated to p1");

		Thread.sleep(2000);

		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");

		Thread.sleep(2000);

		// Deallocate single instance of resource 234 from p1
		allocate.relinquishInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is deallocated from p1");

		Thread.sleep(2000);

		// Make resource rC available
		rC.setAvailability(true);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, rC.getResourceName() + " is available now");

		Thread.sleep(2000);

		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");
		Thread.sleep(2000);

		// Allocate single instance of resource 345 to p1
		allocate.issueInstance(p1, rC);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rC.getResourceName()
				+ " is allocated to p1");

		Thread.sleep(2000);

		// Allocate single instance of resource 345 to p2
		allocate.issueInstance(p2, rC);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rC.getResourceName()
				+ " is allocated to p2");

		Thread.sleep(2000);

		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");

		Thread.sleep(1000);

		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");

		Thread.sleep(1000);
		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");
		
		Thread.sleep(1000);
		// Allocate single instance of resource 234 to p1
		allocate.issueInstance(p1, rB);
		snap = new ResourceSnapshotView(rList, pList);
		render.plotResource(snap, "One instance of " + rB.getResourceName()
				+ " is allocated to p1");

	}

}
