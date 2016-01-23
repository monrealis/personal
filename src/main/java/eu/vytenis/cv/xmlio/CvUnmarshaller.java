package eu.vytenis.cv.xmlio;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eu.vytenis.cv.CV;
import eu.vytenis.cv.ObjectFactory;

public class CvUnmarshaller extends BaseUnmarshaller<CV> {
	public CvUnmarshaller() {
		super(CV.class, ObjectFactory.class);
	}

	public CV unmarshall(InputStream xml) {
		try {
			return tryUnmarshall(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public CV unmarshall(String xml) {
		try {
			return tryUnmarshall(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private CV tryUnmarshall(InputStream xml) throws JAXBException {
		return asCv((JAXBElement<?>) createUnmarshaller().unmarshal(xml));
	}

	private CV tryUnmarshall(String xml) throws JAXBException {
		StringReader r = new StringReader(xml);
		return asCv((JAXBElement<?>) createUnmarshaller().unmarshal(r));
	}

	private CV asCv(JAXBElement<?> element) {
		return (CV) element.getValue();
	}

	private Unmarshaller createUnmarshaller() throws JAXBException {
		Class<?>[] types = { ObjectFactory.class };
		JAXBContext c = new ContextCache().getContext(types);
		return c.createUnmarshaller();
	}
}
