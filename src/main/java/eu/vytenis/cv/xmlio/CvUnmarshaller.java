package eu.vytenis.cv.xmlio;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eu.vytenis.cv.CV;
import eu.vytenis.cv.ObjectFactory;

public class CvUnmarshaller {

	public CV unmarshall(String xml) {
		try {
			return tryUnmarshall(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private CV tryUnmarshall(String xml) throws JAXBException {
		StringReader r = new StringReader(xml);
		JAXBElement<?> el = (JAXBElement<?>) createUnmarshaller().unmarshal(r);
		return (CV) el.getValue();
	}

	private Unmarshaller createUnmarshaller() throws JAXBException {
		Class<?>[] types = { ObjectFactory.class };
		JAXBContext c = new ContextCache().getContext(types);
		return c.createUnmarshaller();
	}
}
