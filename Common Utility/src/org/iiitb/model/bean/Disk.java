package org.iiitb.model.bean;

import java.util.HashMap;
import java.util.Map;

import org.iiitb.controller.util.DiskVisualiser;
import org.iiitb.controller.util.ProcessDiskVisualizer;

public class Disk {
	
	Map<Integer,Cylinder> diskobj ;
	
	public Disk(int numOfCylinders,int numOfSectors,int sizeOfSector){
		diskobj = new HashMap<Integer, Cylinder>();
		for(int i = 0; i< numOfCylinders;i++){
			Cylinder cyl = new Cylinder(i, numOfSectors, sizeOfSector);
			diskobj.put(i,cyl);
		}
		
	}

	public Map<Integer, Cylinder> getDiskobj() {
		return diskobj;
	}

	public void setDiskobj(Map<Integer, Cylinder> diskobj) {
		this.diskobj = diskobj;
	}
	//Use this method only for process and disk integration
	public Sector fetchResource(int cylinder,int sector){
		if(occupySector(cylinder, sector)){
		ProcessDiskVisualizer.setContent("Adding Sector "+sector+" to cylinder "+cylinder);
		return diskobj.get(cylinder).getCylinderobj().get(sector);
		}
		return null;
	}
	
	public Sector releaseResource(int cylinder,int sector){
		if(releaseSector(cylinder, sector)){
			ProcessDiskVisualizer.setContent("Removing Sector "+sector+" from cylinder "+cylinder);
			return diskobj.get(cylinder).getCylinderobj().get(sector);
		}
		return null;
	}
	
	public boolean occupySector(int cylinder,int sector){
		
		Sector temp= diskobj.get(cylinder).getCylinderobj().get(sector);
		if(!temp.isInUse()){
			temp.setInUse(true);
			DiskVisualiser.setContent("Adding Sector "+sector+" to cylinder "+cylinder);
			return true;
		}
		return false;
		
	}
	
	public boolean releaseSector(int cylinder,int sector){
		Sector temp= diskobj.get(cylinder).getCylinderobj().get(sector);
		if(temp.isInUse()){
			temp.setInUse(false);
			DiskVisualiser.setContent("Removing Sector "+sector+" from cylinder "+cylinder);
			return true;
		}
		return false;
	}
	
	

}
