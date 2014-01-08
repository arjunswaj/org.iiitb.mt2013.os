package org.iiitb.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iiitb.model.consts.ProcessState;

/**
 * Process Bean Object
 * 
 * @author common-utility-team
 * 
 */
public class ProcessBean {
	/**
	 * process id
	 */
	private int pid;
	/**
	 * process name
	 */
	private String pName;
	/**
	 * arrival time of process
	 */
	private Date arrivalTime;
	/**
	 * resources associated with this process
	 */
	private List<Resource> resources;
	private List<Resource> resourceRequest;
	/**
	 * list of Time quanta associated with this process
	 */
	private List<TimeQuantum> burstList;
	/**
	 * priority of the process
	 */
	private int priority;
	/**
	 * Life cycle State of the process
	 */
	private ProcessState processState;
	/**
	 * Size of logical address space of a process
	 */
	private long logicalAddressSpacesize;
	/**
	 * Memory Associated with Process
	 */
	private Memory<MemorySegment> memoryUnit;

	public ProcessBean(int pid, String pName) {
		this.pid = pid;
		this.pName = pName;
		this.resources = new ArrayList<Resource>();
		this.resourceRequest = new ArrayList<Resource>();
	}

	public ProcessBean(int pid, String pName, Date arrivalTime,
			List<Resource> resources, List<TimeQuantum> burstList,
			int priority, ProcessState processState) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.arrivalTime = arrivalTime;
		this.resources = resources;
		this.burstList = burstList;
		this.priority = priority;
		this.processState = processState;
	}

	public int getPid() {
		return pid;
	}

	
	public List<Resource> getResourceRequest() {
		return resourceRequest;
	}

	public void setResourceRequest(List<Resource> resourceRequest) {
		this.resourceRequest = resourceRequest;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<TimeQuantum> getBurstList() {
		return burstList;
	}

	public void setBurstList(List<TimeQuantum> burstList) {
		this.burstList = burstList;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ProcessState getProcessState() {
		return processState;
	}

	public void setProcessState(ProcessState processState) {
		this.processState = processState;
	}

	public Memory<MemorySegment> getMemoryUnit() {
		return memoryUnit;
	}

	public void setMemoryUnit(Memory<MemorySegment> memoryUnit) {
		this.memoryUnit = memoryUnit;
	}

	/**
	 * @return the logicalAddressSpacesize
	 */
	public long getLogicalAddressSpacesize()
	{
		return logicalAddressSpacesize;
	}

	/**
	 * @param logicalAddressSpacesize the logicalAddressSpacesize to set
	 */
	public void setLogicalAddressSpacesize(long logicalAddressSpacesize)
	{
		this.logicalAddressSpacesize = logicalAddressSpacesize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pName == null) ? 0 : pName.hashCode());
		result = prime * result + pid;
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
		ProcessBean other = (ProcessBean) obj;
		if (pName == null) {
			if (other.pName != null)
				return false;
		} else if (!pName.equals(other.pName))
			return false;
		if (pid != other.pid)
			return false;
		return true;
	}
	
	

}
