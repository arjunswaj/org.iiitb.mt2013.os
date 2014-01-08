package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.ProcessResourceVisualizer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.model.bean.ResourceAllocation;
import org.iiitb.view.ResourceProcessView;


public class ProcessResourceTest {
	
	
public static void main(String[] args) throws InterruptedException{

	/**
	 * Allocate resources to processes
	 */
	Resource rA = new Resource(123, "Memory", true, 2);
	Resource rB = new Resource(234, "Disk", true, 3);
	Resource rC = new Resource(345, "CD ROM", false, 3);
	Resource rD = new Resource(456, "Processor", true, 2);
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

	
	ResourceProcessView psnap = new ResourceProcessView(pList,p1,null,0);
	ProcessResourceVisualizer rRenderer = new ProcessResourceVisualizer();
	
	//allocate.requestInstance(p1, rA);
	allocate.issueInstance(p1, rA);	
	rRenderer.plotProcess(psnap,"Adding " + rA.getResourceName()+" to "+p1.getpName());
	Thread.sleep(2500);
	//allocate.requestInstance(p1, rB);
	allocate.issueInstance(p1, rB);
	rRenderer.plotProcess(psnap,"Adding " + rB.getResourceName()+" to "+p1.getpName());
	Thread.sleep(2500);
	//allocate.requestInstance(p1, rD);
	allocate.issueInstance(p1, rD);
	rRenderer.plotProcess(psnap,"Adding " + rD.getResourceName()+" to "+p1.getpName());
	Thread.sleep(2500);
	//allocate.requestInstance(p1, rA);
	allocate.issueInstance(p1, rA);
	rRenderer.plotProcess(psnap,"Adding " + rA.getResourceName()+" to "+p1.getpName());
	Thread.sleep(2500);
	
	
	blist.add(p1);
	pList.remove(p2);
	rRenderer.plotProcess(psnap,"Adding " + p1.getpName()+" to blocked queue"+"\n"+"Removing " + p2.getpName()+" from ready queue");
	psnap = new ResourceProcessView(pList,p2,blist,3);
	Thread.sleep(2500);
	allocate.issueInstance(p2, rD);
	rRenderer.plotProcess(psnap,"Adding " + rD.getResourceName()+" to "+p2.getpName());
	Thread.sleep(2500);
	
	blist.add(p2);
	blist.remove(p1);
	rRenderer.plotProcess(psnap,"Adding " + p2.getpName()+" to blocked queue"+"\n"+"Removing " + p1.getpName()+" from blocked queue");
	psnap = new ResourceProcessView(pList,p1,blist,6);
	Thread.sleep(2500);
	allocate.relinquishInstance(p1, rD);
	rRenderer.plotProcess(psnap,"Removing "+ rD.getResourceName() + " from "+ p1.getpName());
	Thread.sleep(2500);
	
	blist.add(p1);
	pList.remove(p3);
	rRenderer.plotProcess(psnap,"Adding " + p1.getpName()+" to blocked queue"+"\n"+"Removing " + p3.getpName()+" from ready queue");
	psnap = new ResourceProcessView(pList,p3,blist,6);
	rRenderer.plotProcess(psnap,"No resources allocated to the process");
	

}

}