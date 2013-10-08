package org.iiitb.model.util.test;

import java.io.File;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;
import org.iiitb.controller.util.SegmentationGrapher;

/**
 * Test Program to plot Segments
 * 
 * @author arjun
 * 
 */
public class SegmentationGraphTestProgram {

	public static void main(String[] args) throws InterruptedException,
			InvalidMemoryUnitException {
		File segmentFile = new File("segmentFile.csv");
		File reDrawsegmentFile = new File("reDrawsegmentFile.csv");
		SegmentationGrapher segmentationGrapher = new SegmentationGrapher(1,
				"Segment", true, 10, ResourceType.MEMORY);
		segmentationGrapher.plotGraph(segmentFile);
		Thread.sleep(5000);
		segmentationGrapher.reDraw(reDrawsegmentFile);

		Thread.sleep(5000);

		// Test Using Memory Module
		SegmentationGrapher segmentationGrapherUsingMemory = new SegmentationGrapher(
				1, "Segment", true, 10, ResourceType.MEMORY);
		Memory<MemorySegment> memory = new Memory<MemorySegment>(1024);

		// Generating test data of 5 segments.
		int size = 50;
		for (int index = 0; index < 5; index += 1, size += 5) {
			MemorySegment memorySegment = new MemorySegment(2, "Segment", true,
					10, ResourceType.MEMORY, index * 100, size, index,
					"Segment: " + index);
			memory.add(memorySegment);
		}
		segmentationGrapherUsingMemory.plotGraph(memory);
		Thread.sleep(5000);

		// Generating test data of 8 segments.
		size = 75;
		for (int index = 0; index < 8; index += 1, size += 3) {
			MemorySegment memorySegment = new MemorySegment(2, "Segment", true,
					10, ResourceType.MEMORY, index * 100, size, index,
					"Segment: " + index);
			memory.add(memorySegment);
		}

		segmentationGrapherUsingMemory.reDraw(memory);
	}
}
