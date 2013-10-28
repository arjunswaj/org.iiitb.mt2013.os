package org.iiitb.controller.util;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.iiitb.view.DiskSnapshot;
import org.iiitb.view.ProcessSnapshotView;
import org.iiitb.view.consts.DiskViewConsts;
import org.iiitb.view.consts.ViewConsts;

public class ProcessDiskVisualizer {
	JFrame window = new JFrame();
	DiskVisualiser dv = new DiskVisualiser();
	
	static JTextArea text = new JTextArea(100,100);

		
	public JTextArea getText() {
			return text;
		}
	public  void setText(JTextArea text) {
			text = text;
		}

	public static void setContent(String s){
		StringBuilder str = new StringBuilder(text.getText());
		str.append(s+"\n");
		//text.setFont(new Font(str.toString(), 5, 18));
		text.setText(str.toString());
	}

	public void plot(DiskSnapshot snapshot, ProcessSnapshotView pSnap){
		setText(dv.getText());
		JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			JLabel heading = new JLabel("PROCESS AND DISK SNAPSHOT");
			heading.setPreferredSize(new Dimension(20,20));
			c.gridx=0;
			c.gridy=0;
			c.weightx=0.05;
			c.weighty=0.05;
			c.anchor = GridBagConstraints.ABOVE_BASELINE;
			//c.fill = GridBagConstraints.BOTH;
			panel.add(heading,c);
			
			
			c.gridx=0;
			c.gridy=1;
			c.weightx=1;
			c.weighty=0.5;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			
			panel.add(pSnap,c);
			
			
			
			
			c.gridx=0;
			c.gridy=2;
			c.weightx=0.5;
			c.weighty=0.5;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			panel.add(snapshot,c);
			
			
			text.setEditable(false);
			c.gridx=1;
			c.gridy=2;
			c.weightx=0.2;
			c.weighty=0.2;
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			//text.setBounds(0, 0, 500, 500);
			//window.setLayout(new BoxLayout());
			panel.add(text,c);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setBounds(DiskViewConsts.WINDOW_X,DiskViewConsts.WINDOW_Y,
					DiskViewConsts.WINDOW_WIDTH,DiskViewConsts.WINDOW_HEIGHT);
			
			JScrollPane scrollPane = new JScrollPane(panel);
			
			
			scrollPane.setPreferredSize(new Dimension(
					ViewConsts.PROCESS_SEGMENT_SCROLL_WIDTH,
					ViewConsts.PROCESS_SEGMENT_SCROLL_HEIGHT));

			panel.setPreferredSize(new Dimension(
					ViewConsts.PROCESS_SEGMENT_PANEL_WIDTH,
					ViewConsts.PROCESS_SEGMENT_PANEL_HEIGHT));

		/*	window.setBounds(ViewConsts.SEGMENT_WINDOW_X_OFFSET,
					ViewConsts.SEGMENT_WINDOW_Y_OFFSET,
					ViewConsts.PROCESS_SEGMENT_WINDOW_WIDTH,
					ViewConsts.PROCESS_SEGMENT_WINDOW_HEIGHT);*/
			
			window.setExtendedState(window.getExtendedState()|JFrame.MAXIMIZED_BOTH);
			
			window.getContentPane().removeAll();
			window.getContentPane().add(scrollPane);
			window.repaint();
			window.setVisible(true);
		}
}
