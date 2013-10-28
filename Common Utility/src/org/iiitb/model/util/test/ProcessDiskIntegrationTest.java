package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.DiskVisualiser;
import org.iiitb.controller.util.ProcessDiskVisualizer;
import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.view.DiskSnapshot;
import org.iiitb.view.ProcessSnapshotView;

public class ProcessDiskIntegrationTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		DiskSnapshot diskSnap = new DiskSnapshot(10, 12, 1024);
		ProcessBean p1 = new ProcessBean(1, "Process1");
		SnapshotRenderer render = new SnapshotRenderer();
		ProcessDiskVisualizer dRenderer = new ProcessDiskVisualizer();
		ProcessBean p2 = new ProcessBean(2, "Process 2");
		ProcessBean p3 = new ProcessBean(3, "Process 3");
		ProcessBean p4 = new ProcessBean(4, "Process 4");
		ProcessBean p5 = new ProcessBean(5, "Process 5");
		ProcessBean p6 = new ProcessBean(5, "Process 6");
		
		List<ProcessBean> plist = new ArrayList<ProcessBean>();
		List<ProcessBean> blist = new ArrayList<ProcessBean>();
		List<Resource> rlist = new ArrayList<Resource>();
		
		
		plist.add(p2);
		plist.add(p3);
		plist.add(p4);
		plist.add(p5);
		plist.add(p6);
		
		rlist.add(diskSnap.getDisk().fetchResource(1, 1));
		rlist.add(diskSnap.getDisk().fetchResource(1, 2));
		p1.setResources(rlist);
		ProcessSnapshotView snap = new ProcessSnapshotView(plist,p1,null,0);
		
		dRenderer.plot(diskSnap,snap);
		Thread.sleep(5000);
			
		
		plist.remove(p2);
		blist.add(p1);
		snap = new ProcessSnapshotView(plist,p2,blist,2);
		
		diskSnap.getDisk().releaseResource(1, 1);
		diskSnap.getDisk().releaseResource(1, 2);
		rlist=new ArrayList<Resource>();
		rlist.add(diskSnap.getDisk().fetchResource(4, 6));
		rlist.add(diskSnap.getDisk().fetchResource(7, 2));
		dRenderer.plot(diskSnap,snap);
		Thread.sleep(5000);
		
		plist.remove(p3);
		blist.add(p2);
		snap = new ProcessSnapshotView(plist,p3,blist,3);
		diskSnap.getDisk().releaseResource(4, 6);
		diskSnap.getDisk().releaseResource(7, 2);
		rlist=new ArrayList<Resource>();
		rlist.add(diskSnap.getDisk().fetchResource(3, 6));
		rlist.add(diskSnap.getDisk().fetchResource(1, 8));
		
		dRenderer.plot(diskSnap,snap);
		Thread.sleep(5000);
		
		
		rlist.add(diskSnap.getDisk().fetchResource(1, 1));
		rlist.add(diskSnap.getDisk().fetchResource(1, 2));
		dRenderer.plot(diskSnap,snap);

	}

}
