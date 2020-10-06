package telran.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.StandardCopyOption;

public class FileSystemWork {
	/**
	 * 
	 * @param path
	 * @param depth
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void displayDirectoryContent(Path path, int depth) throws Exception {
		if (!Files.exists(path)) {
			throw new InvalidPathException(path.toString(), "Path doesn't exist");
		}
		if (depth < 0) {
			throw new IllegalArgumentException("Depth can't be less then zero");
		}
		if (depth == 0) {
			depth = Integer.MAX_VALUE;
		}
		
		//having problems with 
		//steps output of documents and folders to the console
		
		

		Files.walkFileTree(path, new HashSet<FileVisitOption>(), depth, new Visitor());
	}

	/**
	 * 
	 * @param source
	 * @param newDir
	 * @throws IOException
	 */
	public void fileStandartCopy(Path source, Path newDir) throws IOException {
		if (!Files.exists(source)) {
			throw new InvalidPathException(source.toString(), "Path doesn't exist");
		}

		Files.copy(source, newDir, StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * 
	 * @param source
	 * @param newDir
	 * @throws IOException
	 */
	public void fileByteStreamCopy(Path source, Path newDir) throws IOException {
		if (!Files.exists(source)) {
			throw new InvalidPathException(source.toString(), "Path doesn't exist");
		}
		if(Files.exists(newDir)) {
			Files.delete(newDir);
		}

		FileInputStream in = null;
		FileOutputStream out = null;
		

		try {
			in = new FileInputStream(source.toString());
			out = new FileOutputStream(newDir.toString());
			int c;

			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

}
