package eu.vytenis.cv.xmlio;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.fo.FoBuilder;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoMarshallerTest {
	@Test
	public void run() {
		String xml = marshall(new FoBuilder().build());
		assertNotNull(xml);
		System.out.println(xml);
	}

	private String marshall(Root root) {
		return new FoMarshaller().marshall(root);
	}
}
