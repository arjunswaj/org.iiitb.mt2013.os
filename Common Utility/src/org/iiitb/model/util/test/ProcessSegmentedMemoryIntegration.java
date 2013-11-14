package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.bean.TimeQuantum;
import org.iiitb.model.bean.processScheduling.controller.FCFS;
import org.iiitb.model.bean.segmentation.SegmentTableEntry;
import org.iiitb.model.bean.segmentation.SegmentedMemoryWrapper;
import org.iiitb.model.consts.BurstType;

/**
 * @author kempa
 * 
 */
public class ProcessSegmentedMemoryIntegration
{

	final static int nSegments = 5;
	final static int nReferences = 5;
	final static int nProcesses = 3;
	final static int burstTime = 5;
	final static int processSize = 20;
	final static int memorySize = 6; // in terms of powers of two

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SegmentedMemoryWrapper smw = new SegmentedMemoryWrapper(memorySize);

		List<ProcessBean> processList = new ArrayList<ProcessBean>();
		List<TimeQuantum> burstList;
		TimeQuantum timequant;
		Random r = new Random();

		HashMap<Integer, Long[]> references = new HashMap<Integer, Long[]>();
		for (int count = 0; count < nProcesses; ++count)
		{
			int pId = smw.loadProcess(processSize);
			references.put(new Integer(pId),
					generateMemoryReferences(pId, smw, smw.getSegmentSizes(processSize)));
			ProcessBean processbean = new ProcessBean(pId, "P" + pId);
			processbean.setMemoryUnit(smw.getMemory());
			processbean.setLogicalAddressSpacesize(processSize);
			@SuppressWarnings("deprecation")
			Date date = new Date(2013, 10, 0, 0, r.nextInt() % nProcesses);
			processbean.setArrivalTime(date);
			timequant = new TimeQuantum();
			timequant.setType(BurstType.CPU);
			timequant.setQuantum(burstTime);
			burstList = new ArrayList<TimeQuantum>();
			burstList.add(timequant);
			processbean.setBurstList(burstList);
			processList.add(processbean);
		}
		new FCFS().Schedule(processList, references);
	}

	/**
	 * Generates and returns 5 physical address of process pId to mimic execution of a process
	 * 
	 * @param segmentSize
	 * @return
	 */
	public static Long[] generateMemoryReferences(int pId,
			SegmentedMemoryWrapper smw, long segmentSize[])
	{
		Long physicalAddress[] = new Long[nReferences];
		HashMap<Integer, SegmentTableEntry> segmentTable = smw
				.getSegmentTable(pId);
		
		Random r = new Random();
		for (int i = 0; i < nReferences; i++)
		{
			int sid;
			while ((sid = Math.abs(r.nextInt()) % nSegments) == 0)
				;
			physicalAddress[i] = segmentTable.get(sid).getbAddress() + Math.abs(r.nextLong())
					% segmentSize[sid];
		}
		return physicalAddress;
	}
}
