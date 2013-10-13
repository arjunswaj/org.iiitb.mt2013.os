package org.iiitb.model.bean;

import org.iiitb.model.consts.ResourceType;

public class Resource {
	protected int rid;
	protected String resourceName;
	protected boolean availability;
	protected int ownerPid;
	protected ResourceType rType;
	

	/**
	 * Default Constructor
	 */
	public Resource() {

	}

	public Resource(int rid, String resourceName, boolean availability, 
			int ownerPid, ResourceType rType) {
		super();
		this.rid = rid;
		this.resourceName = resourceName;
		this.availability = availability;
		this.ownerPid = ownerPid;
		this.rType = rType;
	}
	
	public Resource( int rid, String resourceName, boolean availability){
		super();
		this.rid = rid;
		this.resourceName = resourceName;
		this.availability = availability;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public int getOwnerPid() {
		return ownerPid;
	}

	public void setOwnerPid(int ownerPid) {
		this.ownerPid = ownerPid;
	}

	public ResourceType getrType() {
		return rType;
	}

	public void setrType(ResourceType rType) {
		this.rType = rType;
	}

}
