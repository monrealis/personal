package eu.vytenis.cv.xmlio;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class CvUnmarshallerTest {
	private String xml = "<cv xmlns='http://vytenis.eu/cv'/>";
	private CvUnmarshaller unmarshaller = new CvUnmarshaller();

	@Test
	public void unmarshallsString() {
		assertNotNull(unmarshaller.unmarshall(xml));
	}

	@Test
	public void unmarshallsInputStream() {
		ByteArrayInputStream xmlBytes = new ByteArrayInputStream(xml.getBytes());
		assertNotNull(unmarshaller.unmarshall(xmlBytes));
	}
}
