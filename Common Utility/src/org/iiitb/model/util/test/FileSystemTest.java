package org.iiitb.model.util.test;

import org.iiitb.controller.util.FileSystemGrapher;
import org.iiitb.model.bean.files.FileSystem;
import org.iiitb.model.bean.files.FileUnit;
import org.iiitb.model.bean.files.Files;
import org.iiitb.model.bean.files.IndexedFileUnit;
import org.iiitb.model.consts.FileAllocationType;

/**
 * Tests File system view Implementation
 * 
 * @author arjun
 * 
 */
public class FileSystemTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = new FileSystem(4096, 128, FileAllocationType.INDEXED);

		Files file1 = new Files(1);

		for (int counter = 0; counter < 10; counter += 2) {
			FileUnit fileUnit = new IndexedFileUnit(counter);
			file1.getFileUnits().add(fileUnit);
		}

		Files file2 = new Files(2);

		for (int counter = 5; counter < 20; counter += 5) {
			FileUnit fileUnit = new IndexedFileUnit(counter);
			file2.getFileUnits().add(fileUnit);
		}

		
		Files file3 = new Files(3);

		for (int counter = 20; counter < 32; counter += 4) {
			FileUnit fileUnit = new IndexedFileUnit(counter);
			file3.getFileUnits().add(fileUnit);
		}
		
		fileSystem.getFiles().add(file1);
		fileSystem.getFiles().add(file2);
		fileSystem.getFiles().add(file3);

		FileSystemGrapher grapher = new FileSystemGrapher();
		grapher.plotGraph(fileSystem);
	}

}
