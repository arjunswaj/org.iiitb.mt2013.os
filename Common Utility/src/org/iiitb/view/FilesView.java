package org.iiitb.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import org.iiitb.model.bean.files.FileSystem;
import org.iiitb.model.bean.files.FileUnit;
import org.iiitb.model.bean.files.Files;
import org.iiitb.model.bean.files.IndexedFileUnit;
import org.iiitb.model.bean.files.LinkedFileUnit;
import org.iiitb.view.consts.ViewConsts;

/**
 * fileSystem View. Plots the fileSystems
 * 
 * @author arjun
 * 
 */
public class FilesView extends JComponent {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 3361893591327175621L;
  private FileSystem fileSystem;
  private double scalingFactor;

  public FilesView(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
    this.scalingFactor = ((double) this.fileSystem.getDiskSize())
        / (ViewConsts.FILE_SYSTEM_WINDOW_HEIGHT - 100);
  }

  public void allocationSpecificLabels(Graphics g, Files files,
      FileUnit fileUnit, int x, int y) {
    switch (fileSystem.getFileAllocationType()) {
    case CONTIGUOUS:
      g.drawString("File Number: " + files.getFileNumber(), x, y
          + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
      break;
    case INDEXED:
      g.drawString("File Number: " + files.getFileNumber() + ", File Index: "
          + fileUnit.getIndex(), x, y + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
      break;
    case LINKED:
      g.drawString(
          "File Number: " + files.getFileNumber()
              + ", Next Block Memory Location: "
              + ((LinkedFileUnit) fileUnit).getNextAddress(), x, y
              + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
      break;
    default:
      break;

    }
  }

  public void allocationSpecificTitles(Graphics g) {
    g.setColor(new Color(0x00, 0x00, 0x00));
    String title = null;
    switch (fileSystem.getFileAllocationType()) {
    case CONTIGUOUS:
      title = "Visualisation of Contiguous Allocation. File Block Size: "
          + fileSystem.getFileUnitSize();
      break;
    case INDEXED:
      title = "Visualisation of File Allocation. File Block Size: "
          + fileSystem.getFileUnitSize();
      break;
    case LINKED:
      title = "Visualisation of Linked Allocation. File Block Size: "
          + fileSystem.getFileUnitSize();
      break;
    default:
      break;

    }
    g.drawString(title, ViewConsts.FILE_SYSTEM_TITLE_X_MARGIN,
        ViewConsts.FILE_SYSTEM_TITLE_Y_MARGIN);
  }

  public void paint(Graphics g) {
    int height = (int) ((double) this.fileSystem.getDiskSize() / scalingFactor);
    /*
     * System.out.println("memory size: " + memorySize + " scaling factor: " +
     * scalingFactor + " height: " + height);
     */

    allocationSpecificTitles(g);

    g.draw3DRect(ViewConsts.FILE_SYSTEM_VIEW_X_MARGIN,
        ViewConsts.FILE_SYSTEM_VIEW_Y_MARGIN,
        ViewConsts.FILE_SYSTEM_VIEW_WIDTH, height
            + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS, true);

    g.drawString(String.valueOf(0), ViewConsts.FILE_SYSTEM_TEXT_LEFT_X_MARGIN,
        ViewConsts.FILE_SYSTEM_VIEW_Y_MARGIN);

    g.drawString(String.valueOf(this.fileSystem.getDiskSize()),
        ViewConsts.FILE_SYSTEM_TEXT_LEFT_X_MARGIN,
        ViewConsts.FILE_SYSTEM_VIEW_Y_MARGIN + height
            + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);

    Iterable fileList = fileSystem.getFiles();

    height = (int) ((double) fileSystem.getFileUnitSize() / scalingFactor);

    int clr = 0x00;
    int widthOfLine = 0;
    for (Object object : fileList) {
      Files files = (Files) object;

      int prevYCoOrd = 0;
      for (FileUnit fileUnit : files.getFileUnits()) {

        int yCoOrd = (int) ((double) (fileUnit.getIndex() * fileSystem
            .getFileUnitSize()) / scalingFactor)
            + ViewConsts.FILE_SYSTEM_VIEW_Y_MARGIN;

        g.setColor(new Color(0x00, clr, clr));
        g.fill3DRect(ViewConsts.FILE_SYSTEM_VIEW_X_MARGIN, yCoOrd,
            ViewConsts.FILE_SYSTEM_VIEW_WIDTH, height, true);

        g.setColor(new Color(0x00, 0x00, 0x00));
        g.draw3DRect(ViewConsts.FILE_SYSTEM_VIEW_X_MARGIN, yCoOrd,
            ViewConsts.FILE_SYSTEM_VIEW_WIDTH, height, true);

        int xfileSystemName = (ViewConsts.FILE_SYSTEM_VIEW_X_MARGIN + ViewConsts.FILE_SYSTEM_VIEW_WIDTH) / 3;
        int yfileSystemName = ((2 * yCoOrd) + height) / 2;

        g.setColor(new Color(0xCC, 0xCC, 0xCC));
        allocationSpecificLabels(g, files, fileUnit, xfileSystemName,
            yfileSystemName);

        g.setColor(new Color(0x00, 0x00, 0x00));
        g.drawString(
            String.valueOf(fileUnit.getIndex() * fileSystem.getFileUnitSize()),
            ViewConsts.FILE_SYSTEM_TEXT_LEFT_X_MARGIN, yCoOrd
                + ViewConsts.VERTICAL_TEXT_ADJUSTMENTS);
        
        if (0 != prevYCoOrd) {
          widthOfLine += 10;
          int xCoOrd = ViewConsts.FILE_SYSTEM_VIEW_X_MARGIN + ViewConsts.FILE_SYSTEM_VIEW_WIDTH;
          int lineYCoOrd = yCoOrd + (height / 3);
          int prevLineYCoOrd = prevYCoOrd + ((2 * height) / 3);       
          
          g.drawLine(xCoOrd, prevLineYCoOrd, xCoOrd + widthOfLine, prevLineYCoOrd);
          g.drawLine(xCoOrd + widthOfLine, lineYCoOrd, xCoOrd + widthOfLine, prevLineYCoOrd);
          g.drawLine(xCoOrd, lineYCoOrd, xCoOrd + widthOfLine, lineYCoOrd);          
                    
          g.drawLine(xCoOrd, lineYCoOrd, xCoOrd + 5, lineYCoOrd + (height / 3));
          g.drawLine(xCoOrd, lineYCoOrd, xCoOrd + 5, lineYCoOrd - (height / 3));
        }
        prevYCoOrd = yCoOrd;
      }
      clr += 0x30;
      clr %= 0xFF;
    }
  }
}
