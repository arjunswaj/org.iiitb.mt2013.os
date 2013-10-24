package org.iiitb.model.bean;

import java.util.HashMap;
import java.util.Map;

public class Cylinder  {
	
	Map <Integer,Sector> cylinderobj;
	public Cylinder(){
		
	}

	public Cylinder(int cylinderNum, int numOfSectors, int sizeofSector) {
		
		cylinderobj = new HashMap<Integer, Sector>();
		for(int i = 0; i <numOfSectors; i++){
			Sector sec	 = new Sector(i,sizeofSector);
			cylinderobj.put(i, sec);
		}
		
	}

	public Map<Integer, Sector> getCylinderobj() {
		return cylinderobj;
	}

	public void setCylinderobj(Map<Integer, Sector> cylinderobj) {
		this.cylinderobj = cylinderobj;
	}
	
	

}
