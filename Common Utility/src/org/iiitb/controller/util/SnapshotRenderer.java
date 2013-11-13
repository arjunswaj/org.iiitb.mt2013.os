package org.iiitb.controller.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	static JTextArea text = new JTextArea(100,100);
		
	public static JTextArea getText() {
		return text;
	}

	public static void setText(JTextArea text) {
		SnapshotRenderer.text = text;
	}
	
	public static void setContent(String s){
		StringBuilder str = new StringBuilder(text.getText());
		str.append(s+"\n");
		//text.setFont(new Font(str.toString(), 5, 18));
		text.setText(str.toString());
	}

	/** Call this method by passing the ProcessSnapshotViewobject
	 * 
	 * @param snapshot
	 */
	public void plot(ProcessSnapshotView snapshot){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel heading = new JLabel("PROCESS SNAPSHOT");
	//	heading.setPreferredSize(new Dimension(20,20));
		c.gridx=0;
		c.gridy=0;
		c.weightx=0.05;
		c.weighty=0.05;
		c.anchor = GridBagConstraints.ABOVE_BASELINE;
		//c.fill = GridBagConstraints.BOTH;
		panel.add(heading,c);
		
		
		c.gridx=0;
		c.gridy=1;
		c.weightx=0.5;
		c.weighty=0.5;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(snapshot,c);
		
		String state= "Executing PID:"+snapshot.getCurrent().getPid()+"\tNAME:"+snapshot.getCurrent().getpName();
		text.setEditable(false);
		text.setBackground(new Color(0, 20, 20));
		text.setForeground(new Color(255, 255, 255));
		text.setFont(new Font(state, 5, 18));
		text.setMargin(new Insets(0, 100, 0, 20));
		
	
		setContent(state);
		c.gridx=0;
		c.gridy=2;
		c.weightx=0.2;
		c.weighty=0.2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		//text.setBounds(0, 0, 500, 500);
		//window.setLayout(new BoxLayout());
		panel.add(text,c);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ProcViewConsts.WINDOW_X,ProcViewConsts.WINDOW_Y,
				ProcViewConsts.WINDOW_WIDTH,ProcViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(panel);
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
