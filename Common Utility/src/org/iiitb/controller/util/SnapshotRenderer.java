package org.iiitb.controller.util;

import javax.swing.JFrame;

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
	
	public void plotResource(ResourceSnapshotView snapshot){
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,ResourceViewConsts.WINDOW_Y,
				ResourceViewConsts.WINDOW_WIDTH,ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(snapshot);
		window.setVisible(true);
	}
		
	
}
