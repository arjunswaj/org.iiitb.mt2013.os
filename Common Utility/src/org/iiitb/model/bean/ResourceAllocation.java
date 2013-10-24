package org.iiitb.model.bean;

import java.util.ArrayList;
import java.util.List;

public class ResourceAllocation {
	public boolean instanceAvailable = true;

	public boolean issueInstance(ProcessBean p, Resource r) {
		List<Resource> temp = new ArrayList<Resource>();

		if (r.isAvailability() && r.getNumOfInstance() > 0) {
			temp = p.getResources();

			temp.add(r);

			p.setResources(temp);
			r.removeInstance();
			setInstanceAvailable(true);
			//System.out.println(getInstanceAvailable());
			return true;
		} else {
			
			setInstanceAvailable(false);
			//System.out.println(getInstanceAvailable());
			return false;
		}
		
	}

	public void relinquishInstance(ProcessBean p, Resource r) {
		List<Resource> rTemp = new ArrayList<Resource>();
		rTemp = p.getResources();
		for (int i = 0; i < rTemp.size(); i++) {
			// System.out.println(rTemp.get(i).rid);
			if (rTemp.get(i).rid == r.rid) {
				rTemp.remove(r);
				r.addInstance();
			}
		}

		p.setResources(rTemp);
		// System.out.println(p.getResources().size());
		// System.out.println("added" + r.numOfInstance);
	}

	public boolean getInstanceAvailable() {
		return instanceAvailable;
	}

	public void setInstanceAvailable(boolean instanceAvailable) {
		this.instanceAvailable = instanceAvailable;
	}
}
