package org.iiitb.model.bean.segmentation;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CreateAProcess {

	long[] segmentArray = new long[6]; //Note : First position has the value on whether process was created successfully or not
	
	public static int calculateSize(String s)
	{
		byte[] sizeOfString = s.getBytes(); //Taking UTF-8 into consideration, where 8bits = 1 byte i.e. 1 character = 1 byte
		int i = sizeOfString.length;
		return i;
	}
	
	public static String sizeConverter (double size)
	{
		if (size<=1024) 
		{
			return (size+ " Bytes");
		}
		else if (size>=1024) 
		{	
			size = size/1024;
			size = Math.round(size * 100.0) / 100.0;
			return (size + " KB"); 
		}
		else if (size >= (1024*1024)) 
		{
			size = size/(1024*1024);
			size = Math.round(size * 100.0) / 100.0;
			return (size + " MB"); 
		}
		else if (size >= (1024*1024*1024))
		{
			size = size/(1024*1024*1024);
			size = Math.round(size * 100.0) / 100.0;
			return (size + " GB"); 
		}
		else 
	return null;
	}
	
	public long[] ProcessInputCode() {
	
		   
		 JTextArea tf = new JTextArea(30,50);
		 tf.setLineWrap(true);
		    tf.setWrapStyleWord(true);
		    JScrollPane scrollPane = new JScrollPane(tf);  
	      
	         
		
		int result = JOptionPane.showConfirmDialog(null, scrollPane, "Input", JOptionPane.OK_CANCEL_OPTION);
        
		if (result==JOptionPane.OK_OPTION) 
		{
            String code = tf.getText();
            segmentArray[0]=1;
            performCalculations(code);
            return segmentArray;
        } 
		else 
        {
            System.out.println("User did not chose an option!");
            segmentArray[0] = 0;
            return segmentArray;
        }
    }
	
	public void performCalculations (String code)
	{
		double size = calculateSize(code);
		int sizeCalc = (int)size;
		System.out.println("Size of given text is:" + sizeConverter(size));
					
		int CONSTANTS_SIZE = sizeCalc/10;
		sizeCalc -= CONSTANTS_SIZE;
		segmentArray[1]=CONSTANTS_SIZE;
		
		int GLOBALS_SIZE = sizeCalc/8;
		sizeCalc -= GLOBALS_SIZE;
		segmentArray[2]=GLOBALS_SIZE;
		
		int CODE_SIZE = sizeCalc/6;
		sizeCalc -= CODE_SIZE;
		segmentArray[3]=CODE_SIZE;
		
		int HEAP_SIZE = sizeCalc/3;
		segmentArray[4]=HEAP_SIZE;
		
		int STACK_SIZE = sizeCalc - HEAP_SIZE;
		segmentArray[5] = STACK_SIZE;
	
		System.out.println(CONSTANTS_SIZE + ", "+ GLOBALS_SIZE+", " + CODE_SIZE+ ", " + HEAP_SIZE+ ", "+ STACK_SIZE);
	}
	/**
	 * @param args
	 */
	

}
