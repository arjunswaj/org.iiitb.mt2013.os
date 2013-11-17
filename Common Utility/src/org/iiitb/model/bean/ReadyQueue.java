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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ready == null) ? 0 : ready.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReadyQueue other = (ReadyQueue) obj;
		if (ready == null) {
			if (other.ready != null)
				return false;
		} else if (!ready.equals(other.ready))
			return false;
		return true;
	}
	
	
}
