package org.iiitb.controller.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.iiitb.view.ResourceProcessView;
import org.iiitb.view.consts.ResourceViewConsts;

public class ProcessResourceVisualizer {
	
	JFrame window = new JFrame();
	JPanel panel;
	
	static JTextArea text = new JTextArea(100,100);

		
	public JTextArea getText() {
			return text;
		}
	public  void setText(JTextArea text) {
			ProcessResourceVisualizer.text = text;
		}

	public static void setContent(String s){
		StringBuilder str = new StringBuilder(text.getText());
		str.append(s+"\n");
		//text.setFont(new Font(str.toString(), 5, 18));
		text.setText(str.toString());
	}
	
	
	public void plotProcess(ResourceProcessView snap, String action){
	
		panel = new JPanel(new GridBagLayout());
		JTextArea actionText;
		GridBagConstraints gbc = new GridBagConstraints();
		
		actionText = new JTextArea(ResourceViewConsts.TEXT_ROWS,
				ResourceViewConsts.TEXT_COLUMNS);
		actionText.setBackground(new Color(0, 20, 20));
		actionText.setForeground(new Color(255, 255, 255));
		actionText.setFont(new Font(action, 5, 18));
		actionText.setMargin(new Insets(0, 250, 0, 20));
		actionText.setText(action);
		actionText.setEditable(false);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(ResourceViewConsts.WINDOW_X,
				ResourceViewConsts.WINDOW_Y, ResourceViewConsts.WINDOW_WIDTH,
				ResourceViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(snap, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipadx = 100;
		gbc.ipady = 100;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		panel.add(actionText, gbc);
		
		window.getContentPane().add(panel);
		window.setVisible(true);
	}

	

}
