package eu.vytenis.cv.fo;

import static org.junit.Assert.assertTrue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.pdf.PdfCreator;
import eu.vytenis.cv.pdf.PdfWriter;

public class FoBuilderTest {
	private FoBuilder builder = new FoBuilder();

	@Test
	public void addsKeyValue() {
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		String xml = buildXml();
		assertTrue(xml, xml.contains("Vardenis"));
	}

	@Test
	public void createsPdf() throws IOException {
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		Builder<String> xmlBuilder = () -> buildXml();
		PdfCreator creator = new PdfCreator(xmlBuilder);
		Builder<byte[]> pdfBuilder = () -> creator.createPdf();
		File file = new PdfWriter(pdfBuilder).writeToFile();
		Desktop.getDesktop().open(file);
	}

	private String buildXml() {
		return print(builder.buildXml());
	}

	private String print(String xml) {
		System.out.println(xml);
		return xml;
	}
}
