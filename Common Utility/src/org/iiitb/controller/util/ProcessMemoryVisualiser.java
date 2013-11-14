package org.iiitb.controller.util;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.bean.ProcessBean;
import org.iiitb.view.SegmentView;
import org.iiitb.view.VerticalProcessSnapshotView;
import org.iiitb.view.consts.ViewConsts;

public class ProcessMemoryVisualiser {

  private long memorySize;

  JFrame window = new JFrame();

  /**
   * Plot the graph using Memory unit as parameter
   * 
   * @param memoryUnit
   *          memory unit
   */
  public void plotGraph(List<ProcessBean> readyQueue,
      ProcessBean currentProcess, List<ProcessBean> waitQueue) {
    Memory<MemorySegment> memoryUnit = currentProcess.getMemoryUnit();
    this.memorySize = memoryUnit.getSize();

    SegmentView segmentView = new SegmentView(memoryUnit);

    plotSegment(readyQueue, currentProcess, waitQueue, segmentView);
  }

  private void plotSegment(List<ProcessBean> readyQueue,
      ProcessBean currentProcess, List<ProcessBean> waitQueue,
      SegmentView segmentView) {
    VerticalProcessSnapshotView snap = new VerticalProcessSnapshotView(readyQueue,
        currentProcess, waitQueue, 0);

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    GridBagLayout processsSegmentGrid = new GridBagLayout();
    JPanel jPanel = new JPanel(processsSegmentGrid);

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 0.5;
    constraints.weighty = 0.01;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.fill = GridBagConstraints.BOTH;

    JLabel processSimulation = new JLabel("Process Queue Simulation");
    JLabel memorySimulation = new JLabel("Memory Simulation of Current Process");

    jPanel.add(processSimulation, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weightx = 0.5;
    constraints.weighty = 0.6;
    jPanel.add(snap, constraints);

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.weightx = 0.5;
    constraints.weighty = 0.01;
    constraints.ipadx = 100;
    jPanel.add(memorySimulation, constraints);

    constraints.ipadx = 0;
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.weightx = 0.5;
    constraints.weighty = 0.6;
    jPanel.add(segmentView, constraints);

    JScrollPane scrollPane = new JScrollPane(jPanel);
    scrollPane.setPreferredSize(new Dimension(
        ViewConsts.PROCESS_SEGMENT_SCROLL_WIDTH,
        ViewConsts.PROCESS_SEGMENT_SCROLL_HEIGHT));

    jPanel.setPreferredSize(new Dimension(
        ViewConsts.PROCESS_SEGMENT_PANEL_WIDTH,
        ViewConsts.PROCESS_SEGMENT_PANEL_HEIGHT));

    window.setBounds(ViewConsts.SEGMENT_WINDOW_X_OFFSET,
        ViewConsts.SEGMENT_WINDOW_Y_OFFSET,
        ViewConsts.PROCESS_SEGMENT_WINDOW_WIDTH,
        ViewConsts.PROCESS_SEGMENT_WINDOW_HEIGHT);

    window.getContentPane().add(scrollPane);
    window.setVisible(true);
    window.setTitle("Process Memory Visualiser");
  }

  /**
   * Call this when the memory segments updates and you have to update the view
   * 
   * @param memoryUnit
   *          memory Unit
   */
  public void reDraw(List<ProcessBean> readyQueue, ProcessBean currentProcess,
      List<ProcessBean> waitQueue, long accessedAddress) {
    window.getContentPane().removeAll();
    Memory<MemorySegment> memoryUnit = currentProcess.getMemoryUnit();
    this.memorySize = memoryUnit.getSize();
    SegmentView segmentView = new SegmentView(memoryUnit, accessedAddress);
    plotSegment(readyQueue, currentProcess, waitQueue, segmentView);

  }
  
  /**
   * Call this when the memory segments updates and you have to update the view
   * 
   * @param memoryUnit
   *          memory Unit
   */
  public void reDraw(List<ProcessBean> readyQueue, ProcessBean currentProcess,
      List<ProcessBean> waitQueue) {
    window.getContentPane().removeAll();
    Memory<MemorySegment> memoryUnit = currentProcess.getMemoryUnit();
    this.memorySize = memoryUnit.getSize();
    SegmentView segmentView = new SegmentView(memoryUnit);
    plotSegment(readyQueue, currentProcess, waitQueue, segmentView);

  }
}
