package org.iiitb.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;

import javax.swing.JComponent;

import org.iiitb.model.bean.Cylinder;
import org.iiitb.model.bean.Disk;
import org.iiitb.model.bean.Sector;
import org.iiitb.view.consts.DiskViewConsts;



public class DiskSnapshot extends JComponent{
	
	private static final long serialVersionUID = 548911654877467475L;
	int numOfCylinders;
	int numOfSectors;
	int sectorSize;
	Disk disk;
	
	
	public Disk getDisk() {
		return disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public DiskSnapshot(int numOfCylinders,int numOfsectors,int sectorSize){
		this.numOfCylinders = numOfCylinders;
		this.numOfSectors = numOfsectors;
		this.sectorSize = sectorSize;
		
		disk = new Disk(numOfCylinders, numOfsectors, sectorSize);
		}
	
	public void paint(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		int i=10,j;
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(15));
		g.fillOval(DiskViewConsts.CENTER-10*20, DiskViewConsts.CENTER-i*20, DiskViewConsts.ARC_WIDTH+i*20*2, DiskViewConsts.ARC_HEIGHT+i*20*2);
		for(i = 0 ; i < numOfCylinders; i++){
			Cylinder cyl = disk.getDiskobj().get(i);
			
			for(j=0;j < numOfSectors;j++){
				Sector sec = cyl.getCylinderobj().get(j);
				if(sec == null)
					System.out.println("NULL");
				if(j%2==0)
					g.setColor(Color.gray);
				else
					g.setColor(Color.WHITE);
				if(sec.isInUse()){
					g.setColor(Color.RED);
					
					g.drawArc(DiskViewConsts.CENTER-i*20, DiskViewConsts.CENTER-i*20, DiskViewConsts.ARC_WIDTH+i*20*2, DiskViewConsts.ARC_HEIGHT+i*20*2, j*DiskViewConsts.ANGLE, DiskViewConsts.ANGLE);
					
				}
				else
				g.drawArc(DiskViewConsts.CENTER-i*20, DiskViewConsts.CENTER-i*20, DiskViewConsts.ARC_WIDTH+i*20*2, DiskViewConsts.ARC_HEIGHT+i*20*2, j*DiskViewConsts.ANGLE, DiskViewConsts.ANGLE);
			}
		}
		i--;
		
		i--;
		int x1 = DiskViewConsts.CENTER-i*20;
		int y1 = DiskViewConsts.CENTER-i*20+(DiskViewConsts.ARC_HEIGHT+i*20*2)/2;
		i=0;
		int x2 =  DiskViewConsts.CENTER-i*20;;
		int y2= DiskViewConsts.CENTER-i*20+(DiskViewConsts.ARC_HEIGHT+i*20*2)/2;;
		
		
/*		g.drawLine(x1, y1, x2, y2);
		for(i = 1;i<numOfSectors;i++){
		
		int x1_temp=((int)(x1*Math.cos(DiskViewConsts.ANGLE*Math.PI/180)))-((int)(y1* Math.sin(DiskViewConsts.ANGLE*Math.PI/180)));
        int y1_temp=((int)(x1*Math.sin(DiskViewConsts.ANGLE*Math.PI/180)))+((int)(y1* Math.cos(DiskViewConsts.ANGLE*Math.PI/180)));
        
        int x2_temp=((int)(x2*Math.cos(DiskViewConsts.ANGLE*Math.PI/180)))-((int)(y2* Math.sin(DiskViewConsts.ANGLE*Math.PI/180)));
        int y2_temp=((int)(x2*Math.sin(DiskViewConsts.ANGLE*Math.PI/180)))+((int)(y2* Math.cos(DiskViewConsts.ANGLE*Math.PI/180)));
        
        
        x1=x1_temp+DiskViewConsts.CENTER+100;
        x2=x2_temp+DiskViewConsts.CENTER+100;
        y1=y1_temp+DiskViewConsts.CENTER+100;
        y2=y2_temp+DiskViewConsts.CENTER+100;
        g.setColor(Color.blue);
        g.drawLine(x1, y1, x2, y2);
		}*/
        

		//g.drawLine(DiskViewConsts.CENTER-i*20, DiskViewConsts.CENTER-i*20+(DiskViewConsts.ARC_HEIGHT+i*20*2)/2,  DiskViewConsts.CENTER-i*20+(DiskViewConsts.ARC_WIDTH+i*20*2),  DiskViewConsts.CENTER-i*20+(DiskViewConsts.ARC_HEIGHT+i*20*2)/2);
		
	}
	
}