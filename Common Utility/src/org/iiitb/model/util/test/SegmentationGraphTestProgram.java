package org.iiitb.model.util.test;

import java.io.File;

import org.iiitb.model.consts.ResourceType;
import org.iiitb.view.util.SegmentationGrapher;

/**
 * Test Program to plot Segments
 * 
 * @author arjun
 * 
 */
public class SegmentationGraphTestProgram {

	public static void main(String[] args) throws InterruptedException {
		File segmentFile = new File("segmentFile.csv");
		File reDrawsegmentFile = new File("reDrawsegmentFile.csv");
		SegmentationGrapher segmentationGrapher = new SegmentationGrapher(1,
				"Segment", true, 10, ResourceType.MEMORY);
		segmentationGrapher.plotGraph(segmentFile);
		Thread.sleep(5000);
		segmentationGrapher.reDraw(reDrawsegmentFile);
	}

}
