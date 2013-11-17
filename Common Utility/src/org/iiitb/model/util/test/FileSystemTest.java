package org.iiitb.model.util.test;

import org.iiitb.controller.util.FileSystemGrapher;
import org.iiitb.model.bean.files.ContiguousFileUnit;
import org.iiitb.model.bean.files.FileSystem;
import org.iiitb.model.bean.files.FileUnit;
import org.iiitb.model.bean.files.Files;
import org.iiitb.model.bean.files.IndexedFileUnit;
import org.iiitb.model.bean.files.LinkedFileUnit;
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
		doIndexedFileSystemTest();
		//doLinkedFileSystemTest();
		//doContiguousFileSystemTest();
	}

	private static void doIndexedFileSystemTest() throws Exception {
		FileSystem fileSystem = new FileSystem(4096, 128,
				FileAllocationType.INDEXED);

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

	private static void doLinkedFileSystemTest() throws Exception {
		FileSystem fileSystem = new FileSystem(4096, 128,
				FileAllocationType.LINKED);

		Files file1 = new Files(1);

		for (int counter = 0; counter < 10; counter += 2) {
			FileUnit fileUnit = new LinkedFileUnit(counter, (counter + 2) * 128);
			file1.getFileUnits().add(fileUnit);
		}

		Files file2 = new Files(2);

		for (int counter = 5; counter < 20; counter += 5) {
			FileUnit fileUnit = new LinkedFileUnit(counter, (counter + 5) * 128);
			file2.getFileUnits().add(fileUnit);
		}

		Files file3 = new Files(3);

		for (int counter = 20; counter < 32; counter += 4) {
			FileUnit fileUnit = new LinkedFileUnit(counter, (counter + 4) * 128);
			file3.getFileUnits().add(fileUnit);
		}

		fileSystem.getFiles().add(file1);
		fileSystem.getFiles().add(file2);
		fileSystem.getFiles().add(file3);

		FileSystemGrapher grapher = new FileSystemGrapher();
		grapher.plotGraph(fileSystem);

	}

	private static void doContiguousFileSystemTest() throws Exception {
    FileSystem fileSystem = new FileSystem(4096, 128,
        FileAllocationType.CONTIGUOUS);

    Files file1 = new Files(1);

    for (int counter = 0; counter < 10; counter += 1) {
      FileUnit fileUnit = new ContiguousFileUnit(counter);
      file1.getFileUnits().add(fileUnit);
    }

    Files file2 = new Files(2);

    for (int counter = 15; counter < 20; counter += 1) {
      FileUnit fileUnit = new ContiguousFileUnit(counter);
      file2.getFileUnits().add(fileUnit);
    }

    Files file3 = new Files(3);

    for (int counter = 25; counter < 32; counter += 1) {
      FileUnit fileUnit = new ContiguousFileUnit(counter);
      file3.getFileUnits().add(fileUnit);
    }

    fileSystem.getFiles().add(file1);
    fileSystem.getFiles().add(file2);
    fileSystem.getFiles().add(file3);

    FileSystemGrapher grapher = new FileSystemGrapher();
    grapher.plotGraph(fileSystem);

  }
}
