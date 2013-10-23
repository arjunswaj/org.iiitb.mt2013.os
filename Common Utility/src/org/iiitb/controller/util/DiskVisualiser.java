package org.iiitb.controller.util;

import javax.swing.JFrame;

import org.iiitb.view.DiskSnapshot;

import org.iiitb.view.consts.DiskViewConsts;

public class DiskVisualiser {
	JFrame window = new JFrame();
	
public void plot(DiskSnapshot snapshot){
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(DiskViewConsts.WINDOW_X,DiskViewConsts.WINDOW_Y,
				DiskViewConsts.WINDOW_WIDTH,DiskViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(snapshot);
		
		window.setVisible(true);
	}

}
