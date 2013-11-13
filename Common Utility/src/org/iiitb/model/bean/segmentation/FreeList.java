package org.iiitb.model.bean.segmentation;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import org.iiitb.model.bean.InvalidMemoryUnitException;
import org.iiitb.model.bean.Memory;
import org.iiitb.model.bean.MemorySegment;
import org.iiitb.model.consts.ResourceType;

public class FreeList implements Comparable<FreeList> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FreeList [startingAdd=");
		builder.append(startingAdd);
		builder.append(", endingAdd=");
		builder.append(endingAdd);
		builder.append(", memorySize=");
		builder.append(memorySize);
		builder.append(", size=");
		builder.append(size);
		builder.append(", availableMemorySize=");
		builder.append(availableMemorySize);
		builder.append("]");
		return builder.toString();
	}

	long startingAdd;
	long endingAdd;
	long memorySize;
	long size;
	long availableMemorySize;

	public FreeList(long memorySize) {
		startingAdd = 0;
		endingAdd = memorySize;
		size = memorySize;
		// this.memorySize = memorySize;
		this.availableMemorySize = memorySize;
	}

	public FreeList(long startAdd, long endAdd, long size, long avail) {
		this.startingAdd = startAdd;
		this.endingAdd = endAdd;
		this.size = size;
		this.availableMemorySize = avail;
	}

	public long getAvailableMemory() {
		return this.availableMemorySize;
	}

	public long getStartingAddress() {
		return this.startingAdd;
	}

	public long getEndingAddress() {
		return this.endingAdd;
	}

	public long getSize() {
		return this.size;
	}

	public long updateAvailableMemory(List<FreeList> flList, long processSize,
			int flag) {
		// 0 for insertion, 1 for deletion
		long retVal = 0;
		if (flag == 0) {
			for (int k = 0; k < flList.size(); k++) {
				FreeList item = flList.get(k);
				item.availableMemorySize = item.getAvailableMemory()
						- processSize;
				retVal = item.availableMemorySize;
			}
		}
		if (flag == 1) {
			for (int k = 0; k < flList.size(); k++) {
				FreeList item = flList.get(k);
				item.availableMemorySize = item.getAvailableMemory()
						+ processSize;
				retVal = item.availableMemorySize;
			}
		}
		return retVal;
	}

	public long[] returnSegmentAddresses(long[] segmentArray,
			List<FreeList> flList, Memory<MemorySegment> memory) throws SQLException, InvalidMemoryUnitException {

		long processSize = 0;
		long[] allocatedAddress = new long[5];

		for (int i = 0; i <= 4; i++) {
			processSize += segmentArray[i + 1];
		}

		long availMem = 0;
		for (FreeList item : flList) {
			availMem = item.getAvailableMemory();
		}

		if (processSize > availMem) {
			Arrays.fill(allocatedAddress, -2);
			return allocatedAddress;
		} else {
			long startAddress, size;// , memoryBetween;
			// long prevAddress = 0; // End Address
			Arrays.fill(allocatedAddress, -1);
			int count = 0, flag = 0;
			System.out.println("Size of list is : " + flList.size());
			for (int k = 0; k < flList.size(); k++) {
				FreeList item = flList.get(k);
				int breakFlag = 0;
				// System.out.println("Starting Address: " + startAddress);
				for (int i = 0; i < 5; i++) {
					startAddress = item.getStartingAddress();
					size = item.getSize();

					if (allocatedAddress[i] == -1) {
						if (segmentArray[i + 1] <= size) {

							System.out.println("FreeList node: "+k+ " Start Add: "+ item.getStartingAddress() + " Size of Node and Segment: " + item.getSize() + " and "+ segmentArray[i+1] );
							allocatedAddress[i] = startAddress;
							System.out.println("Allocation for Seg" +i+ ": " 
									+ allocatedAddress[i]);

							// If the segment occupies entire freelist node
							// memory
							if (segmentArray[i + 1] == size) {
								flList.remove(item);
							}

							// If allocation happens, then...
							else {
								item.startingAdd = startAddress
										+ segmentArray[i + 1];
								item.size = item.size - segmentArray[i + 1];
								
								//  item.availableMemorySize =
								 // item.availableMemorySize - segmentArray[i +
								 // 1];
								 
								count++;
								if (count == 5)
									{ breakFlag=1;
									break;}
								//System.out.println(item.size);
							}
						}
					}
				//	System.out.println("Value of count :" + count);
				}
				if (breakFlag==1)
				{break;}
			}
			for (int i = 0; i < 5; i++) {
				if (allocatedAddress[i] == -1) {
					// Defragment the Memory
					
//					MainWindow m = new MainWindow();
//					m.status.setForeground(Color.RED);
//					m.status.setText("Defragmenting the memory");
//					//System.out.println("Defragmentation called");
					
					ConstantsManual.defragmentationFlag = 1;
					
					FreeList node1 = null;
					FreeList node2 = null;
					int counter = 0;
					long cumSize = 0;
					for (int k = 0; k < flList.size(); k++) {
						node1 = flList.get(k);
						if (cumSize >= segmentArray[i+1])
						{
							break;
						}
						else
						{
							cumSize = cumSize + node1.size;
							counter++;
						}
					}
					System.out.println("Counter is : " + counter );
					Connection con;
					con = MySqlConnection.getConnection();
					String myQuery;
					PreparedStatement query;
					ResultSet result;
					HandyFunctions hf = new HandyFunctions();
					//hf.SortList(flList);
					for (int k=0; k<(counter-1); k++)
					{
						node1 = flList.get(0);
						node2 = flList.get(1);
						long node2Size = node2.size;
	
						myQuery = "SELECT * FROM SegmentTable WHERE (BaseAddress >= " + node1.endingAdd + " AND BaseAddress <= " + node2.startingAdd + ") order by BaseAddress DESC";
						query = con.prepareStatement(myQuery);
						result = query.executeQuery();
						//result.next();
						long endForNext = node2.endingAdd;
						long endAddr, startAddr, sizer, tempStart, tempEnd, tempSize;
						
						while (result.next())
						{
							startAddr = Long.valueOf(result.getString(4));
							sizer = Long.valueOf(result.getString(5));
							endAddr = startAddr + sizer;
							tempEnd = endForNext;
							tempStart = tempEnd - sizer;							
							endForNext = tempStart;
							//Delete from Memory
							memory.remove(startAddr);
							
							//Insert into Memory
							MemorySegment memorySegment = new MemorySegment(2,
									"Segment", true, 10, ResourceType.MEMORY,
									tempStart, sizer,
									result.getInt(2), result.getString(3)
											+ " Segment");
							
							//Reflect in Database
							Connection conn;
								conn = MySqlConnection.getConnection();
							
								String strQuery = "Update SegmentTable SET BaseAddress = "+tempStart+" where BaseAddress = "+startAddr;
								PreparedStatement Pquery = conn.prepareStatement(strQuery);
								Pquery.executeUpdate();
							conn.close();
							
							memory.add(memorySegment);
						}
						node2.startingAdd = node1.endingAdd;
						node2.endingAdd = node1.endingAdd + node2Size; 
						node2.size = node2Size;
						hf.MergeList(flList);
					}
					
					allocatedAddress[i] = node1.startingAdd;
					System.out.println("Allocation for Segment:" + i + " "
							+ allocatedAddress[i]);

					// If the segment occupies entire freelist node
					// memory
					if (segmentArray[i + 1] == node1.size) {
						flList.remove(node1);
					}

					// If allocation happens, then...
					else {
						node1.startingAdd = node1.startingAdd
								+ segmentArray[i + 1];
						node1.size = node1.size - segmentArray[i + 1];
						/*
						 * item.availableMemorySize =
						 * item.availableMemorySize - segmentArray[i +
						 * 1];
						 */
					}
					con.close();
				}
			}
			//System.out.println("While allocating: " + allocatedAddress[1]);

			return allocatedAddress;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (startingAdd ^ (startingAdd >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FreeList other = (FreeList) obj;
		if (startingAdd != other.startingAdd)
			return false;
		return true;
	}

	@Override
	public int compareTo(FreeList o) {
		if (!this.equals(o)) {
			return (this.startingAdd - o.startingAdd > 0 ? 1 : -1);
		} else
			return 0;

	}

}

// For reference:
// Making iterator parse all occupied memory
/*
 * Iterable<MemorySegment> aMemory = memory.getAll(); Iterator itr =
 * aMemory.iterator(); while (itr.hasNext()) { MemorySegment element =
 * (MemorySegment) itr.next();
 * 
 * System.out.println(element.getAddress());
 * System.out.println(element.getSize());
 */

/*
 * for (int i = 0; i < 5; i++) { System.out.println("Segmented Memory: " +
 * segmentArray[i + 1]);
 * 
 * }
 */
