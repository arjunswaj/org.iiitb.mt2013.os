package org.iiitb.controller.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.iiitb.view.Legend;
import org.iiitb.view.ResourceProcessView;
import org.iiitb.view.consts.ResourceViewConsts;

public class ProcessResourceVisualizer {
	
	JFrame window = new JFrame();
	JPanel panel;
	
	static JTextArea actionText = new JTextArea(ResourceViewConsts.TEXT_ROWS,
			ResourceViewConsts.TEXT_COLUMNS);;

		
	public JTextArea getText() {
			return actionText;
		}
	public  void setText(JTextArea text) {
			ProcessResourceVisualizer.actionText = text;
		}

	public static void setContent(String s){
		StringBuilder str = new StringBuilder(actionText.getText());
		str.append(s+"\n");
		//text.setFont(new Font(str.toString(), 5, 18));
		actionText.setText(str.toString());
	}
	
	
	public void plotProcess(ResourceProcessView snap, String action){
	
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,
				ResourceViewConsts.WINDOW_Y, ResourceViewConsts.WINDOW_WIDTH,
				ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		
		JLabel heading = new JLabel("PROCESS AND RESOURCE GRAPH");
		heading.setPreferredSize(new Dimension(20,20));
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.05;
		gbc.weighty=0.05;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
		panel.add(heading,gbc);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(snap, gbc);
		
		Color[] col= {Color.BLUE,Color.GREEN,Color.RED,Color.RED,Color.RED}; 
		String[] vals = {"RESOURCES ALLOCATED","CURRENT PROCESS","UNAVAILABLE RESOURCES","UNAVAILABLE RESOURCES","UNAVAILABLE RESOURCES"};
		Legend legendObj =new Legend(5,vals,col);		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridheight=1;
		gbc.weightx=0.005;
		gbc.weighty=0.05;	
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(0,10,0,0);
		panel.add(legendObj.getLegend(),gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.ipadx = 100;
		gbc.ipady = 100;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		actionText.setBackground(new Color(0, 20, 20));
		actionText.setForeground(new Color(255, 255, 255));
		actionText.setFont(new Font(action, 5, 18));
		actionText.setMargin(new Insets(0, 250, 0, 20));
		setContent(action);
		actionText.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(actionText);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll, gbc);
		
		//panel.add(actionText, gbc);
		
		window.getContentPane().add(panel);
		window.setVisible(true);
	}

	

}
