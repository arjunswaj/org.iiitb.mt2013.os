package org.iiitb.model.bean.segmentation;

import java.awt.*;
import javax.swing.*;

import org.iiitb.controller.util.SegmentationGrapher;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;
import org.iiitb.view.SegmentView;


public class createPanel extends JPanel{

	private long memorySize;

	public createPanel(long size) {
		
		memorySize = (long) Math.pow(2, size);
		System.out.println(memorySize);
		/*	Memory<MemorySegment> memory = new Memory<MemorySegment>(memorySize);
			SegmentView segmentView = new SegmentView(memory);
			getContentPane().add(segmentView);
			setVisible(true);
			*/
		
		// Test Using Memory Module
		SegmentationGrapher segmentationGrapherUsingMemory = new SegmentationGrapher(
				1, "Segment", true, 10, ResourceType.MEMORY);
		Memory<MemorySegment> memory = new Memory<MemorySegment>(memorySize);	
		
		SegmentView segmentView = new SegmentView(memory);
		getRootPane().add(segmentView);
		
		
     }
	
	}
	

