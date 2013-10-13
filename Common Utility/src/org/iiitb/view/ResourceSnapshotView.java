package org.iiitb.view;

/**
 * @author anvith
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import org.iiitb.model.bean.ResourceInstances;
import org.iiitb.view.consts.ResourceViewConsts;

public class ResourceSnapshotView extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613688163985731142L;
	List<ResourceInstances> resource;
	
	public ResourceSnapshotView( List<ResourceInstances> snap){
		this.resource = snap;
	}

	public List<ResourceInstances> getResource() {
		return resource;
	}

	public void setResource(List<ResourceInstances> resource) {
		this.resource = resource;
	}

	public void paint( Graphics g ){
		Object[] list = resource.toArray();
		for(int i =0 ; i<resource.size(); i++){
			int offset = i*250;
			if(((ResourceInstances) list[i]).isAvailability()){
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
			g.drawString(((ResourceInstances) list[i]).getResourceName(),ResourceViewConsts.BLOCK_POS_X+offset+30 ,ResourceViewConsts.BLOCK_POS_Y+25);
			g.drawString(((ResourceInstances) list[i]).getInstances(), ResourceViewConsts.BLOCK_POS_X+offset+60 ,ResourceViewConsts.BLOCK_POS_X+25+50);
		}		
	}
}
