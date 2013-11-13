package org.iiitb.model.bean.segmentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;



public class InitialWindow extends JFrame implements ActionListener {
	
	int m;
	JLabel jl1;
	JTextField jt1;
	JButton jb1;
	
	
	public InitialWindow() {
		
		  setLocation(0,0);
		  setSize(400,300);	
		  setTitle("Initial Screen");
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  setLayout(null);
		  
		  jl1 = new JLabel("<html><body style='width: 10% px'> Enter The Size of Physical Memory :</html>");
		  jl1.setBounds(30,100,200,50);
		  
		  jt1 = new JTextField();
		  jt1.setBounds(250,115,80,30);
		  
		  jb1 = new JButton("Submit");
		  jb1.setBounds(160,200,100,30);
		  add(jl1);
		  add(jt1);
		  add(jb1);
		  
		  jb1.addActionListener(this);
		 
		 jt1.setText("");
		  
		  setVisible(true);
		  
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		if(o == jb1) {
			
			
			try {
				
				m = Integer.parseInt(jt1.getText());
				 if(jt1.getText().equals("") || m>64) {
					 
					 JOptionPane.showMessageDialog(null, "Invalid Value");
				 }
				 
				 else {
					 
					 this.setVisible(false);
					 
						this.dispose();
						long memorySize = m;
						MainWindow mw = new MainWindow(memorySize);
						
				 }
			} catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Invalid value");
				
			}
			
		}
		
	}
	
	 public static void main(String args[]) {

			Connection con;
			try {
				con = MySqlConnection.getConnection();
				String myQuery;
				PreparedStatement query;

				myQuery = "delete from Process";
				query = con.prepareStatement(myQuery);
				query.executeUpdate();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
		 new InitialWindow();
   	 }
}