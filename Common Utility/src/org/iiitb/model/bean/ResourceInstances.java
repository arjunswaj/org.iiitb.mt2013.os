package org.iiitb.model.bean;

/**
 * 
 * @author anvith
 *
 */

public class ResourceInstances extends Resource{

	protected int instances;

	public String getInstances() {
		return Integer.toString(instances);
	}

	public void setInstances(int instances) {
		this.instances = instances;
	}
	public ResourceInstances(int rid, String resourceName, boolean availability, int instances){
		super(rid, resourceName, availability);
		this.instances = instances;
		
	}
	
	public void issueInstance(int value){
		setInstances(instances - value);
	}
	
	public void addInstance(int value){
		setInstances(instances + value);
	}
	
}
