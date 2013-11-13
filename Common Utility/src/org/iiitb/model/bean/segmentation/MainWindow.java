package org.iiitb.model.bean.segmentation;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;
import org.iiitb.view.SegmentView;

public class MainWindow extends JFrame implements ActionListener,
	WindowListener, WindowFocusListener, WindowStateListener
{

    public static int pid = 0;

    JPanel p1 = new JPanel();
    Connection connection;
    JButton CreateProcess;
    JButton AddressForProcess;
    JButton SegmentTable;
    JButton DeleteProcess;
    JLabel Team5;
    JLabel MemoryUsed;
    JLabel MemoryAvailable;
    JLabel ProcessCount;
    JLabel x;
    JLabel y;
    JLabel z;
    JLabel status;
    JLabel Segment_Table;
    JLabel Process_ID;
    JLabel Segment_Name;
    JLabel Segment_ID;
    JLabel BA;
    JLabel length;
    JTextField jt1;
    JTextField jt2;
    JTextField jt3;
    JTextField jt4;
    JTextField jt5;
    JTextField jt6;
    JTextField jt7;
    JTextField jt8;
    JTextField jt9;
    JTextField jt10;
    JTextField jt11;
    JTextField jt12;
    JTextField jt13;
    JTextField jt14;
    JTextField jt15;
    JTextField jt16;
    JTextField jt17;
    JTextField jt18;
    JTextField jt19;
    JTextField jt20;
    JTextField jt21;
    JTextField jt22;
    JTextField jt23;
    JTextField jt24;
    JTextField jt25;

    int generateProcRandom = 0;

    int processCount;

    private long memorySize;

    protected Memory<MemorySegment> memory;
    FreeList fl;
    List<FreeList> flList;

    long[] allocationAddress = new long[5];

    public MainWindow()
    {
	JPanel pane1 = new JPanel();
	pane1.setBorder(BorderFactory.createEtchedBorder());
	pane1.setBackground(Color.GRAY);
	pane1.setLayout(new GridLayout(1, 4));
	getContentPane().add(pane1);
	pane1.setBounds(0, 661, 1300, 28);
	pane1.setOpaque(false);
	status = new JLabel("");
	status.setBounds(20, 575, 1280, 768);
	pane1.add(status);
    }

    public MainWindow(long size)
    {

	setLocation(0, 0);
	setSize(1300, 760);
	setTitle("Main Screen");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(null);
	JPanel p = new JPanel();
	getContentPane().add(p);
	p.setLayout(new GridLayout(6, 4));

	p.setBounds(280, 400, 390, 180);

	JPanel pane = new JPanel();
	pane.setBorder(BorderFactory.createEtchedBorder());
	pane.setBackground(Color.GRAY);
	pane.setLayout(new GridLayout(1, 8));
	getContentPane().add(pane);
	pane.setBounds(0, 688, 1300, 28);
	pane.setOpaque(false);

	JPanel pane1 = new JPanel();
	pane1.setBorder(BorderFactory.createEtchedBorder());
	pane1.setBackground(Color.GRAY);
	pane1.setLayout(new GridLayout(1, 4));
	getContentPane().add(pane1);
	pane1.setBounds(0, 661, 1300, 28);
	pane1.setOpaque(false);

	JPanel pane2 = new JPanel();
	pane2.setBorder(BorderFactory.createEtchedBorder());
	pane2.setBackground(Color.GRAY);
	pane2.setLayout(new GridLayout(1, 11));
	getContentPane().add(pane2);
	pane2.setBounds(380, 351, 115, 35);
	pane2.setOpaque(false);

	/* Drawing memory view on Window */
	memorySize = (long) Math.pow(2, size);
	System.out.println(memorySize);
	fl = new FreeList(memorySize);
	flList = new ArrayList<FreeList>();
	flList.add(fl);
	memory = new Memory<MemorySegment>(memorySize);

	SegmentView segmentView = new SegmentView(memory);
	getContentPane().add(segmentView).setBounds(740, 0, 600, 700);

	status = new JLabel(
		"This status bar will guide you throughout the simulation");
	status.setBounds(20, 575, 1280, 768);

	Team5 = new JLabel("Team - 5");
	Team5.setBounds(20, 675, 60, 20);

	MemoryUsed = new JLabel("Memory Used:");
	MemoryUsed.setBounds(110, 675, 110, 20);

	x = new JLabel("X MB");
	x.setBounds(220, 675, 50, 20);
	x.setText("0 Bytes");

	MemoryAvailable = new JLabel("Memory Available:");
	MemoryAvailable.setBounds(300, 675, 140, 20);

	y = new JLabel("Y MB");
	y.setBounds(470, 675, 50, 20);
	y.setText(String.valueOf(memorySize));

	ProcessCount = new JLabel("Process Count:");
	ProcessCount.setBounds(520, 675, 110, 20);
	processCount = 0;

	z = new JLabel("Z");
	z.setBounds(630, 675, 20, 20);
	z.setText(Integer.toString(processCount));

	CreateProcess = new JButton(
		"<html><body style='width: 10% px'> Create a Process </html>");
	CreateProcess.setBounds(5, 30, 175, 25);

	AddressForProcess = new JButton(
		"<html><body style='width: 10% px'> Generate Address For a Process </html>");
	AddressForProcess.setBounds(5, 145, 175, 50);

	SegmentTable = new JButton(
		"<html><body style='width: 10% px'> Segment Table for a Process </html>");
	SegmentTable.setBounds(5, 90, 175, 50);

	DeleteProcess = new JButton(
		"<html><body style='width: 10% px'> Delete a Process </html>");
	DeleteProcess.setBounds(5, 60, 175, 25);

	Segment_Table = new JLabel("Segment Table");
	Segment_Table.setBounds(400, 353, 150, 25);

	Process_ID = new JLabel(
		"<html><body style='width: 10% px'>Process ID</html>");
	Segment_Name = new JLabel(
		"<html><body style='width: 10% px'>Segment Name</html>");
	Segment_ID = new JLabel(
		"<html><body style='width: 10% px'>Segment ID</html>");
	BA = new JLabel("<html><body style='width: 10% px'>Base Address</html>");
	length = new JLabel("Length");

	jt1 = new JTextField();
	jt2 = new JTextField();
	jt3 = new JTextField();
	jt4 = new JTextField();
	jt5 = new JTextField();
	jt6 = new JTextField();
	jt7 = new JTextField();
	jt8 = new JTextField();
	jt9 = new JTextField();
	jt10 = new JTextField();
	jt11 = new JTextField();
	jt12 = new JTextField();
	jt13 = new JTextField();
	jt14 = new JTextField();
	jt15 = new JTextField();
	jt16 = new JTextField();
	jt17 = new JTextField();
	jt18 = new JTextField();
	jt19 = new JTextField();
	jt20 = new JTextField();
	jt21 = new JTextField();
	jt22 = new JTextField();
	jt23 = new JTextField();
	jt24 = new JTextField();
	jt25 = new JTextField();

	pane.add(Team5);
	pane.add(MemoryUsed);
	pane.add(x);
	pane.add(MemoryAvailable);
	pane.add(y);
	pane.add(ProcessCount);
	pane.add(z);
	pane1.add(status);
	add(CreateProcess);
	add(AddressForProcess);
	add(SegmentTable);
	add(DeleteProcess);

	pane2.add(Segment_Table);

	p.add(Process_ID);
	p.add(Segment_ID);
	p.add(Segment_Name);
	p.add(BA);
	p.add(length);
	p.add(jt1);
	p.add(jt2);
	p.add(jt3);
	p.add(jt4);
	p.add(jt5);
	p.add(jt6);
	p.add(jt7);
	p.add(jt8);
	p.add(jt9);
	p.add(jt10);
	p.add(jt11);
	p.add(jt12);
	p.add(jt13);
	p.add(jt14);
	p.add(jt15);
	p.add(jt16);
	p.add(jt17);
	p.add(jt18);
	p.add(jt19);
	p.add(jt20);
	p.add(jt21);
	p.add(jt22);
	p.add(jt23);
	p.add(jt24);
	p.add(jt25);

	TextEmpty();

	addWindowListener(this);
	// addWindowFocusListener(this);
	// addWindowStateListener(this);

	CreateProcess.addActionListener(this);
	AddressForProcess.addActionListener(this);
	SegmentTable.addActionListener(this);
	DeleteProcess.addActionListener(this);

	setVisible(true);
	p.setVisible(true);

    }

    public void windowClosing(WindowEvent e)
    {

	Connection con;
	try
	{
	    con = MySqlConnection.getConnection();
	    String myQuery;
	    PreparedStatement query;

	    myQuery = "delete from Process";
	    query = con.prepareStatement(myQuery);
	    query.executeUpdate();
	    con.close();
	} catch (Exception ex)
	{
	    ex.printStackTrace();
	}
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	// TODO Auto-generated method stub
	Object o = e.getSource();

	if (o == CreateProcess)
	{

	    CreateAProcess cp = new CreateAProcess();
	    long[] segmentArray = cp.ProcessInputCode();
	    if (segmentArray[0] == 1)
	    { // User did input something

		allocationAddress = firstFitAllocationPolicy(segmentArray);
		int flag = 1; // For checking whether there was enough memory.
			      // Check this for loop for understanding.

		if (allocationAddress[1] == -2)
		{ // As all are set to -2

		    JOptionPane.showMessageDialog(null, "NOT ENOUGH MEMORY!!");
		    flag = 0;
		}

		if (flag == 1)
		{
		    processCount++;

		    addprocess(); // Making an entry of the process into
				  // Database

		    z.setText(Integer.toString(processCount));
		    long processSize = 0;
		    for (int i = 0; i < 5; i++)
		    {
			MemorySegment memorySegment = new MemorySegment(2,
				"Segment", true, 10, ResourceType.MEMORY,
				allocationAddress[i], segmentArray[i + 1],
				i + 1, ConstantsManual.SEGMENT_NAME[i]
					+ " Segment");
			// System.out.println("In Main:" + segmentArray[i]);
			addsegment(allocationAddress, segmentArray, i + 1);
			try
			{
			    memory.add(memorySegment);
			} catch (InvalidMemoryUnitException e1)
			{
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
			processSize += segmentArray[i + 1];
		    }
		    status.setForeground(Color.BLACK);
		    status.setText("Size of the Process " + pid + " is : "
			    + processSize + ". Size of the Segments are: "
			    + segmentArray[1] + " Bytes CONSTANTS, "
			    + segmentArray[2] + "B GLOBALS, " + segmentArray[3]
			    + "B CODE, " + segmentArray[4] + "B HEAP, "
			    + segmentArray[5] + "B STACK. See, Segment Table.");
		    if (ConstantsManual.defragmentationFlag == 1)
		    {
			String tempo = status.getText();
			status.setText("<html><font color=red> Encountered Defragmentation </font> <font color=black> | "
				+ tempo + "</font></html>");
		    }
		    SegmentView segmentView = new SegmentView(memory);
		    getContentPane().add(segmentView).setBounds(740, 0, 600,
			    700);
		    // FreeList updateOp = null;
		    long avail = fl.updateAvailableMemory(flList, processSize,
			    0);
		    y.setText(String.valueOf(avail));
		    x.setText(String.valueOf(memorySize - avail));
		}
	    }
	    // firstFitAllocationPolicy();
	}

	if (o == SegmentTable)
	{
	    try
	    {
		ProcessDisplay(0);
	    } catch (SQLException e1)
	    {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}

	if (o == DeleteProcess)
	{
	    // Make segments free

	    try
	    {
		ProcessDisplay(1);
	    } catch (SQLException e1)
	    {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    // Update FreeList, add nodes to it.
	    // Delete the entry from Process relation.
	    // Delete the entries from its DB SegmentTable relation
	}

	if (o == AddressForProcess)
	{

	    try
	    {
		System.out.println("Generate Process!!!!");
		ProcessDisplay(2);
	    } catch (SQLException e1)
	    {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}

    }

    protected long[] firstFitAllocationPolicy(long[] segmentArray)
    {
	// for (int i = 0; i < memorySize; i++) {

	// }
	try
	{
	    allocationAddress = fl.returnSegmentAddresses(segmentArray, flList,
		    memory);
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidMemoryUnitException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return allocationAddress;
    }

    public void addprocess()
    {
	Connection conn;
	try
	{
	    pid++;
	    String P_name = "P" + pid;
	    conn = MySqlConnection.getConnection();
	    String strQuery = "INSERT INTO Process(P_ID, P_name) " + "VALUES "
		    + "(?, ?)";
	    PreparedStatement query = conn.prepareStatement(strQuery);
	    query.setInt(1, pid);
	    query.setString(2, P_name);

	    query.executeUpdate();
	    conn.close();
	} catch (Exception ex)
	{
	    ex.printStackTrace();
	}

    }

    public void addsegment(long[] baseAddr, long[] size, int i)
    {

	Connection conn;
	try
	{
	    conn = MySqlConnection.getConnection();

	    String strQuery = "INSERT INTO SegmentTable(P_ID, Segment_ID, Segment_name, BaseAddress, Length) "
		    + "VALUES "
		    + "('"
		    + pid
		    + "',  '"
		    + i
		    + "' , '"
		    + ConstantsManual.SEGMENT_NAME[i - 1]
		    + "', '"
		    + baseAddr[i - 1] + "' ," + size[i] + ")";
	    PreparedStatement query = conn.prepareStatement(strQuery);
	    // query.setBigDecimal(4, baseAddr[i]);
	    // query.setInt(5, size[i+1]);
	    query.executeUpdate();

	    conn.close();
	} catch (Exception e)
	{
	    System.out.println("Error Occured:" + e);
	}
    }

    @Override
    public void windowStateChanged(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowGainedFocus(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowLostFocus(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0)
    {
	// TODO Auto-generated method stub

    }

    public void ProcessDisplay(final int flag) throws SQLException
    {
	p1.removeAll();
	p1.updateUI();
	p1.setLayout(new GridLayout(12, 1));
	p1.setBounds(200, 25, 80, 250);
	getContentPane().add(p1);

	connection = MySqlConnection.getConnection();
	String myQuery;
	PreparedStatement query;
	ResultSet r;

	myQuery = "SELECT P_ID FROM Process";
	query = connection.prepareStatement(myQuery);
	r = query.executeQuery();
	JButton b = null;

	for (int i = 1; i <= processCount || r.next() != false; i++)
	{

	    r.next();
	    b = new JButton(String.valueOf(r.getInt("P_ID")));
	    b.setText("P" + r.getInt("P_ID"));

	    // Segment table view
	    b.addActionListener(new java.awt.event.ActionListener()
	    {
		public void actionPerformed(java.awt.event.ActionEvent e)
		{

		    Connection con;
		    try
		    {
			con = MySqlConnection.getConnection();
			String myQuery;
			PreparedStatement query;
			ResultSet result;

			String btnValue = ((JButton) e.getSource()).getText()
				.toString();
			btnValue = btnValue.substring(1);
			myQuery = "SELECT * FROM SegmentTable WHERE P_ID =  "
				+ btnValue;
			query = con.prepareStatement(myQuery);
			result = query.executeQuery();

			if (flag == 0)
			{

			    result.next();
			    jt1.setText(result.getString(1));
			    jt2.setText(result.getString(2));
			    jt3.setText(result.getString(3));
			    jt4.setText(result.getString(4));
			    jt5.setText(result.getString(5));

			    result.next();

			    jt6.setText(result.getString(1));
			    jt7.setText(result.getString(2));
			    jt8.setText(result.getString(3));
			    jt9.setText(result.getString(4));
			    jt10.setText(result.getString(5));

			    result.next();

			    jt11.setText(result.getString(1));
			    jt12.setText(result.getString(2));
			    jt13.setText(result.getString(3));
			    jt14.setText(result.getString(4));
			    jt15.setText(result.getString(5));

			    result.next();

			    jt16.setText(result.getString(1));
			    jt17.setText(result.getString(2));
			    jt18.setText(result.getString(3));
			    jt19.setText(result.getString(4));
			    jt20.setText(result.getString(5));

			    result.next();

			    jt21.setText(result.getString(1));
			    jt22.setText(result.getString(2));
			    jt23.setText(result.getString(3));
			    jt24.setText(result.getString(4));
			    jt25.setText(result.getString(5));

			}

			if (flag == 1)
			{
			    status.setForeground(Color.BLACK);
			    status.setText("Deleting the process and making necessary changes");
			    int k = 0;
			    processCount--;
			    z.setText(Integer.toString(processCount));
			    HandyFunctions hf = new HandyFunctions();
			    while (k < 5)
			    {
				result.next();
				k++;
				long address = Long.valueOf(result.getString(4));
				memory.remove(address);

				// Updating labels in status bar
				long avail = fl.updateAvailableMemory(flList,
					Long.valueOf(result.getString(5)), 1);
				y.setText(String.valueOf(avail));
				x.setText(String.valueOf(memorySize - avail));

				// Adding a node in FreeList
				long endAdd = Long.valueOf(result.getString(4))
					+ Long.valueOf(result.getString(5));
				fl = new FreeList(Long.valueOf(result
					.getString(4)), endAdd, Long
					.valueOf(result.getString(5)), avail);
				flList.add(fl);

				// Query to delete segment tables
				String mQuery;
				PreparedStatement preparedSt;
				mQuery = "delete from Process where P_ID="
					+ btnValue;
				preparedSt = con.prepareStatement(mQuery);
				preparedSt.executeUpdate();

				// System.out.println("Yes, deleted!!");
				// Redrawing the Memory View
				SegmentView segmentView = new SegmentView(
					memory);
				getContentPane().add(segmentView).setBounds(
					740, 0, 600, 700);
			    }
			    // System.out.println("Before");
			    // for (int i=0; i<flList.size();i++)
			    // {
			    // FreeList item = flList.get(i);
			    // System.out.print(item.startingAdd + " : " +
			    // item.size + "|");
			    // }
			    hf.SortList(flList);
			    // flList.toString();
			    hf.MergeList(flList);
			    // flList.toString();
			    // System.out.println("After");
			    // for (int i=0; i<flList.size();i++)
			    // {
			    // FreeList item = flList.get(i);
			    // System.out.print(item.startingAdd + " : " +
			    // item.size + "|");
			    // }
			    TextEmpty();
			    ProcessDisplay(1);
			    // This will regenrate all the process buttons,
			    // needed to do this as one process is being deleted
			}

			if (flag == 2)
			{
			    // Generate an address
			    ResultSet resultSet = result;

			    // uselessRepetation
			    result.next();
			    jt1.setText(result.getString(1));
			    jt2.setText(result.getString(2));
			    jt3.setText(result.getString(3));
			    jt4.setText(result.getString(4));
			    jt5.setText(result.getString(5));

			    result.next();

			    jt6.setText(result.getString(1));
			    jt7.setText(result.getString(2));
			    jt8.setText(result.getString(3));
			    jt9.setText(result.getString(4));
			    jt10.setText(result.getString(5));

			    result.next();

			    jt11.setText(result.getString(1));
			    jt12.setText(result.getString(2));
			    jt13.setText(result.getString(3));
			    jt14.setText(result.getString(4));
			    jt15.setText(result.getString(5));

			    result.next();

			    jt16.setText(result.getString(1));
			    jt17.setText(result.getString(2));
			    jt18.setText(result.getString(3));
			    jt19.setText(result.getString(4));
			    jt20.setText(result.getString(5));

			    result.next();

			    jt21.setText(result.getString(1));
			    jt22.setText(result.getString(2));
			    jt23.setText(result.getString(3));
			    jt24.setText(result.getString(4));
			    jt25.setText(result.getString(5));

			    resultSet.beforeFirst();
			    Random r = new Random();
			    int temp = r.nextInt(5);
			    temp = temp + 1; // As the random is inclusive of 0
					     // and exclusive of n
			    int m = 0;
			    while (m < temp)
			    {
				result.next();
				m++;
			    }
			    long showAddr, showLen, cumAdd, localEnd;
			    showAddr = Long.valueOf(resultSet.getString(4));
			    showLen = Long.valueOf(resultSet.getString(5));
			    cumAdd = showAddr + showLen;
			    localEnd = cumAdd;
			    if (generateProcRandom == 0) // TrueAddress
			    {
				cumAdd -= 4;
				status.setForeground(Color.BLACK);
				status.setText("The logical address generated was: "
					+ temp
					+ ""
					+ cumAdd
					+ " which means segment "
					+ temp
					+ " and byte at location: "
					+ cumAdd
					+ " which, from segment table is valid address");
				generateProcRandom = 1;
			    }
			    else
			    // ErrorneousAddress
			    {
				cumAdd += 5;
				status.setForeground(Color.RED);
				status.setText("Logical address generated was: "
					+ temp
					+ ""
					+ cumAdd
					+ " which means segment "
					+ temp
					+ " and byte at location: "
					+ cumAdd
					+ ", which is Invalid. As the address generated is out of Segment, this is SEGMENTATION FAULT");
				generateProcRandom = 0;
			    }
			}
			con.close();
		    } catch (Exception ex)
		    {
			ex.printStackTrace();
		    }
		}
	    });
	    p1.add(b);
	}
	// connection.close();

	p1.revalidate();
	p1.setVisible(true);

    }

    public void TextEmpty()
    {

	jt1.setText("");
	jt2.setText("");
	jt3.setText("");
	jt4.setText("");
	jt5.setText("");
	jt6.setText("");
	jt7.setText("");
	jt8.setText("");
	jt9.setText("");
	jt10.setText("");
	jt11.setText("");
	jt12.setText("");
	jt13.setText("");
	jt14.setText("");
	jt15.setText("");
	jt16.setText("");
	jt17.setText("");
	jt18.setText("");
	jt19.setText("");
	jt20.setText("");
	jt21.setText("");
	jt22.setText("");
	jt23.setText("");
	jt24.setText("");
	jt25.setText("");
    }
}
