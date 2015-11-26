package eu.vytenis.cv.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfWriter {
	private final byte[] bytes;
	private final File file = new File("target/test.pdf");

	public PdfWriter(byte[] bytes) {
		super();
		this.bytes = bytes;
	}

	public void writeToFile() {
		try (FileOutputStream fos = new FileOutputStream(file);) {
			fos.write(bytes);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public File getFile() {
		return file;
	}
}
