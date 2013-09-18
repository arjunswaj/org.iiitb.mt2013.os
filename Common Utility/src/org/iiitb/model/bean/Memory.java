package org.iiitb.model.bean;

import java.util.List;

public class Memory {

	private List<MemoryUnit> memory;
	private long size;
	
	public void insertMemoryUnit(MemoryUnit memoryUnit){
		this.memory.add(memoryUnit);
	}
	
	public void removeMemoryUnit(){
		
	}	
	
}
