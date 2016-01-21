package eu.vytenis.cv.xmlio;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CvUnmarshallerTest {
	@Test
	public void unmarshalls() {
		String xml = "<cv xmlns='http://vytenis.eu/cv'/>";
		assertNotNull(new CvUnmarshaller().unmarshall(xml));
	}
}
