package org.iiitb.model.util.test;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.controller.util.ProcessMemoryVisualiser;
import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.model.consts.ResourceType;

public class ProcessMemoryIntegrationTest {

	/**
	 * @param args
	 * @throws InvalidMemoryUnitException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InvalidMemoryUnitException,
			InterruptedException {
		ProcessMemoryVisualiser processMemoryVisualiser = new ProcessMemoryVisualiser();
		ProcessBean p1 = new ProcessBean(1, "Process 1");
		ProcessBean p2 = new ProcessBean(2, "Process 2");
		ProcessBean p3 = new ProcessBean(3, "Process 3");
		ProcessBean p4 = new ProcessBean(4, "Process 4");
		ProcessBean p5 = new ProcessBean(5, "Process 5");
		ProcessBean p6 = new ProcessBean(5, "Process 6");
		List<ProcessBean> readyQueue = new ArrayList<ProcessBean>();
		List<ProcessBean> waitingQueue = new ArrayList<ProcessBean>();

		readyQueue.add(p2);
		readyQueue.add(p3);
		readyQueue.add(p4);
		readyQueue.add(p5);
		readyQueue.add(p6);

		// Generating test data of 8 segments.
		Memory<MemorySegment> memory = new Memory<MemorySegment>(1024);
		int size = 50;
		for (int index = 0; index < 5; index += 1, size += 5) {
			MemorySegment memorySegment = new MemorySegment(2, "Segment", true,
					10, ResourceType.MEMORY, index * 100, size, index,
					"Segment: " + index);
			memory.add(memorySegment);
		}
		p1.setMemoryUnit(memory);

		processMemoryVisualiser.plotGraph(readyQueue, p1, waitingQueue);
		Thread.sleep(5000);

		// Generating test data of 5 segments.
		size = 75;
		for (int index = 0; index < 8; index += 1, size += 3) {
			MemorySegment memorySegment = new MemorySegment(2, "Segment", true,
					10, ResourceType.MEMORY, index * 100, size, index,
					"Segment: " + index);
			memory.add(memorySegment);
		}

		p4.setMemoryUnit(memory);

		readyQueue.remove(p2);
		waitingQueue.add(p2);

		readyQueue.remove(p3);
		waitingQueue.add(p3);
		waitingQueue.add(p1);

		processMemoryVisualiser.plotGraph(readyQueue, p4, waitingQueue);
		processMemoryVisualiser.reDraw(readyQueue, p4, waitingQueue, 50);
	}

}
