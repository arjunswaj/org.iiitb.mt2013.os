package org.iiitb.view;

/**
 * @author anvith
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.view.consts.ResourceViewConsts;

public class ResourceSnapshotView extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613688163985731142L;
	//List<ResourceInstances> resource;
	List<Resource> resource;
	List<ProcessBean> process;
	//ResourceInstances instanceObj = new ResourceInstances();
	

	public ResourceSnapshotView( List<Resource> resourceSnap, List<ProcessBean> processSnap){
		this.resource = resourceSnap;
		this.process = processSnap;
	}
	public ResourceSnapshotView(){
	
	}
	
	public ResourceSnapshotView( List<Resource> resourceSnap){
		this.resource = resourceSnap;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}
	
	public List<ProcessBean> getProcess() {
		return process;
	}

	public void setProcess(List<ProcessBean> process) {
		this.process = process;
	}

	public void paint( Graphics g ){
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Resource Allocation Graph",
				ResourceViewConsts.SEGMENT_TITLE_X_MARGIN,
				ResourceViewConsts.SEGMENT_TITLE_Y_MARGIN);
		
		Object[] list = resource.toArray();
		for(int i =0 ; i<resource.size(); i++){
			int offset = i*250;
			if(((Resource) list[i]).isAvailability() && ((Resource) list[i]).getNumOfInstance() > 0){
			g.setColor(Color.GREEN);
			}
			else{
				g.setColor(Color.RED);
			}
			g.fill3DRect(ResourceViewConsts.BLOCK_POS_X+offset, ResourceViewConsts.BLOCK_POS_Y, 
					ResourceViewConsts.BLOCK_WIDTH,
					ResourceViewConsts.BLOCK_HEIGHT, true);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString(((Resource) list[i]).getResourceName(),ResourceViewConsts.BLOCK_POS_X+offset+30 ,ResourceViewConsts.BLOCK_POS_Y+25);
			String noOfInstances = Integer.toString(((Resource) list[i]).getNumOfInstance());
			g.drawString(noOfInstances, ResourceViewConsts.BLOCK_POS_X+offset+60 ,ResourceViewConsts.BLOCK_POS_X+25+50);
		}
		
		Object[] plist = process.toArray();
		for(int i =0 ; i<process.size(); i++){
			int offset = i*250;
			g.setColor(Color.WHITE);
			g.fill3DRect(ResourceViewConsts.PROCESS_X+offset, ResourceViewConsts.PROCESS_Y, 
					ResourceViewConsts.PROCESS_WIDTH,
					ResourceViewConsts.PROCESS_HEIGHT, true);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString(((ProcessBean) plist[i]).getpName(),ResourceViewConsts.PROCESS_X+offset+30 ,ResourceViewConsts.PROCESS_Y+25);
		}
		
		g.drawString("view console for output",ResourceViewConsts.PROCESS_X+30 ,ResourceViewConsts.PROCESS_Y+300);
	}
}
