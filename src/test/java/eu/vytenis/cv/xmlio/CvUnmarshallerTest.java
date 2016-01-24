package eu.vytenis.cv.xmlio;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import eu.vytenis.cv.CV;

public class CvUnmarshallerTest {
	private String xml = "<cv xmlns='http://vytenis.eu/cv'/>";
	private String invalidXml = "<cv xmlns='http://vytenis.eu/cv' invalid='' />";
	private CvUnmarshaller unmarshaller = new CvUnmarshaller();

	@Test
	public void unmarshallsString() {
		assertNotNull(unmarshall());
	}

	@Test
	public void unmarshallsValidXmlWithValidation() {
		unmarshaller.setValidating(true);
		assertNotNull(unmarshall());
	}

	@Test(expected = RuntimeException.class)
	public void doesNotUnmarshallInvalidXmlWithValidation() {
		xml = invalidXml;
		unmarshaller.setValidating(true);
		unmarshall();
	}

	private CV unmarshall() {
		return unmarshaller.unmarshall(xml);
	}

	@Test
	public void unmarshallsInputStream() {
		ByteArrayInputStream xmlBytes = new ByteArrayInputStream(xml.getBytes());
		assertNotNull(unmarshaller.unmarshall(xmlBytes));
	}
}
