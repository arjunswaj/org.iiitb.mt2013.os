package org.iiitb.model.process;

import java.io.File;
import java.util.List;

import org.iiitb.model.bean.ProcessBean;

public interface ProcessManager {
	
	public ProcessBean createProcesses();

	public List<ProcessBean> createProcesses(File file);

	public List<ProcessBean> createProcesses(int numProcess);

}
