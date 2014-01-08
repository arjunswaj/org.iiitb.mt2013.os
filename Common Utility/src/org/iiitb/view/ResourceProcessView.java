package org.iiitb.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.ReadyQueue;
import org.iiitb.model.bean.Resource;
import org.iiitb.view.consts.ProcViewConsts;
import org.iiitb.view.consts.ResourceViewConsts;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class ResourceProcessView extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3824350326722797495L;
	ReadyQueue ready;
	ProcessBean current;
	List<ProcessBean> blocked;
	int time;

	public ResourceProcessView(List<ProcessBean> readylist,
			ProcessBean current, List<ProcessBean> blocked, int time) {

		ready = new ReadyQueue();

		if (readylist.size() != 0) {
			for (ProcessBean p : readylist) {
				if (!ready.insertToReady(p))
					break; // TODO exceptions
			}
		}
		this.current = current;
		this.blocked = blocked;
		this.time = time;

	}

	public ResourceProcessView(ProcessSnapshotView snap) {
		this.ready = snap.ready;
		this.current = snap.current;
		this.blocked = snap.blocked;
		this.time = snap.time;
	}

	public ReadyQueue getReady() {
		return ready;
	}

	public void setReady(ReadyQueue ready) {
		this.ready = ready;
	}

	public List<ProcessBean> getBlocked() {
		return blocked;
	}

	public void setBlocked(List<ProcessBean> blocked) {
		this.blocked = blocked;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ProcessBean getCurrent() {
		return current;
	}

	public void setCurrent(ProcessBean current) {
		this.current = current;
	}

	/*
	 * public ResourceProcessView(List<Resource> resourceSnap) { this.resource =
	 * resourceSnap; }
	 */

	public void paint(Graphics g) {

		
		/**
		 * Draw Process life cycle
		 */

		g.drawString("TIME " + getTime(), ProcViewConsts.TIME_LABEL_X,
				ProcViewConsts.TIME_LABEL_Y);
		g.drawString("READY QUEUE", ProcViewConsts.READY_LABEL_X,
				ProcViewConsts.READY_LABEL_Y);
		g.drawString("CURRENT PROCESS", ProcViewConsts.CURRENT_LABEL_X,
				ProcViewConsts.CURRENT_LABEL_Y);
		g.drawString("BLOCKED / WAITING", ProcViewConsts.BLOCKED_LABEL_X,
				ProcViewConsts.BLOCKED_LABEL_Y);
		Object[] list = ready.getArray();
		for (int i = 0; i < ready.getsize(); i++) {

			int offset = i * 50;
			g.fill3DRect(ProcViewConsts.READY_X, ProcViewConsts.READY_Y
					+ offset, ProcViewConsts.READY_BLOCK_WIDTH,
					ProcViewConsts.READY_BLOCK_HEIGHT, true);
			g.setColor(Color.WHITE);
			g.drawString(((ProcessBean) list[i]).getpName(),
					ProcViewConsts.READY_X + 25, ProcViewConsts.READY_Y
							+ offset + 25);
			g.setColor(Color.BLACK);

		}

		g.setColor(Color.GREEN);
		g.fill3DRect(ProcViewConsts.CUR_X, ProcViewConsts.CUR_Y,
				ProcViewConsts.CUR_BLOCK_WIDTH,
				ProcViewConsts.CUR_BLOCK_HEIGHT, true);
		g.setColor(Color.BLACK);
		g.drawString(current.getpName(), ProcViewConsts.CUR_X + 15,
				ProcViewConsts.CUR_Y + 50);

		if (blocked != null) {
			for (int i = 0; i < blocked.size(); i++) {
				int offset = i * 50;
				g.draw3DRect(ProcViewConsts.BLOCKED_X, ProcViewConsts.BLOCKED_Y
						+ offset, ProcViewConsts.BLOCKED_BLOCK_WIDTH,
						ProcViewConsts.BLOCKED_BLOCK_HEIGHT, true);
				g.drawString(blocked.get(i).getpName(),
						ProcViewConsts.BLOCKED_X + 25, ProcViewConsts.BLOCKED_Y
								+ offset + 25);

			}
		}

		/*
		 * Draw resources
		 */
		
		List<Resource> pResource = current.getResources();		
		Hashtable countResource = new Hashtable();
		List<Resource> temp = new ArrayList<Resource>();
		for(int i=0; i< pResource.size();i++){
			if(countResource.containsKey(pResource.get(i).getRid())){
				//System.out.println("exists in hash");
				Integer newCount = (Integer) countResource.get(pResource.get(i).getRid());
				//System.out.println(newCount);
				countResource.put(pResource.get(i).getRid(),newCount++);
			}
			else{
				int tempCount = 1;
				countResource.put(pResource.get(i).getRid(),tempCount);
				temp.add(pResource.get(i));
			}
			//System.out.println(countResource.get(pResource.get(i).getRid()));
		}
		countResource.clear();
		if(temp.isEmpty()){
			g.setFont(new Font("default", Font.BOLD, 22));
			g.drawString("No resources allocated", ResourceViewConsts.RESOURCE_X
					 + 330, ResourceViewConsts.RESOURCE_X + 25 + 50 + 230);
		}else{
		for (int i = 0; i < temp.size(); i++) {
			
			for (int j = 0; j < temp.size(); j++) {
				
				int offset = i * ResourceViewConsts.OFFSET_CONST;
				g.setColor(Color.BLUE);
				g.fill3DRect(ResourceViewConsts.RESOURCE_X + offset,
						ResourceViewConsts.RESOURCE_Y+300,
						ResourceViewConsts.RESOURCE_WIDTH,
						40, true);
				g.setColor(Color.BLACK);
				g.setFont(new Font("default", Font.BOLD, 16));
				g.drawString(pResource.get(i).getResourceName(),
						ResourceViewConsts.RESOURCE_X + offset + 30,
						ResourceViewConsts.RESOURCE_Y + 25 + 300);
				//String noOfInstances = (String) countResource.get(temp.get(j).getRid());
				//System.out.println(noOfInstances);
				/*g.drawString(noOfInstances, ResourceViewConsts.RESOURCE_X
						+ offset + 60, ResourceViewConsts.RESOURCE_X + 25 + 50 + 300);*/
			}
		}
		}

	}

}
