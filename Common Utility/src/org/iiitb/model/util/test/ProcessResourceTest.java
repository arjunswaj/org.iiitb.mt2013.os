package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.ProcessResourceVisualizer;
import org.iiitb.controller.util.ResourceGraphVisualiser;
import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.model.bean.ResourceAllocation;
import org.iiitb.view.ProcessSnapshotView;
import org.iiitb.view.ResourceSnapshotView;

public class ProcessResourceTest {
	
	
public static void main(String[] args) throws InterruptedException{

	/**
	 * Allocate resources to processes
	 */
	Resource rA = new Resource(123, "Memory", true, 2);
	Resource rB = new Resource(234, "Disk", true, 3);
	Resource rC = new Resource(345, "CD ROM", false, 3);
	Resource rD = new Resource(456, "Processor", true, 2);
	//Resource rF = new Resource(567, "File", true, 2);
	ResourceAllocation allocate = new ResourceAllocation();
	
	/**
	 * Create processes that would be using the above resources
	 */
	
	ProcessBean p1 = new ProcessBean(1, "process 1");
	ProcessBean p2 = new ProcessBean(2, "process 2");
	ProcessBean p3 = new ProcessBean(3, "process 3");
	
	/**
	 * Create Process and Resource list
	 */
	List<Resource> rList = new ArrayList<Resource>();
	List<ProcessBean> pList = new ArrayList<ProcessBean>();
	List<ProcessBean> blist = new ArrayList<ProcessBean>();

	/**
	 * Add created resources and processes to their respective lists
	 */
	rList.add(rA);
	rList.add(rB);
	rList.add(rC);
	rList.add(rD);

	//pList.add(p1);
	pList.add(p2);
	pList.add(p3);

	ResourceSnapshotView rsnap = new ResourceSnapshotView(rList);
	ProcessSnapshotView psnap = new ProcessSnapshotView(pList,p1,null,0);
	ProcessResourceVisualizer rRenderer = new ProcessResourceVisualizer();
	rRenderer.plotProcess(psnap, rsnap);
	
	
	
	

}

}