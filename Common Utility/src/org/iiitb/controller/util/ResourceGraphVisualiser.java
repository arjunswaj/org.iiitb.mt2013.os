package org.iiitb.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.view.ResourceSnapshotView;
import org.iiitb.view.consts.ResourceViewConsts;

public class ResourceGraphVisualiser {

	JFrame window = new JFrame();
	
	public void plotResource(ResourceSnapshotView snapshot){
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,ResourceViewConsts.WINDOW_Y,
				ResourceViewConsts.WINDOW_WIDTH,ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(snapshot);
		window.setVisible(true);
	}
		
	public List<ResourceSnapshotView> readResourcesFromFile( File file ) throws InterruptedException, NumberFormatException, IOException{
		List<ResourceSnapshotView> resourceBlocks = new ArrayList<ResourceSnapshotView>();
		BufferedReader reader = null;
		
		int rid;
		String resourceName;
		boolean availability;
		int numOfInstance;
		int pid;
		String pName;
		
		try{
			
			reader = new BufferedReader( new FileReader(file));
			String line =  null;
			
			while( null != (line = reader.readLine())){
				
				List<Resource> rlist = new ArrayList<Resource>();
				List<ProcessBean> plist = new ArrayList<ProcessBean>();
				StringTokenizer token = new StringTokenizer(line, ",");
				
				rid = Integer.parseInt(token.nextToken());
				resourceName = token.nextToken();
				availability = Boolean.parseBoolean(token.nextToken());
				numOfInstance = Integer.parseInt(token.nextToken());
				pid = Integer.parseInt(token.nextToken());
				pName = token.nextToken();
				
				Resource resource = new Resource(rid,resourceName,availability,numOfInstance);
				ProcessBean process = new ProcessBean(pid,pName);
				
				ResourceSnapshotView snap = new ResourceSnapshotView();
				plotResource(snap);
				
				resourceBlocks.add(snap);
				Thread.sleep(1000);
			}
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
	
	public void plotGraph(File file) throws NumberFormatException, InterruptedException, IOException {

		List<ResourceSnapshotView> resourceBlocks = readResourcesFromFile(file);
		
	}

	public void draw(ResourceSnapshotView p){
		
		ResourceGraphVisualiser render = new ResourceGraphVisualiser();
		render.plotResource(p);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
