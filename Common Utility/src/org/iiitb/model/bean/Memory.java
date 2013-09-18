package org.iiitb.model.bean;

import java.util.List;

public class Memory {

	private List<MemoryUnit> memory;
	private long size;		
	
	public List<MemoryUnit> getMemory() {
		return memory;
	}

	public void setMemory(List<MemoryUnit> memory) {
		this.memory = memory;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void insertMemoryUnit(MemoryUnit memoryUnit){
		this.memory.add(memoryUnit);
	}
	
	public void removeMemoryUnit(){
		
	}	
		
}
