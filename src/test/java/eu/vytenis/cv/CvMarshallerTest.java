package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.example.cv.CV;
import org.example.cv.ObjectFactory;
import org.junit.Test;

public class CvMarshallerTest {
	@Test
	public void buildsCv() throws Exception {
		String xml = marshall(new CvBuilder().build());
		assertNotNull(xml);
	}

	private String marshall(CV cv) throws JAXBException {
		ObjectFactory f = new ObjectFactory();
		JAXBContext c = JAXBContext.newInstance(f.getClass());
		Marshaller m = c.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter w = new StringWriter();
		m.marshal(f.createCv(cv), w);
		String xml = w.toString();
		return xml;
	}
}
