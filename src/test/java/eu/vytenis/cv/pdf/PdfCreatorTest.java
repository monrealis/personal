package eu.vytenis.cv.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.fo.FoRootBuilder;
import eu.vytenis.cv.pdf.PdfCreator;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class PdfCreatorTest {
	@Test
	public void createPdf() {
		byte[] bytes = new PdfCreator(buildXml()).createPdf();
		writeToFile(bytes);
	}

	private void writeToFile(byte[] bytes) {
		try (FileOutputStream fos = new FileOutputStream("target/test.pdf");) {
			fos.write(bytes);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private String buildXml() {
		Root root = new FoRootBuilder().build();
		String xml = new FoMarshaller().marshall(root);
		return xml;
	}

}
