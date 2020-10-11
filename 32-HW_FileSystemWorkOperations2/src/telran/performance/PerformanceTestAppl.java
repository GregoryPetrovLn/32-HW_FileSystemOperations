package telran.performance;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import telran.io.FileUtils.FileSystemWork;

public class PerformanceTestAppl {
	private static FileSystemWork fileWork;
	private static  FileSystem fs = FileSystems.getDefault();
	private static int nRuns = 100;
	
	private static Path source = fs.getPath("./src/telran/io/FileUtils/FileSystemWork.java").toAbsolutePath().normalize();
	private static Path newDir1 = fs.getPath("/Users/gregorypetrov/Documents/FileSystemWorkCopyFirstMethod.java").toAbsolutePath().normalize();
	private static Path newDir2 = fs.getPath("/Users/gregorypetrov/Documents/FileSystemWorkCopySecondMethod.java").toAbsolutePath().normalize();

	public static void main(String[] args) {
		FSWStandartCopyPerformanceTest perfTestStandrt = new FSWStandartCopyPerformanceTest("FileStandartCopy", nRuns, source, newDir1);
		FSWStreamPerformanceTest perfTestStream = new FSWStreamPerformanceTest("FileByteStreamCopy", nRuns, source, newDir2);
		FByteArrayStreamCopyPerfTest perfTestByteStream =new FByteArrayStreamCopyPerfTest("ByteArrayStreamCopy", nRuns, source, newDir2);
		
		System.out.println("\t\tPerofrmance tests:\n");
		perfTestStandrt.run();
		perfTestStream.run();
		perfTestByteStream.run();

	}

}
