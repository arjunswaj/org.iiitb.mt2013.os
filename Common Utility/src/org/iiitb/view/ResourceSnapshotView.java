package org.iiitb.view;

/**
 * @author anvith
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.Resource;
import org.iiitb.model.bean.ResourceAllocation;
import org.iiitb.view.consts.ResourceViewConsts;

public class ResourceSnapshotView extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2613688163985731142L;
	List<Resource> resource;
	List<ProcessBean> process;
	//JTextArea actionText;
	ResourceAllocation ra = new ResourceAllocation();
	boolean flag = false;

	// String action;

	public ResourceSnapshotView(List<Resource> resourceSnap,
			List<ProcessBean> processSnap) {
		this.resource = resourceSnap;
		this.process = processSnap;

	}

	public ResourceSnapshotView(List<Resource> resourceSnap) {
		this.resource = resourceSnap;
		flag = true;
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

	public void paint(Graphics g) {

		if(flag){
			g.setColor(new Color(0, 0, 100));
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Resources",
					ResourceViewConsts.SEGMENT_TITLE_X_MARGIN + 140,
					ResourceViewConsts.SEGMENT_TITLE_Y_MARGIN + 500);

			Object[] list = resource.toArray();
			for (int i = 0; i < resource.size(); i++) {
				int offset = i * ResourceViewConsts.OFFSET_CONST;
				if (((Resource) list[i]).isAvailability()
						&& ((Resource) list[i]).getNumOfInstance() > 0) {
					g.setColor(new Color(0, 200, 100));
				} else {
					g.setColor(new Color(255, 0, 50));
				}
				g.fill3DRect(ResourceViewConsts.RESOURCE_X + offset,
						ResourceViewConsts.RESOURCE_Y+300,
						ResourceViewConsts.RESOURCE_WIDTH,
						ResourceViewConsts.RESOURCE_HEIGHT, true);
				g.setColor(Color.BLACK);
				g.setFont(new Font("default", Font.BOLD, 16));
				g.drawString(((Resource) list[i]).getResourceName(),
						ResourceViewConsts.RESOURCE_X + offset + 30,
						ResourceViewConsts.RESOURCE_Y + 25 + 300);
				String noOfInstances = Integer.toString(((Resource) list[i])
						.getNumOfInstance());
				g.drawString(noOfInstances, ResourceViewConsts.RESOURCE_X + offset
						+ 60, ResourceViewConsts.RESOURCE_X + 25 + 50 + 300);
			}
		}
		else{
		/*
		 * Draw resources
		 */
		g.setColor(new Color(0, 0, 100));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Resource Allocation Graph",
				ResourceViewConsts.SEGMENT_TITLE_X_MARGIN,
				ResourceViewConsts.SEGMENT_TITLE_Y_MARGIN);

		Object[] list = resource.toArray();
		for (int i = 0; i < resource.size(); i++) {
			int offset = i * ResourceViewConsts.OFFSET_CONST;
			if (((Resource) list[i]).isAvailability()
					&& ((Resource) list[i]).getNumOfInstance() > 0) {
				g.setColor(new Color(0, 200, 100));
			} else {
				g.setColor(new Color(255, 0, 50));
			}
			g.fill3DRect(ResourceViewConsts.RESOURCE_X + offset,
					ResourceViewConsts.RESOURCE_Y,
					ResourceViewConsts.RESOURCE_WIDTH,
					ResourceViewConsts.RESOURCE_HEIGHT, true);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString(((Resource) list[i]).getResourceName(),
					ResourceViewConsts.RESOURCE_X + offset + 30,
					ResourceViewConsts.RESOURCE_Y + 25);
			String noOfInstances = Integer.toString(((Resource) list[i])
					.getNumOfInstance());
			g.drawString(noOfInstances, ResourceViewConsts.RESOURCE_X + offset
					+ 60, ResourceViewConsts.RESOURCE_X + 25 + 50);
		}
		/*
		 * Draw processes
		 */

		Object[] plist = process.toArray();
		for (int i = 0; i < process.size(); i++) {
			int offset = i * ResourceViewConsts.OFFSET_CONST;
			//System.out.println("111" + ra.getInstanceAvailable());
			if (ra.getInstanceAvailable()) {
				g.setColor(new Color(100, 100, 255));

			} else {
				g.setColor(new Color(255, 0, 50));
				
			}
			g.fill3DRect(ResourceViewConsts.PROCESS_X + offset,
					ResourceViewConsts.PROCESS_Y,
					ResourceViewConsts.PROCESS_WIDTH,
					ResourceViewConsts.PROCESS_HEIGHT, true);
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString(((ProcessBean) plist[i]).getpName(),
					ResourceViewConsts.PROCESS_X + offset + 30,
					ResourceViewConsts.PROCESS_Y + 25);
		}

		/*
		 * Map resources and process according to resource allocation
		 */

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(new Color(200, 0, 0));
		for (int i = 0; i < plist.length; i++) {

			List<Resource> allocatedRList = ((ProcessBean) plist[i])
					.getResources();

			for (int j = 0; j < allocatedRList.size(); j++) {

				for (int k = 0; k < list.length; k++) {

					if (allocatedRList.get(j).getRid() == ((Resource) list[k])
							.getRid()) {

						int rOffset = k * ResourceViewConsts.OFFSET_CONST;
						int pOffset = i * ResourceViewConsts.OFFSET_CONST;

						g.drawLine(
								ResourceViewConsts.RESOURCE_X
										+ rOffset
										+ (ResourceViewConsts.RESOURCE_WIDTH / 2),
								ResourceViewConsts.RESOURCE_Y
										+ ResourceViewConsts.RESOURCE_HEIGHT,
								ResourceViewConsts.PROCESS_X
										+ pOffset
										+ (ResourceViewConsts.RESOURCE_WIDTH / 2),
								ResourceViewConsts.PROCESS_Y);
					}
				}

			}

		}
	}
}
}