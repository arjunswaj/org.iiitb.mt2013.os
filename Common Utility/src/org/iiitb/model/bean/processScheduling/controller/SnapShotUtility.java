package org.iiitb.model.bean.processScheduling.controller;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.view.ProcessSnapshotView;
import org.iiitb.controller.util.SnapshotRenderer;
/*
 * Class which provides snapshot viewing functionality 
 * */
public  class SnapShotUtility {
	//Method which displays snap shot
	public static void ViewSnapHot(List<ProcessBean> readylist,ProcessBean current,List<ProcessBean> blockedlist,int time)
	{
		ProcessSnapshotView p = new ProcessSnapshotView(readylist,current,blockedlist,time);
		SnapshotRenderer sr = new SnapshotRenderer();
		sr.plot(p);
		List<ProcessSnapshotView> processSegments = new ArrayList<ProcessSnapshotView>();
		processSegments.add(p);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
