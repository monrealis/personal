package eu.vytenis.cv.pdf;

import java.io.File;

import org.junit.Test;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.desktop.FileOpener;
import eu.vytenis.cv.fo.FoRootBuilder;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class PdfWriterTest {
	private File file;
	private boolean openFile = false;

	@Test
	public void createPdf() {
		byte[] bytes = new PdfCreator(buildXml()).createPdf();
		writeToFile(bytes);
		openFile();
	}

	private String buildXml() {
		Root root = new FoRootBuilder().build();
		String xml = new FoMarshaller().marshall(root);
		return xml;
	}

	private void writeToFile(byte[] bytes) {
		PdfWriter w = new PdfWriter(bytes);
		file = w.writeToFile();
	}

	private void openFile() {
		new FileOpener(file, openFile).open();
	}
}
