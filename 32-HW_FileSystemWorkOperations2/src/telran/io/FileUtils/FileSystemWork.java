package telran.io.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FileSystemWork {
	/**
	 * 
	 * @param path
	 * @param depth
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void displayDirectoryContentWalkFT(Path path, int depth) throws Exception {
		if (!Files.exists(path)) {
			throw new InvalidPathException(path.toString(), "Path doesn't exist");
		}
		if (depth < 0) {
			throw new IllegalArgumentException("Depth can't be less then zero");
		}
		if (depth == 0) {
			depth = Integer.MAX_VALUE;
		}

		Files.walkFileTree(path, new HashSet<FileVisitOption>(), depth, new Visitor());
	}

	/**
	 * 
	 * @param path
	 * @param depth
	 * @throws Exception
	 */
	public static void displayDirectoryContentRecursive(Path path, int depth) throws Exception {
		if (!Files.exists(path)) {
			throw new InvalidPathException(path.toString(), "Path doesn't exist");
		}
		if (depth < 0) {
			throw new IllegalArgumentException("Depth can't be less then zero");
		}
		if (depth == 0) {
			depth = Integer.MAX_VALUE;
		}

		displayDirContentRecursive(path, 0, depth);

	}

	private static void displayDirContentRecursive(Path path, int init, int depth) throws IOException {
		if (init > depth) {
			return;
		}

		if (Files.isDirectory(path)) {
			System.out.printf("   ".repeat(init) + "%s\n", path.getFileName());
			Files.list(path).forEach(p -> {
				try {
					displayDirContentRecursive(p, init + 1, depth);

				} catch (Throwable e) {
					e.printStackTrace();
				}
			});
		} else {
			System.out.printf("   ".repeat(init) + "%s\n", path.getFileName());
		}

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
		if (Files.exists(newDir)) {
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
			in.close();
			out.close();

		}
	}

	/**
	 * 
	 * @param source
	 * @param newDir
	 * @throws IOException
	 */
	public void fileByteArrayStreamCopy(Path source, Path newDir) throws IOException {
		if (!Files.exists(source)) {
			throw new InvalidPathException(source.toString(), "Path doesn't exist");
		}
		if (Files.exists(newDir)) {
			Files.delete(newDir);
		}

		long sizeBitLong = getSize(source);// bit
		int sizeByte = (int) (sizeBitLong / 8);


		InputStream from = null;
		OutputStream to = null;

		try {
			from = new FileInputStream(source.toString());
			to = new FileOutputStream(newDir.toString());

			if (sizeByte > Integer.MAX_VALUE ) {
				sizeByte = Integer.MAX_VALUE ;
			}

			if (sizeByte < 1) {
				sizeByte = 1;
			}

			byte[] buffer = new byte[sizeByte];

			while (from.read(buffer) > 0) {
				to.write(buffer);
			}

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			from.close();
			to.close();

		}

	}

	/**
	 * 
	 * @param startPath
	 * @return
	 * @throws IOException
	 */
	static long getSize(Path startPath) throws IOException {
		final AtomicLong size = new AtomicLong();

		Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				size.addAndGet(attrs.size());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				// Skip folders that can't be traversed
				System.out.println("skipped: " + file + "e=" + exc);
				return FileVisitResult.CONTINUE;
			}
		});

		return size.get();
	}

}
