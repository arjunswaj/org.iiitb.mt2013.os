package org.iiitb.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;

import org.iiitb.model.bean.Memory;
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

	JFrame window = new JFrame();

	/**
	 * Constructor for Segmentation Grapher
	 * 
	 * @param rid
	 *            resource Id
	 * @param resourceName
	 *            resource Name
	 * @param availability
	 *            is available
	 * @param ownerPid
	 *            owner process id
	 * @param rType
	 *            resource type
	 */
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

	/**
	 * Plot the graph using File as paramenter
	 * 
	 * @param file
	 *            file
	 */
	public void plotGraph(File file) {
		List<MemorySegment> memorySegments = readMemorySegmentsFromFile(file);
		SegmentView segmentView = new SegmentView(memorySize, memorySegments);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ViewConsts.SEGMENT_WINDOW_X_OFFSET,
				ViewConsts.SEGMENT_WINDOW_Y_OFFSET,
				ViewConsts.SEGMENT_WINDOW_WIDTH,
				ViewConsts.SEGMENT_WINDOW_HEIGHT);
		window.getContentPane().add(segmentView);
		window.setVisible(true);
	}

	/**
	 * Plot the graph using Memory unit as parameter
	 * 
	 * @param memoryUnit
	 *            memory unit
	 */
	public void plotGraph(Memory<MemorySegment> memoryUnit) {
		this.memorySize = memoryUnit.getSize();
		SegmentView segmentView = new SegmentView(memoryUnit);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ViewConsts.SEGMENT_WINDOW_X_OFFSET,
				ViewConsts.SEGMENT_WINDOW_Y_OFFSET,
				ViewConsts.SEGMENT_WINDOW_WIDTH,
				ViewConsts.SEGMENT_WINDOW_HEIGHT);
		window.getContentPane().add(segmentView);
		window.setVisible(true);
	}

	/**
	 * Call this when the memory segments updates and you have to update the
	 * view
	 * 
	 * @param memoryUnit
	 *            memory Unit
	 */
	public void reDraw(Memory<MemorySegment> memoryUnit) {
		this.memorySize = memoryUnit.getSize();
		SegmentView segmentView = new SegmentView(memoryUnit);
		window.getContentPane().removeAll();
		window.getContentPane().add(segmentView);
		window.setVisible(true);
	}

	/**
	 * Call this when the memory segments updates and you have to update the
	 * view
	 * 
	 * @param file
	 *            file
	 */
	public void reDraw(File file) {
		List<MemorySegment> memorySegments = readMemorySegmentsFromFile(file);
		SegmentView segmentView = new SegmentView(memorySize, memorySegments);
		window.getContentPane().removeAll();
		window.getContentPane().add(segmentView);
		window.setVisible(true);
	}

}
