package org.iiitb.model.bean.processScheduling.controller;
import java.util.List;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.processScheduling.model.ProcessOutputParamaters;
/*
 * Interface to implement basic functionalities in Scheduling
 */
public interface IScheduler {
	
public ProcessOutputParamaters Schedule(List<ProcessBean> processList);
//public ProcessOutputParamaters CalculateParameters(List<ProcessBean> processlist);

}
