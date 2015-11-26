package eu.vytenis.cv.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.builders.ConstBuilder;

public class PdfWriter {
	private final Builder<byte[]> bytesBuilder;
	private final File file = new File("target/test.pdf");

	public PdfWriter(byte[] bytes) {
		this.bytesBuilder = new ConstBuilder<>(bytes);
	}

	public PdfWriter(Builder<byte[]> bytesBuilder) {
		this.bytesBuilder = bytesBuilder;
	}

	public File writeToFile() {
		try (FileOutputStream fos = new FileOutputStream(file);) {
			fos.write(bytesBuilder.build());
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return file;
	}
}
