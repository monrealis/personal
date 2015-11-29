package eu.vytenis.cv.fo;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import eu.vytenis.cv.fop.FopExecutor;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoBuilderTest {
	private FoBuilder builder = new FoBuilder();
	private boolean openFile = false;

	@Test
	public void addsKeyValue() {
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		String xml = print(buildXml());
		assertTrue(xml, xml.contains("Vardenis"));
	}

	@Test
	public void createsPdf() throws IOException {
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		new FopExecutor(builder, openFile).run();
	}

	private String buildXml() {
		return print(new FoMarshaller().marshall(builder.build()));
	}

	private String print(String xml) {
		System.out.println(xml);
		return xml;
	}
}
