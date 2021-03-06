package telran.performance;

import java.io.IOException;
import java.nio.file.Path;

import telran.io.FileUtils.FileSystemWork;

public class FSWStreamPerformanceTest extends PerformanceTest {
	FileSystemWork fs = new FileSystemWork();
	Path source;
	Path newDir;

	public FSWStreamPerformanceTest(String testName, int nRuns, Path source, Path newDir) {
		super(testName, nRuns);
		this.source = source;
		this.newDir = newDir;

	}

	@Override
	protected void runTest() {
		try {
			fs.fileByteStreamCopy(source, newDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
