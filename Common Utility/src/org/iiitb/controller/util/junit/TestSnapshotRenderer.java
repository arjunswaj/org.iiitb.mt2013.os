package org.iiitb.controller.util.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.iiitb.controller.util.SnapshotRenderer;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.view.ProcessSnapshotView;
import org.junit.Test;

public class TestSnapshotRenderer {

	@Test
	public void test() {
		
			File pfile = new File("testcase.csv");
			String test[]={"3,p3,0,3,1,p1,2,p2,4,p4,2,5,p5,6,p6","2,p2,1,2,3,p3,1,p1,3,5,p5,4,p4,6,p6"};
			
			SnapshotRenderer snap = new SnapshotRenderer();
			
			List<ProcessSnapshotView> p= new ArrayList<ProcessSnapshotView>();;
			List<ProcessSnapshotView> ps = snap.readProcessSegmentsFromFile(pfile);
			
			int currentpid;
			String currentpname;
			int time;
			int readyq;
			int blockedq;
			ProcessBean current;
			
			
			//StringTokenizer st = new StringTokenizer(test1, ",");
			//StringTokenizer st;
			
			for(int k=0;k<2;k++){
				List<ProcessBean> readylist = new ArrayList<ProcessBean>();
				List<ProcessBean> blockedlist = new ArrayList<ProcessBean>();
				
				StringTokenizer st = new StringTokenizer(test[k], ",");	
				currentpid = Integer.parseInt(st.nextToken());
				currentpname = st.nextToken();
				current=new ProcessBean(currentpid,currentpname);
				time = Integer.parseInt(st.nextToken());
				readyq = Integer.parseInt(st.nextToken());
				
				for(int i=0;i<readyq;i++){
					int pid = Integer.parseInt(st.nextToken());
					String pName = st.nextToken();
					ProcessBean ready=new ProcessBean(pid,pName);
					readylist.add(ready);
				}
				blockedq = Integer.parseInt(st.nextToken());
			
				for(int j=0;j<blockedq;j++){
					int pid = Integer.parseInt(st.nextToken());
					String pName = st.nextToken();
					ProcessBean blocked=new ProcessBean(pid,pName);
					blockedlist.add(blocked);
				}
				
				ProcessSnapshotView pview = new ProcessSnapshotView(readylist,current,blockedlist,time);
				p.add(pview);
			}

		    //System.out.println(p.get(0).getReady().equals(ps.get(0).getReady()) && p.get(0).getBlocked().equals(ps.get(0).getBlocked()) && p.get(0).getCurrent().equals(ps.get(0).getCurrent()) && p.get(0).getTime()==(ps.get(0).getTime()));
		    //System.out.println(p.get(1).getReady().equals(ps.get(1).getReady()) && p.get(1).getBlocked().equals(ps.get(1).getBlocked()) && p.get(1).getCurrent().equals(ps.get(1).getCurrent()) && p.get(1).getTime()==(ps.get(1).getTime()));

		    for(int i=0;i<p.size();i++){
		    	assertEquals("correct",p.get(i).getReady(),ps.get(i).getReady());
		    	assertNotSame(p.get(i).getReady(),ps.get(i).getReady());
			
		    	assertEquals("correct",p.get(i).getBlocked(), ps.get(i).getBlocked());
		    	assertNotSame(p.get(i).getBlocked(), ps.get(i).getBlocked());
			
		    	assertEquals("correct",p.get(i).getCurrent(),ps.get(i).getCurrent());
		    	assertNotSame(p.get(i).getCurrent(),ps.get(i).getCurrent());
			
		    	assertEquals("correct",p.get(i).getTime(),ps.get(i).getTime());
		    	assertFalse("fail",p.get(i).getTime()!=ps.get(i).getTime());
			
			}
	}

}
