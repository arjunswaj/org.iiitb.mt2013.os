package org.iiitb.view.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;
import org.iiitb.view.SegmentView;
import org.iiitb.view.consts.ViewConsts;

/**
 * Segmentation Grapher A utility to plot the Segmentation Graph from the CSV
 * file, Memory Interval Tree etc
 * 
 * @author arjun
 * 
 */
public class SegmentationGrapher {

	private int rid;
	private String resourceName;
	private boolean availability;
	private int ownerPid;
	private ResourceType rType;
	private long memorySize;
	
	public SegmentationGrapher(int rid, String resourceName,
			boolean availability, int ownerPid, ResourceType rType) {
		super();
		this.rid = rid;
		this.resourceName = resourceName;
		this.availability = availability;
		this.ownerPid = ownerPid;
		this.rType = rType;		
	}

	private List<MemorySegment> readMemorySegmentsFromFile(File file) {
		List<MemorySegment> memorySegments = new ArrayList<MemorySegment>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			line = bufferedReader.readLine();
			this.memorySize = Long.parseLong(line);
			while (null != (line = bufferedReader.readLine())) {
				StringTokenizer st = new StringTokenizer(line, ",");
				if (4 == st.countTokens()) {
					long segmentNumber = Long.parseLong(st.nextToken());
					long startAddress = Long.parseLong(st.nextToken());
					long segmentSize = Long.parseLong(st.nextToken());
					String segmentName = st.nextToken();
					MemorySegment memorySegment = new MemorySegment(rid,
							resourceName, availability, ownerPid, rType,
							startAddress, segmentSize, segmentNumber,
							segmentName);
					memorySegments.add(memorySegment);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return memorySegments;
	}

	public void plotGraph(File file) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ViewConsts.SEGMENT_WINDOW_X_OFFSET,
				ViewConsts.SEGMENT_WINDOW_Y_OFFSET,
				ViewConsts.SEGMENT_WINDOW_WIDTH,
				ViewConsts.SEGMENT_WINDOW_HEIGHT);

		List<MemorySegment> memorySegments = readMemorySegmentsFromFile(file);
		SegmentView segmentView = new SegmentView(memorySize, memorySegments);
		window.getContentPane().add(segmentView);
		window.setVisible(true);
	}

}
