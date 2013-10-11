package org.iiitb.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;


import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.ReadyQueue;
import org.iiitb.view.consts.ProcViewConsts;

/*
 * The class depicting a CPU snapshot view. This consists of a Ready queue(process waiting to be scheduled),
 * Current process which is getting executed
 * List of processes which are blocked/waiting for I/O
 * 
 * @author harsha
 */

public class ProcessSnapshotView extends JComponent{
	
	private static final long serialVersionUID = 5122026938552575692L;
	ReadyQueue ready;
	ProcessBean current;
	List<ProcessBean> blocked;
	int time;
	
	public  ProcessSnapshotView(List<ProcessBean> readylist, ProcessBean current,List<ProcessBean>blocked,int time){
		
		ready = new ReadyQueue();
		
		if(readylist.size() != 0){
			for(ProcessBean p:readylist){
				if(!ready.insertToReady(p))
					break; // TODO exceptions
			}
		}
		this.current = current;
		this.blocked=blocked;
		this.time = time;
				
	}
	
	public ProcessSnapshotView( ProcessSnapshotView snap) {
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

	public void paint(Graphics g){
		
		g.drawString("TIME "+getTime(), ProcViewConsts.TIME_LABEL_X,ProcViewConsts.TIME_LABEL_Y);
		g.drawString("READY QUEUE", ProcViewConsts.READY_LABEL_X,ProcViewConsts.READY_LABEL_Y);
		g.drawString("CURRENT PROCESS", ProcViewConsts.CURRENT_LABEL_X,ProcViewConsts.CURRENT_LABEL_Y);
		g.drawString("BLOCKED / WAITING", ProcViewConsts.BLOCKED_LABEL_X,ProcViewConsts.BLOCKED_LABEL_Y);
		Object[] list = ready.getArray();
		for(int i =0 ; i<ready.getsize(); i++){
			
			int offset = i*50;
			g.fill3DRect(ProcViewConsts.READY_X, ProcViewConsts.READY_Y+offset, 
					ProcViewConsts.READY_BLOCK_WIDTH,
					ProcViewConsts.READY_BLOCK_HEIGHT, true);
			g.setColor(Color.WHITE);
			g.drawString(((ProcessBean) list[i]).getpName(),ProcViewConsts.READY_X+25 ,ProcViewConsts.READY_Y+offset+25);
			g.setColor(Color.BLACK);
			
			
		}
		
		g.setColor(Color.GREEN);
		g.fill3DRect(ProcViewConsts.CUR_X, ProcViewConsts.CUR_Y, 
				ProcViewConsts.CUR_BLOCK_WIDTH, ProcViewConsts.CUR_BLOCK_HEIGHT, true);
		g.setColor(Color.BLACK);
		g.drawString(current.getpName(),ProcViewConsts.CUR_X+10 ,ProcViewConsts.CUR_Y+50);
		
		if(blocked != null){
			for(int i =0 ; i<blocked.size(); i++){
				int offset = i*50;
				g.draw3DRect(ProcViewConsts.BLOCKED_X, ProcViewConsts.BLOCKED_Y+offset, 
						ProcViewConsts.BLOCKED_BLOCK_WIDTH,
						ProcViewConsts.BLOCKED_BLOCK_HEIGHT, true);
				g.drawString(blocked.get(i).getpName(),ProcViewConsts.BLOCKED_X+25 ,ProcViewConsts.BLOCKED_Y+offset+25);
				
			}
		}
	}

	
}
