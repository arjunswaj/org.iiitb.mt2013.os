package org.iiitb.model.resource;

import java.io.File;
import java.util.List;

import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.Resource;

public interface ResourceManager {


	public Resource acquireResources();

	public List<Resource> acquireResources(File file);

	public List<Resource> acquireResources(int numResources);
	
	public Memory createMemory();
}
