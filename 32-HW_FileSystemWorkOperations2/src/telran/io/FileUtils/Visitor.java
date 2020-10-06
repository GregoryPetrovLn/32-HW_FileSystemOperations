package telran.io.FileUtils;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Visitor implements FileVisitor {
	private int countsOfPreVisitLoops = 0;
	private String prefix;

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		if (countsOfPreVisitLoops == 0) {
			System.out.println(dir);
			prefix = dir.toString();
		}

		String path = dir.toString().substring(prefix.length());

		System.out.println(" ".repeat(prefix.length()) + path);

		countsOfPreVisitLoops++;

		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		String path = file.toString().substring(prefix.length());

		System.out.println(" ".repeat(prefix.length()) + path);

		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		System.err.println(exc);
		return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {

		return CONTINUE;
	}

}
