package org.iiitb.controller.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.iiitb.view.DiskSnapshot;
import org.iiitb.view.Legend;

import org.iiitb.view.consts.DiskViewConsts;

public class DiskVisualiser {
	JFrame window = new JFrame();
	static JTextArea text = new JTextArea(100,100);
	
/*	public DiskVisualiser(){
		text.setBackground(new Color(0, 20, 20));
		text.setForeground(new Color(255, 255, 255));
		
		//text.setMargin(new Insets(30, 250, 0, 20));
	}*/
		
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

public void plot(DiskSnapshot snapshot){
	JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel heading = new JLabel("DISK SNAPSHOT");
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
		c.weightx=0.5;
		c.weighty=0.5;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		
		panel.add(snapshot,c);
		
		
		text.setEditable(false);
		c.gridx=1;
		c.gridy=1;
		c.weightx=0.2;
		c.weighty=0.2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		//text.setBounds(0, 0, 500, 500);
		//window.setLayout(new BoxLayout());
		panel.add(text,c);
		
		 Color[] col= {Color.RED,Color.WHITE,Color.GRAY}; 
		 String[] vals = {"IN-USE ","FREE","FREE"};
		// System.out.println("I"+vals[0]);
		Legend l =new Legend(3,vals,col);
			
		
		c.gridx=1;
		c.gridy=0;
		c.weightx=0.05;
		c.weighty=0.05;
		c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		c.fill = GridBagConstraints.CENTER;
		panel.add(l.getLegend(),c);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(DiskViewConsts.WINDOW_X,DiskViewConsts.WINDOW_Y,
				DiskViewConsts.WINDOW_WIDTH,DiskViewConsts.WINDOW_HEIGHT);
		window.getContentPane().removeAll();
		window.getContentPane().add(panel);
		window.repaint();
		window.setVisible(true);
	}


public void readDiskFromFile(File file) throws InterruptedException, IOException {
	
	int numofcylinders=0;
	int numofsectors=0;
	int sectorsize=0;
	int currentcylinder=0;
	int currentsector=0;
	String type;
	

	BufferedReader bufferedReader = null;
	
		bufferedReader = new BufferedReader(new FileReader(file));
	
	
	String line = null;
	
		line = bufferedReader.readLine();
	try{
	
	StringTokenizer st1 = new StringTokenizer(line, ",");
	
	numofcylinders=Integer.parseInt(st1.nextToken());
	numofsectors=Integer.parseInt(st1.nextToken());
	sectorsize=Integer.parseInt(st1.nextToken());
	//System.out.println(sectorsize);
	
	
	
	DiskSnapshot snap = new DiskSnapshot(numofcylinders,numofsectors,sectorsize);
	
	
		
			while (null != (line = bufferedReader.readLine())) {

				StringTokenizer st = new StringTokenizer(line, ",");
				type=st.nextToken();
				String o = new String("o");
				
				if(o.equalsIgnoreCase(type)){
					
				    currentcylinder=Integer.parseInt(st.nextToken());
				    currentsector=Integer.parseInt(st.nextToken());
				    //System.out.println(currentcylinder+" "+currentsector);
				    snap.getDisk().occupySector(currentcylinder, currentsector);
				    //plot(snap);
				    //plot(snap,"O");
				    plot(snap);
				    
				}
				else
				{
					currentcylinder=Integer.parseInt(st.nextToken());
					currentsector=Integer.parseInt(st.nextToken());
					//System.out.println(currentcylinder+" "+currentsector);
					snap.getDisk().releaseSector(currentcylinder, currentsector);
					plot(snap);
					//plot(snap,"R");
				}
				
				Thread.sleep(1000);
			}
	}
catch(NoSuchElementException e ){
	System.out.println("invalid input field");
	
}
}
}
