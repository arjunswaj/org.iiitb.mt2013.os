package org.iiitb.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;



public class Legend  {

	int number;
	String vals[];
	Color[] col;
	JPanel panel;
	
	public Legend(int n,String[] vals,Color[] col){
		panel = new JPanel(new GridBagLayout());
		number=n;
		this.vals = new String[n];
		for(int i =0;i<n;i++)
			this.vals[i]=vals[i];
		
		this.col = new Color[n];
		for(int i =0;i<n;i++)
			this.col[i]=col[i];
		createLegend();
	}
	
	public JPanel getLegend() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private void createLegend(){
		
		GridBagConstraints c = new GridBagConstraints();
		
		for(int i = 0; i< number;i++){
			
		JTable table = new JTable(1,1);
		table.setBackground(col[i]);
		//table.getColumnModel().getColumn(0).setPreferredWidth(10);
		c.insets = new Insets(0,10,0,0);
		c.gridx=0;
		c.gridy=i;
		//c.ipadx =0;
		c.weightx=0.5;
		c.weighty=0.5;
		panel.add(table,c);
		
	//	System.out.println(vals[i]);
		JLabel label = new JLabel(vals[i]);
		
		    c.gridx=1;
			c.gridy=i;
			//c.ipadx = 3;
			c.weightx=0.5;
			c.weighty=0.5;
			c.anchor=GridBagConstraints.CENTER;
			
			panel.add(label,c);
		}
		
		/*JTable table1 = new JTable(1,1);
		table1.setBackground(Color.WHITE);
		c.gridx=0;
		c.gridy=1;
		c.weightx=0.05;
		c.weighty=0.05;
		panel.add(table1,c);
		
		JTable table2 = new JTable(1,1);
		table2.setBackground(Color.GRAY);
		c.gridx=0;
		c.gridy=2;
		c.weightx=0.05;
		c.weighty=0.05;
		panel.add(table2,c);
	
	   
		
		  JLabel white = new JLabel(" FREE");
		    c.gridx=1;
			c.gridy=1;
			c.weightx=0.05;
			c.weighty=0.05;
			panel.add(white,c);
			
			JLabel gray = new JLabel(" FREE");
		    c.gridx=1;
			c.gridy=2;
			c.weightx=0.05;
			c.weighty=0.05;
			panel.add(gray,c);
			
	    
	
	//return panel;
*/	}
}
