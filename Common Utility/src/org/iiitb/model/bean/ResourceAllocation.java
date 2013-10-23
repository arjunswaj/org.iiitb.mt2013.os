package org.iiitb.model.bean;

import java.util.ArrayList;
import java.util.List;

public class ResourceAllocation {

	public boolean issueInstance(ProcessBean p, Resource r) {
		List<Resource> temp = new ArrayList<Resource>();
		// System.out.println(r.getResourceName());
		if (r.isAvailability() && r.getNumOfInstance() > 0) {
			temp = p.getResources();
			// System.out.println(temp.size());
			temp.add(r);
			// System.out.println("resource allocated: " +
			// temp.get(temp.size()-1).rid);
			p.setResources(temp);
			r.removeInstance();
			// System.out.println("Resource added of size " + temp.size());
			// System.out.println("remove" + r.numOfInstance);
			return true;
		}
		return false;
	}

	public void relinquishInstance(ProcessBean p, Resource r) {
		List<Resource> rTemp = new ArrayList<Resource>();
		rTemp = p.getResources();
		for (int i = 0; i < rTemp.size(); i++) {
			//System.out.println(rTemp.get(i).rid);
			if (rTemp.get(i).rid == r.rid) {
				rTemp.remove(r);
				r.addInstance();
			}
		}

		p.setResources(rTemp);
		//System.out.println(p.getResources().size());
		// System.out.println("added" + r.numOfInstance);
	}
}
