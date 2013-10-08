package org.iiitb.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;

import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.view.consts.ViewConsts;

/**
 * Segment View. Plots the Segments
 * 
 * @author arjun
 * 
 */
public class SegmentView extends JComponent {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3361893591327175621L;
	private long memorySize;
	private List<MemorySegment> memorySegmentList;
	Memory<MemorySegment> memoryUnit;
	private double scalingFactor;

	public SegmentView(long memorySize, List<MemorySegment> memorySegmentList) {
		this.memorySize = memorySize;
		this.memorySegmentList = memorySegmentList;
		this.scalingFactor = ((double) this.memorySize)
				/ (ViewConsts.SEGMENT_WINDOW_HEIGHT - 100);
	}

	public SegmentView(Memory<MemorySegment> memoryUnit) {
		this.memorySize = memoryUnit.getSize();
		this.memoryUnit = memoryUnit;
		this.scalingFactor = ((double) this.memorySize)
				/ (ViewConsts.SEGMENT_WINDOW_HEIGHT - 100);
	}

	public void paint(Graphics g) {
		int height = (int) ((double) memorySize / scalingFactor);
		/*
		 * System.out.println("memory size: " + memorySize + " scaling factor: "
		 * + scalingFactor + " height: " + height);
		 */
		g.drawString("Visualisation of Memory Segmentation",
				ViewConsts.SEGMENT_TITLE_X_MARGIN,
				ViewConsts.SEGMENT_TITLE_Y_MARGIN);

		g.draw3DRect(ViewConsts.SEGMENT_VIEW_X_MARGIN,
				ViewConsts.SEGMENT_VIEW_Y_MARGIN,
				ViewConsts.SEGMENT_VIEW_WIDTH, height
						+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS, true);

		g.drawString(String.valueOf(0), ViewConsts.SEGMENT_TEXT_LEFT_X_MARGIN,
				ViewConsts.SEGMENT_VIEW_Y_MARGIN);

		g.drawString(String.valueOf(memorySize),
				ViewConsts.SEGMENT_TEXT_LEFT_X_MARGIN,
				ViewConsts.SEGMENT_VIEW_Y_MARGIN + height
						+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);

		boolean isLeft = false;
		Iterable memoryList = memorySegmentList;
		if (null == memorySegmentList) {
			memoryList = memoryUnit.getAll();
		}
		for (Object object : memoryList) {
			MemorySegment memorySegment = (MemorySegment) object;
			height = (int) ((double) memorySegment.getSize() / scalingFactor);
			int yCoOrd = (int) ((double) memorySegment.getAddress() / scalingFactor)
					+ ViewConsts.SEGMENT_VIEW_Y_MARGIN;
			/*
			 * System.out.println("memory unit size: " + memorySegment.getSize()
			 * + " scaling factor: " + scalingFactor + " height: " + height +
			 * " memory start address: " + memorySegment.getAddress() +
			 * " memory scaled address: " + yCoOrd);
			 */
			g.fill3DRect(ViewConsts.SEGMENT_VIEW_X_MARGIN, yCoOrd,
					ViewConsts.SEGMENT_VIEW_WIDTH, height, true);

			int xSegmentName = (ViewConsts.SEGMENT_VIEW_X_MARGIN + ViewConsts.SEGMENT_VIEW_WIDTH) / 3;
			int ySegmentName = ((2 * yCoOrd) + height) / 2;
			g.setColor(new Color(0xCC, 0xCC, 0xCC));
			g.drawString("Number: " + memorySegment.getSegmentNumber()
					+ ", Name: " + memorySegment.getSegmentName() + ", Size: "
					+ memorySegment.getSize(), xSegmentName, ySegmentName
					+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
			g.setColor(new Color(0x00, 0x00, 0x00));

			if (isLeft) {
				g.drawString(String.valueOf(memorySegment.getAddress()),
						ViewConsts.SEGMENT_TEXT_LEFT_X_MARGIN, yCoOrd
								+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
				g.drawString(
						String.valueOf(memorySegment.getAddress()
								+ memorySegment.getSize()),
						ViewConsts.SEGMENT_TEXT_LEFT_X_MARGIN, yCoOrd + height
								+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
			} else {
				g.drawString(String.valueOf(memorySegment.getAddress()),
						ViewConsts.SEGMENT_TEXT_RIGHT_X_MARGIN, yCoOrd
								+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
				g.drawString(
						String.valueOf(memorySegment.getAddress()
								+ memorySegment.getSize()),
						ViewConsts.SEGMENT_TEXT_RIGHT_X_MARGIN, yCoOrd + height
								+ ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
			}
			isLeft = !isLeft;
		}
	}
}
