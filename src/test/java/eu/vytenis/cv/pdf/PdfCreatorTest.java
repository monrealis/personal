package eu.vytenis.cv.pdf;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.fo.FoRootBuilder;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class PdfCreatorTest {
	@Test
	public void createPdf() {
		assertNotNull(new PdfCreator(buildXml()).createPdf());
	}

	private String buildXml() {
		Root root = new FoRootBuilder().build();
		String xml = new FoMarshaller().marshall(root);
		return xml;
	}
}
