package org.iiitb.model.bean;
/**
 * 
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.iiitb.model.bean.processScheduling.controller.FCFS;
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
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SegmentedMemoryWrapper smw = new SegmentedMemoryWrapper(6);

		int processCount, count = 0, pId, arrivalTime, burstTime;
		List<ProcessBean> processList = new ArrayList<ProcessBean>();
		List<TimeQuantum> burstList;
		TimeQuantum timequant;
		System.out.println("Please enter number of process");
		Scanner inputReader = new Scanner(System.in);

		// Getting input in integer format
		processCount = inputReader.nextInt();

		HashMap<Integer, List<SegmentedMemoryReference>> references = new HashMap(); 
		for (count = 0; count < processCount; ++count)
		{
			int size = 10;
			pId = smw.loadProcess(size);
			references.put(pId, generateMemoryReferences(smw.getSegmentSizes(size)));
			ProcessBean processbean = new ProcessBean(pId, "P" + pId);
			processbean.setMemoryUnit(smw.getMemory());
			processbean.setLogicalAddressSpacesize(size);

			
			System.out.println("Please arrival time for the process");
			arrivalTime = inputReader.nextInt();
			@SuppressWarnings("deprecation")
			Date date = new Date(2013, 10, 0, 0, arrivalTime);
			processbean.setArrivalTime(date);
			System.out.println("Please burst time for the process");
			timequant = new TimeQuantum();
			timequant.setType(BurstType.CPU);
			burstTime = inputReader.nextInt();
			timequant.setQuantum(burstTime);
			burstList = new ArrayList<TimeQuantum>();
			burstList.add(timequant);
			processbean.setBurstList(burstList);
			processList.add(processbean);
		}
		new FCFS().Schedule(processList, references);

		inputReader.close();
		// SegmentationGrapher segmentationGrapherUsingMemory = new
		// SegmentationGrapher(
		// 1, "Segment", true, 10, ResourceType.MEMORY);
		// segmentationGrapherUsingMemory.plotGraph(smw.getMemory());
	}

	public static List<SegmentedMemoryReference> generateMemoryReferences(long segmentSize[])
	{
		List<SegmentedMemoryReference> references = new LinkedList<SegmentedMemoryReference>();
		Random r = new Random();
		for (int i = 0; i < nReferences; i++)
		{
			int sid; 
			while ((sid = r.nextInt() % nSegments) != 0);
			references.add(new SegmentedMemoryReference(i, r.nextLong() % segmentSize[sid]));
		}
		return references;
	}
}
