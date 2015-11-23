package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.w3._1999.xsl.format.Root;

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
