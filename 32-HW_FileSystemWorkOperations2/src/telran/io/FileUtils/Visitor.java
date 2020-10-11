package telran.io.FileUtils;

import static java.nio.file.FileVisitResult.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Visitor implements FileVisitor {
	private int countsOfPreVisitLoops = 0;
	private String prefix;
	private int prefixLength;
	private String path;

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
		if (countsOfPreVisitLoops == 0) {
			System.out.println(dir);
			prefix = dir.toString();
		}

		
		 path = dir.toString().substring(prefix.length());
		 prefixLength = prefix.length() + path.length();

		System.out.println(" ".repeat(prefixLength) + path);
		countsOfPreVisitLoops++;

		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
		//System.out.println(file);


		System.out.println(" ".repeat(prefixLength) + file.toString().substring(prefixLength + 1) );
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
		System.err.println(exc);
		return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
		//System.out.println("post viiste");
		
		prefixLength -= path.length();
		return CONTINUE;
	}

}
