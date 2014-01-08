package org.iiitb.controller.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.model.bean.ResourceAllocation;
import org.iiitb.view.Legend;
import org.iiitb.view.ResourceSnapshotView;
import org.iiitb.view.consts.ResourceViewConsts;


public class ResourceGraphVisualiser {

	JFrame window = new JFrame();
	
	JPanel panel;
	static JTextArea actionText = new JTextArea(ResourceViewConsts.TEXT_ROWS,
			ResourceViewConsts.TEXT_COLUMNS);
	GridBagLayout gb;
	GridBagConstraints gbc;
	
	public static JTextArea getText() {
		return actionText;
	}

	public static void setText(JTextArea actionText) {
		ResourceGraphVisualiser.actionText = actionText;
	}
	
	public static void setContent(String s){
		StringBuilder str = new StringBuilder(actionText.getText());
		str.append(s+"\n");
		//text.setFont(new Font(str.toString(), 5, 18));
		actionText.setText(str.toString());
	}

	
	public void plotResource(ResourceSnapshotView snapshot){
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,ResourceViewConsts.WINDOW_Y,
				ResourceViewConsts.WINDOW_WIDTH,ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(snapshot);
		window.setVisible(true);
	}
	
	public void plotResource(ResourceSnapshotView snapshot, String action){

		/*
		 * Adding text area to display action message
		 */
		panel = new JPanel(new GridBagLayout());
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,
				ResourceViewConsts.WINDOW_Y, ResourceViewConsts.WINDOW_WIDTH,
				ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();

		gbc.gridx = 0;
		gbc.gridy = 0;
		/*gbc.ipadx = 150;
		gbc.ipady = 200;*/
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(snapshot, gbc);
		
		Color[] col= {Color.BLUE,Color.GREEN,Color.RED}; 
		String[] vals = {"PROCESSES PRESENT","AVAILABLE RESOURCES","UNAVAILABLE RESOURCES"};
		Legend legendObj =new Legend(3,vals,col);		
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridheight=1;
		gbc.weightx=0.005;
		gbc.weighty=0.05;	
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(0,10,0,0);
		panel.add(legendObj.getLegend(),gbc);

		gbc = new GridBagConstraints();
		actionText.setBackground(new Color(0, 20, 20));
		actionText.setForeground(new Color(255, 255, 255));
		actionText.setFont(new Font(action, 5, 18));
		actionText.setMargin(new Insets(0, 250, 0, 20));
		setContent(action);
		actionText.setEditable(false);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = 100;
		gbc.ipady = 100;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		
		JScrollPane scroll = new JScrollPane(actionText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll, gbc);
		
		
		window.getContentPane().add(panel);
		window.setVisible(true);
	}
		
	public List<ResourceSnapshotView> readResourcesFromFile( int noOfResources, int noOfProcesses, File rfile ) throws InterruptedException, NumberFormatException, IOException{
		List<ResourceSnapshotView> resourceBlocks = new ArrayList<ResourceSnapshotView>();
		BufferedReader reader = new BufferedReader( new FileReader(rfile));
		List<Resource> rlist = new ArrayList<Resource>();
		List<ProcessBean> plist = new ArrayList<ProcessBean>();
		ResourceAllocation resourceObj = new ResourceAllocation();
		ProcessBean process = null;
		Resource resource = null;
		ResourceGraphVisualiser render;
		ResourceSnapshotView snap;
		
		int rid;
		String resourceName;
		boolean availability;
		int numOfInstance;
		int pid;
		String pName;
		String taskName;
		
		int lineCount = 0;
		String line = null;
		try{
			while( null != (line = reader.readLine())){
				
				StringTokenizer token = new StringTokenizer(line, ",");
				
				rid = Integer.parseInt(token.nextToken());
				resourceName = token.nextToken();
				availability = Boolean.parseBoolean(token.nextToken());
				numOfInstance = Integer.parseInt(token.nextToken());
					
				
				resource = new Resource(rid,resourceName,availability,numOfInstance);
				
				rlist.add(resource);
				lineCount++;
				if(lineCount == noOfResources){
					break;
				}
				
			}
		}
		catch(NoSuchElementException e){
			System.out.println("One of resource field empty");
		}
		finally{
			lineCount = 0;
		}
			

		try{			
			while( null != (line = reader.readLine())){
				
				StringTokenizer token = new StringTokenizer(line, ",");
				
				pid = Integer.parseInt(token.nextToken());
				pName = token.nextToken();
				process = new ProcessBean(pid,pName);
				plist.add(process);
				lineCount++;
				if(lineCount == noOfProcesses){
					break;
				}
			}			
		}
		catch(NoSuchElementException e){
			System.out.println("One of process field empty");
		}
		finally{
			render = new ResourceGraphVisualiser();
			
			snap = new ResourceSnapshotView(rlist,plist);
			render.plotResource(snap, "abc");

			resourceBlocks.add(snap);
		}
		try{
			while(null != (line = reader.readLine())){
				StringTokenizer token = new StringTokenizer(line, ",");

				taskName = token.nextToken();
				resourceName = token.nextToken();
				pName = token.nextToken();
				
				String allocate = "allocate";
				
				for(int i = 0; i < rlist.size(); i++){
					if(((Resource) rlist.toArray()[i]).getResourceName().compareTo(resourceName)==0){
						resource = ((Resource) rlist.toArray()[i]);
					}
				}
				
				for(int i = 0; i < plist.size(); i++){
					if(((ProcessBean) plist.toArray()[i]).getpName().compareTo(pName)==0){
						process = ((ProcessBean) plist.toArray()[i]);
					}
				}
				//System.out.println("Process name: "+process.getpName());
				//System.out.println("Resource name: "+resource.getResourceName());

				
				if(taskName.compareToIgnoreCase(allocate) == 0){
					//System.out.println("Calling issue instance");
					Thread.sleep(1000);
					resourceObj.issueInstance(process, resource);
					snap = new ResourceSnapshotView(rlist,plist);
					render.plotResource(snap,"abc");
				}
				else{
					//System.out.println("Calling relinquish instance");
					Thread.sleep(1000);
					resourceObj.relinquishInstance(process, resource);
					snap = new ResourceSnapshotView(rlist,plist);
					render.plotResource(snap,"abc");
				}
			}	
		}
		catch(NoSuchElementException e){
			System.out.println("One of the fields empty");
		}
		finally{
			if (null != reader) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
		}
		return resourceBlocks;
	}
	
	public void plotGraph(int noOfResources, int noOfProcesses,File rfile) throws NumberFormatException, InterruptedException, IOException {

		@SuppressWarnings("unused")
		List<ResourceSnapshotView> resourceBlocks = readResourcesFromFile(noOfResources, noOfProcesses, rfile);
		
	}	
	
}
