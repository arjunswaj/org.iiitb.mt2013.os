package org.iiitb.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.view.ProcessSnapshotView;
import org.iiitb.view.ResourceSnapshotView;
import org.iiitb.view.consts.ProcViewConsts;
import org.iiitb.view.consts.ResourceViewConsts;

/*
 *  Utility to plot the Process Snapshot View
 *  
 *  @author kc
 */


public class SnapshotRenderer {
	
	JFrame window = new JFrame();
	
	/** Call this method by passing the ProcessSnapshotViewobject
	 * 
	 * @param snapshot
	 */
	public void plot(ProcessSnapshotView snapshot){
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ProcViewConsts.WINDOW_X,ProcViewConsts.WINDOW_Y,
				ProcViewConsts.WINDOW_WIDTH,ProcViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(snapshot);
		window.setVisible(true);
	}
	
	public List<ProcessSnapshotView> readProcessSegmentsFromFile(File file) {
		List<ProcessSnapshotView> processSegments = new ArrayList<ProcessSnapshotView>();
		BufferedReader bufferedReader = null;
		
		int currentpid;
		String currentpName;
		ProcessBean current;
		int time;
		int readyq;
		int blockedq;
		int n=0;
		
		try{
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
		
			while (null != (line = bufferedReader.readLine())) {
			
				List<ProcessBean> readylist = new ArrayList<ProcessBean>();
				List<ProcessBean> blockedlist = new ArrayList<ProcessBean>();
				StringTokenizer st = new StringTokenizer(line, ",");
			
				currentpid = Integer.parseInt(st.nextToken());
				currentpName = st.nextToken();
				current=new ProcessBean(currentpid,currentpName);
			
				time = Integer.parseInt(st.nextToken());
				readyq = Integer.parseInt(st.nextToken());
			
				for(int i=0;i<readyq;i++){
					int pid = Integer.parseInt(st.nextToken());
					String pName = st.nextToken();
					ProcessBean ready=new ProcessBean(pid,pName);
					readylist.add(ready);
					//System.out.println("ready"+pid+","+pName);
				}
				blockedq = Integer.parseInt(st.nextToken());
			
				for(int i=0;i<blockedq;i++){
					int pid = Integer.parseInt(st.nextToken());
					String pName = st.nextToken();
					ProcessBean blocked=new ProcessBean(pid,pName);
					blockedlist.add(blocked);
					//System.out.println("blocked"+pid+","+pName);
				}
			
				ProcessSnapshotView p = new ProcessSnapshotView(readylist,current,blockedlist,time);
				plot(p);
			
				processSegments.add(p);
				try {
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	return processSegments;
}	
	/**
	 * Plot the graph using File as paramenter
	 * 
	 * @param file
	 *            file
	 */
	public void plotGraph(File file) {

		List<ProcessSnapshotView> processSegments = readProcessSegmentsFromFile(file);
		
	}

	public void draw(ProcessSnapshotView p){
		
		SnapshotRenderer render = new SnapshotRenderer();
		render.plot(p);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
