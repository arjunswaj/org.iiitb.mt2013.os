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
	
	public int getIntInstances() {
		return instances;
	}


	public void setInstances(int instances) {
		this.instances = instances;
	}
	
	public ResourceInstances(){
		
	}
	public ResourceInstances(int rid, String resourceName, boolean availability, int instances){
		super(rid, resourceName, availability);
		this.instances = instances;
		
	}
	
	public void issueInstance(ProcessBean p){
		if(isAvailability() && getIntInstances() > 0){
		setInstances(instances - 1);
		System.out.println("Instance of "+getResourceName()+" allocated to " + p.getpName());
		}
		else{
			System.out.println("Resource "+ getResourceName()+" unavailable");
		}
	}
	
	public void addInstance(){
		if(isAvailability()){
			setInstances(++instances);
			System.out.println("Instance added to "+ getResourceName());
		}
		else{
			System.out.println("Instance cannot be added to "+ getResourceName());
		}
	}
}
