package org.iiitb.view;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DisplayStatus {
	
JTextField textArea ;
int textbox_X;
int textbox_Y;
int rowcount;
int columncount;

public DisplayStatus(){
	
}

public DisplayStatus(int textbox_X, int textbox_Y, int rowcount, int columncount ){
	this.textbox_X = textbox_X;
	this.textbox_Y = textbox_Y;
	this.rowcount = rowcount;
	this.columncount = columncount;
	
	textArea = new JTextField(columncount);
	
}



public int getTextbox_X() {
	return textbox_X;
}

public void setTextbox_X(int textbox_X) {
	this.textbox_X = textbox_X;
}

public int getTextbox_Y() {
	return textbox_Y;
}

public void setTextbox_Y(int textbox_Y) {
	this.textbox_Y = textbox_Y;
}

public int getRowcount() {
	return rowcount;
}

public void setRowcount(int rowcount) {
	this.rowcount = rowcount;
}

public int getColumncount() {
	return columncount;
}

public void setColumncount(int columncount) {
	this.columncount = columncount;
}



public JTextField getTextArea() {
	return textArea;
}



public void setTextArea(JTextField textArea) {
	this.textArea = textArea;
}

public void setContent(String msg){
	this.textArea.setText(msg);
}

public String getContent(){
	return this.textArea.getText();
}

 
}
