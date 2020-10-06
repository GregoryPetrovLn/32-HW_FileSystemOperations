package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.io.FileUtils.FileSystemWork;
import telran.performance.FSWStandartCopyPerformanceTest;
import telran.performance.FSWStreamPerformanceTest;

class FSTests {
	FileSystemWork fileWork;
	FileSystem fs = FileSystems.getDefault();
	int nRuns = 100;
	
	Path source = fs.getPath("./src/telran/io/FileUtils/FileSystemWork.java").toAbsolutePath().normalize();
	Path newDir1 = fs.getPath("./src/telran/io/FileUtils/placeForCopy1/FileSystemWorkCopyFirstMethod.java").toAbsolutePath().normalize();
	Path newDir2 = fs.getPath("./src/telran/io/FileUtils/placeForCopy2/FileSystemWorkCopySecondMethod.java").toAbsolutePath().normalize();



	@BeforeEach
	void setUp() {
		fileWork = new FileSystemWork();
	}

	@Test
	void testDisplayFalse() {
		Path pathFalse = fs.getPath("/Users/gregory//").toAbsolutePath().normalize();
		Path path = fs.getPath("/Users/gregorypetrov/Documents").toAbsolutePath().normalize();

		int depthFalse = -5;
		int depth = 2;

		try {
			fileWork.displayDirectoryContent(pathFalse, depth);
			fail();
		} catch (Throwable e) {

		}

		try {
			fileWork.displayDirectoryContent(path, depthFalse);
			fail();
		} catch (Throwable e) {

		}

	}

	@Test
	void testDisplayTrue() {
		Path path = fs.getPath("/Users/gregorypetrov/Documents/Учеба/java/32-HW_FileSystemWorkOperations2").toAbsolutePath().normalize();
		int depth = 0;

		try {
			fileWork.displayDirectoryContent(path, depth);
		} catch (Throwable e) {
			fail();
		}
	}

	@Test
	void testFileCFM() {

		try {
			fileWork.fileStandartCopy(source, newDir1);
		} catch (IOException e) {
			fail();
		}
		
		System.out.println("\n\tPerformance Tests\n");
		FSWStandartCopyPerformanceTest perfTest = new FSWStandartCopyPerformanceTest("FileStandartCopy", nRuns, source, newDir1);
		perfTest.run();
	}

	@Test
	void testFileCSM() throws IOException {

		try {
			fileWork.fileByteStreamCopy(source, newDir2);
		} catch (IOException e) {
			fail();
		}
		
		FSWStreamPerformanceTest perfTest = new FSWStreamPerformanceTest("FileByteStreamCopy", nRuns, source, newDir2);
		perfTest.run();
		System.out.println("\n============================\n");
	}
	
	

	

}
