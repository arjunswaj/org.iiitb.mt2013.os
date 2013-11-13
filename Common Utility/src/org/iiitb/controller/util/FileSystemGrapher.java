package org.iiitb.controller.util;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.iiitb.model.bean.files.FileSystem;
import org.iiitb.view.FilesView;
import org.iiitb.view.consts.ViewConsts;

/**
 * File System Grapher A utility to plot the Segmentation Graph from the CSV
 * file, Memory Interval Tree etc
 * 
 * @author arjun
 * 
 */
public class FileSystemGrapher {

	JFrame window = new JFrame();

	/**
	 * Plot the graph using Memory unit as parameter
	 * 
	 * @param memoryUnit
	 *            memory unit
	 */
	public void plotGraph(FileSystem fileSystem) {
		JComponent view = new FilesView(fileSystem);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ViewConsts.FILE_SYSTEM_WINDOW_X_OFFSET,
				ViewConsts.FILE_SYSTEM_WINDOW_Y_OFFSET,
				ViewConsts.FILE_SYSTEM_WINDOW_WIDTH,
				ViewConsts.FILE_SYSTEM_WINDOW_HEIGHT);
		window.getContentPane().add(view);
		window.setVisible(true);
	}

	/**
	 * Call this when the memory segments updates and you have to update the
	 * view
	 * 
	 * @param memoryUnit
	 *            memory Unit
	 */
	public void reDraw(FileSystem fileSystem) {
		window.getContentPane().removeAll();
		this.plotGraph(fileSystem);
	}

}
