package org.iiitb.model.bean;

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
	
	public ProcessBean(int pid, String pName){
		this.pid = pid;
		this.pName = pName;
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

}
