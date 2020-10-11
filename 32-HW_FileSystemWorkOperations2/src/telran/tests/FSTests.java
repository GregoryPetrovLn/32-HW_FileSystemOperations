package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.io.FileUtils.FileSystemWork;


class FSTests {
	FileSystemWork fileWork;
	FileSystem fs = FileSystems.getDefault();
	
	Path source = fs.getPath("./src/telran/io/FileUtils/FileSystemWork.java").toAbsolutePath().normalize();
	Path newDir1 = fs.getPath("/Users/gregorypetrov/Documents/FileSystemWorkCopyFirstMethod.java").toAbsolutePath().normalize();
	Path newDir2 = fs.getPath("/Users/gregorypetrov/Documents/FileSystemWorkCopySecondMethod.java").toAbsolutePath().normalize();



	@BeforeEach
	void setUp() {
		fileWork = new FileSystemWork();
		newDir1.toFile().delete();
		newDir2.toFile().delete();
	}

	@Test
	void testDisplayFalse() {
		Path pathFalse = fs.getPath("/Users/gregory//").toAbsolutePath().normalize();
		Path path = fs.getPath("/Users/gregorypetrov/Documents").toAbsolutePath().normalize();

		int depthFalse = -5;
		int depth = 2;

		try {
			fileWork.displayDirectoryContentWalkFT(pathFalse, depth);
			fail();
		} catch (Throwable e) {

		}

		try {
			fileWork.displayDirectoryContentWalkFT(path, depthFalse);
			fail();
		} catch (Throwable e) {

		}

	}

	@Test
	void testDisplayTrue() {
		Path path = fs.getPath(".").toAbsolutePath().normalize();
		int depth = 5;

		try {
			System.out.println("\n\n\tWalkFileTrie method");
			fileWork.displayDirectoryContentWalkFT(path, depth);
		} catch (Throwable e) {
			fail();
		}
	}
	
	@Test
	void testDisplayRecursive() {
		Path path = fs.getPath("/Users/gregorypetrov/Documents").toAbsolutePath().normalize();
		int depth = 3;

		try {
			System.out.println("\n\n\tRecursion method");
			fileWork.displayDirectoryContentRecursive(path, depth);
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
		
		
	}

	@Test
	void testFileCSM() throws IOException {

		try {
			fileWork.fileByteStreamCopy(source, newDir2);
		} catch (IOException e) {
			fail();
		}
		
	}
	
	@Test
	void testFileByteArrayStreamCopy() throws IOException {

		try {
			fileWork.fileByteArrayStreamCopy(source, newDir2);
		} catch (IOException e) {
			fail();
		}
		
	
	}
	
	

	

}
