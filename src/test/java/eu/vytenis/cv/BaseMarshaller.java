package eu.vytenis.cv;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.w3._1999.xsl.format.ObjectFactory;
import org.w3._1999.xsl.format.Root;

public class BaseMarshaller {
	protected Class[] types;

	protected BaseMarshaller(Class... types) {
		this.types = types;
	}

	protected String marshallObject(Object o) {
		try {
			return tryMarshall(o);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private String tryMarshall(Object o) throws JAXBException {
		Marshaller m = createMarshaller();
		StringWriter w = new StringWriter();
		m.marshal(o, w);
		String xml = w.toString();
		return xml;
	}

	private Marshaller createMarshaller() throws JAXBException {
		JAXBContext c = JAXBContext.newInstance(types);
		Marshaller m = c.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		return m;
	}
}
