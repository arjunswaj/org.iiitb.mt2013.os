package org.iiitb.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;

import javax.swing.JComponent;

import org.iiitb.controller.util.DiskVisualiser;
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
		int i=numOfCylinders,j;
		int spanangle;
		spanangle=360/numOfSectors;
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(15));
		g.fillOval(DiskViewConsts.CENTER-i*20, DiskViewConsts.CENTER-i*20, DiskViewConsts.ARC_WIDTH+i*20*2, DiskViewConsts.ARC_HEIGHT+i*20*2);
		for(i = 0 ; i < numOfCylinders; i++){
			Cylinder cyl = disk.getDiskobj().get(i);
			
			for(j=0;j < numOfSectors;j++){
				Sector sec = disk.getDiskobj().get(i).getCylinderobj().get(j);
				
				if(sec == null)
					System.out.println("NULL");
				if(j%2==0)
					g.setColor(Color.gray);
				else
					g.setColor(Color.WHITE);
			
				if(sec.isInUse()){
					g.setColor(Color.RED);					
				}
				
				g.drawArc(DiskViewConsts.CENTER-i*20, DiskViewConsts.CENTER-i*20, DiskViewConsts.ARC_WIDTH+i*20*2, DiskViewConsts.ARC_HEIGHT+i*20*2, j*spanangle, spanangle);
		  
			}
		}
			
	}
	
}