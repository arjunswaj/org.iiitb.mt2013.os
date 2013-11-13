package org.iiitb.model.bean.processScheduling.controller;
import java.util.List;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.processScheduling.controller.FCFS;
import org.iiitb.model.bean.processScheduling.model.ProcessOutputParamaters;
import org.iiitb.model.bean.processScheduling.model.ScheduleType;
import org.iiitb.model.bean.processScheduling.controller.HRRN;
import org.iiitb.model.bean.processScheduling.controller.IScheduler;
import org.iiitb.model.bean.processScheduling.controller.NonPreemptivePriority;
import org.iiitb.model.bean.processScheduling.controller.PreemptivePriority;
import org.iiitb.model.bean.processScheduling.controller.SJF;
import org.iiitb.model.bean.processScheduling.controller.SRT;
/*
 * This is Class connects to the respective Scheduler class
 */
public  class ProcessesScheduler {
	public ProcessOutputParamaters Schedule(List<ProcessBean> processList,ScheduleType scheduletyp)
	{
		ProcessOutputParamaters processoutputparameters;
		IScheduler ISchedule;
		ISchedule = GetScheduler(scheduletyp);
		processoutputparameters = ISchedule.Schedule(processList);
		return processoutputparameters;
	}
	
	/*
	 * Returns the scheduler type
	 */
	public IScheduler GetScheduler(ScheduleType scheduletyp)
	{
		IScheduler ISchedule; 
		switch (scheduletyp) {
         case FCFS:  ISchedule = new FCFS();
                  break;
         case SJF:  ISchedule = new SJF();
         break;
         case HRRN:  ISchedule = new HRRN();
         break;
         case PREMPTIVEPRIORITY:  ISchedule = new PreemptivePriority();
         break;
         case NONPREMPTIVEPRIORITY:  ISchedule = new NonPreemptivePriority();
         break;
         case SRT:  ISchedule = new SRT();
         break;
         default: 
         throw new IllegalArgumentException("Invalid Scheduler name: " + scheduletyp);
     }
		return ISchedule;
	}
	
	}
