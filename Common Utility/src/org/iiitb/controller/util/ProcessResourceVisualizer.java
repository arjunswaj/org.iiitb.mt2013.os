package org.iiitb.controller.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.iiitb.view.ProcessSnapshotView;
import org.iiitb.view.ResourceSnapshotView;
import org.iiitb.view.consts.ResourceViewConsts;

public class ProcessResourceVisualizer {
	
	JFrame window = new JFrame();
	JPanel panel;
	JTextArea actionText;
	GridBagLayout gb;
	GridBagConstraints gbc;
	
	
	public void plotProcess(ProcessSnapshotView psnapshot, String action){
		/*
		 * Adding text area to display action message
		 */
		panel = new JPanel(new GridBagLayout());
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		actionText = new JTextArea(ResourceViewConsts.TEXT_ROWS,
				ResourceViewConsts.TEXT_COLUMNS);
		actionText.setBackground(new Color(0, 20, 20));
		actionText.setForeground(new Color(255, 255, 255));
		actionText.setFont(new Font(action, 5, 18));
		actionText.setMargin(new Insets(30, 250, 0, 20));
		actionText.setText(action);
		actionText.setEditable(false);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,
				ResourceViewConsts.WINDOW_Y, ResourceViewConsts.WINDOW_WIDTH,
				ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.7;
		gbc.weighty = 0.7;
		panel.add(psnapshot, gbc);
		
		/*gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		panel.add(rsnapshot,gbc);*/

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = 200;
		gbc.ipady = 50;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		panel.add(actionText, gbc);
		window.getContentPane().add(panel);

		window.setVisible(true);
	}

	

}
