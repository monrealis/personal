package eu.vytenis.cv.fo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FoBuilderTest {
	FoBuilder builder = new FoBuilder();

	@Test
	public void addsKeyValue() {
		builder.add("Vardas, pavardė", "Vardenis Pavardenis");
		assertTrue(buildXml(), buildXml().contains("Vardenis"));
	}

	private String buildXml() {
		return print(builder.buildXml());
	}

	private String print(String xml) {
		System.out.println(xml);
		return xml;
	}

}
