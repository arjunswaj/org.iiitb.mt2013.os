package org.iiitb.model.bean.segmentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.iiitb.controller.util.SegmentationGrapher;
import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;

public class SegmentedMemoryWrapper
{
	long memorySize;
	private Memory<MemorySegment> memory;
	FreeList fl;
	List<FreeList> flList;
	int processCount;
	static int pid;

	public SegmentedMemoryWrapper(long size)
	{
		memorySize = (long) Math.pow(2, size);
		fl = new FreeList(memorySize);
		flList = new ArrayList<FreeList>();
		flList.add(fl);
		setMemory(new Memory<MemorySegment>(memorySize));
		processCount = 0;
		pid = 0;
		clearDatabase();
	}

	public int loadProcess(int size)
	{
		long[] allocationAddress = new long[5];
		long[] segmentArray = segmentify(size);
		allocationAddress = firstFitAllocationPolicy(segmentArray);

		processCount++;

		addprocess(); // Making an entry of the process into Database

		long processSize = 0;
		for (int i = 0; i < 5; i++)
		{
			MemorySegment memorySegment = new MemorySegment(2, "Segment", true,
					10, ResourceType.MEMORY, allocationAddress[i],
					segmentArray[i + 1], i + 1, ConstantsManual.SEGMENT_NAME[i]
							+ " Segment");
			// System.out.println("In Main:" + segmentArray[i]);
			addsegment(allocationAddress, segmentArray, i + 1);
			try
			{
				getMemory().add(memorySegment);
			}
			catch (InvalidMemoryUnitException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			processSize += segmentArray[i + 1];
		}
		// FreeList updateOp = null;
		long avail = fl.updateAvailableMemory(flList, processSize, 0);
		return pid;
	}

	public HashMap<Integer, SegmentTableEntry> getSegmentTable(int pid)
	{
		HashMap<Integer, SegmentTableEntry> segmentTable = new HashMap<Integer, SegmentTableEntry>();
		Connection conn = MySqlConnection.getConnection();
		String myQuery;
		PreparedStatement query;
		ResultSet result;
		myQuery = "SELECT * FROM SegmentTable WHERE P_ID =  " + pid;
		try
		{
			query = conn.prepareStatement(myQuery);
			result = query.executeQuery();

			while (result.next())
				segmentTable.put(
						result.getInt(2),
						new SegmentTableEntry(pid, result.getInt(2), result
								.getString(3), result.getLong(4), result
								.getLong(5)));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return segmentTable;
	}

	private long[] segmentify(int size)
	{
		CreateAProcess cp = new CreateAProcess();
		String code = new String("");
		;
		for (int i = 0; i < size; i++)
			code += " ";
		cp.performCalculations(code);
		return cp.segmentArray;
	}

	private long[] firstFitAllocationPolicy(long[] segmentArray)
	{
		// for (int i = 0; i < memorySize; i++) {

		// }
		long[] allocationAddress = new long[5];
		try
		{
			allocationAddress = fl.returnSegmentAddresses(segmentArray, flList,
					getMemory());
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidMemoryUnitException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allocationAddress;
	}

	private void addprocess()
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
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	private void addsegment(long[] baseAddr, long[] size, int i)
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
			System.out.println(strQuery);
			PreparedStatement query = conn.prepareStatement(strQuery);
			// query.setBigDecimal(4, baseAddr[i]);
			// query.setInt(5, size[i+1]);
			query.executeUpdate();

			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Error Occured:" + e);
		}
	}

	public void clearDatabase()
	{
		Connection conn;
		try
		{
			conn = MySqlConnection.getConnection();

			String strQuery = "delete from Process where P_name like '__'";
			PreparedStatement query = conn.prepareStatement(strQuery);
			query.executeUpdate();

			strQuery = "delete from SegmentTable where Segment_name like '%%';";
			query = conn.prepareStatement(strQuery);
			query.executeUpdate();

			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Error Occured:" + e);
		}
	}

	public static void main(String s[])
	{
		SegmentedMemoryWrapper smw = new SegmentedMemoryWrapper(6);
		int pid = smw.loadProcess(20);
		smw.loadProcess(10);
		HashMap<Integer, SegmentTableEntry> st = smw.getSegmentTable(pid);
		for (SegmentTableEntry e : st.values())
			System.out.println(e.getPid() + " " + e.getSid() + " "
					+ e.getbAddress() + " " + e.getSize());

		SegmentationGrapher segmentationGrapherUsingMemory = new SegmentationGrapher(
				1, "Segment", true, 10, ResourceType.MEMORY);
		segmentationGrapherUsingMemory.plotGraph(smw.getMemory());
		smw.clearDatabase();
	}

	/**
	 * @return the memory
	 */
	public Memory<MemorySegment> getMemory()
	{
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void setMemory(Memory<MemorySegment> memory)
	{
		this.memory = memory;
	}
	
	public long[] getSegmentSizes(long logicalAddressSpace)
	{
		long segmentArray[] = new long[5]; 
		long CONSTANTS_SIZE = logicalAddressSpace/10;
		logicalAddressSpace -= CONSTANTS_SIZE;
		segmentArray[0]=CONSTANTS_SIZE;
		
		long GLOBALS_SIZE = logicalAddressSpace/8;
		logicalAddressSpace -= GLOBALS_SIZE;
		segmentArray[1]=GLOBALS_SIZE;
		
		long CODE_SIZE = logicalAddressSpace/6;
		logicalAddressSpace -= CODE_SIZE;
		segmentArray[2]=CODE_SIZE;
		
		long HEAP_SIZE = logicalAddressSpace/3;
		segmentArray[3]=HEAP_SIZE;
		
		long STACK_SIZE = logicalAddressSpace - HEAP_SIZE;
		segmentArray[4] = STACK_SIZE;
		
		return segmentArray;
	}
}