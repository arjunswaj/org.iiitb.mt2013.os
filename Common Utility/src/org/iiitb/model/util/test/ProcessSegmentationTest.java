package org.iiitb.model.util.test;

import java.io.File;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;
import org.iiitb.view.ProcessSnapshotView;
//import org.iiitb.controller.util.ProcessSegmentation;
import org.iiitb.controller.util.SegmentationGrapher;
import org.iiitb.controller.util.SnapshotRenderer;

public class ProcessSegmentationTest {


	public static void main(String[] args) throws InterruptedException {
		File processFile = new File("processSnapshot.csv");
		//ProcessSegmentation processSegmentation = new ProcessSegmentation();
		//processSegmentation.plotGraph(processFile);
		SnapshotRenderer snap = new SnapshotRenderer();
		snap.plotGraph(processFile);
		
	}
}
