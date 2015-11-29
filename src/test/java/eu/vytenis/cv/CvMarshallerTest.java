package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import eu.vytenis.cv.builders.CvBuilder;
import eu.vytenis.cv.xmlio.CvMarshaller;

public class CvMarshallerTest {
	@Test
	public void buildsCv() throws Exception {
		String xml = marshall(new CvBuilder().build());
		assertNotNull(xml);
	}

	private String marshall(CV cv) throws JAXBException {
		return new CvMarshaller().marshall(cv);
	}
}
