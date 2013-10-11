package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.view.ProcessSnapshotView;

public class ProcSnapshotTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		SnapshotRenderer render = new SnapshotRenderer();
		
		ProcessBean p1 = new ProcessBean(1, "Process 1");
		ProcessBean p2 = new ProcessBean(2, "Process 2");
		ProcessBean p3 = new ProcessBean(3, "Process 3");
		ProcessBean p4 = new ProcessBean(4, "Process 4");
		ProcessBean p5 = new ProcessBean(5, "Process 5");
		ProcessBean p6 = new ProcessBean(5, "Process 6");
		List<ProcessBean> plist = new ArrayList<ProcessBean>();
		List<ProcessBean> blist = new ArrayList<ProcessBean>();
		
		plist.add(p2);
		plist.add(p3);
		plist.add(p4);
		plist.add(p5);
		plist.add(p6);
		ProcessSnapshotView snap = new ProcessSnapshotView(plist,p1,null,0);
		render.plot(snap);
		Thread.sleep(1000);
			
		
		plist.remove(p2);
		blist.add(p1);
		snap = new ProcessSnapshotView(plist,p2,blist,2);
		render.plot(snap);
		Thread.sleep(1000);
		
		plist.remove(p3);
		blist.add(p2);
		snap = new ProcessSnapshotView(plist,p3,blist,3);
		render.plot(snap);
		Thread.sleep(1000);
	}

}
