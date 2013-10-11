package org.iiitb.model.bean;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Models a ready queue for the process snapshot
 * @author kc 
 */
public class ReadyQueue {
	
	private Queue<ProcessBean> ready ;
	int size;
	
	public ReadyQueue(){
		ready = new LinkedList<ProcessBean>();
		size = 0 ;
	}
	
	public boolean insertToReady(ProcessBean p){
		return ready.add(p);
	}
	
	public ProcessBean removeFromReady(){
		if(!ready.isEmpty())
			return ready.remove();
		
		return null;
	}
	
	public Object[] getArray(){
		return ready.toArray();
	}
	public int getsize(){
		return ready.size();
	}
}
