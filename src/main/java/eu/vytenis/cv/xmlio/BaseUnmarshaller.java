package eu.vytenis.cv.xmlio;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

public class BaseUnmarshaller<T> {
	private final Class<T> type;
	private final Class<?>[] objectFactoryTypes;
	private Schema schema;

	public BaseUnmarshaller(Class<T> type, Class<?>... objectFactoryTypes) {
		this.type = type;
		this.objectFactoryTypes = objectFactoryTypes;
	}

	protected T unmarshall(InputStream xml) {
		try {
			return tryUnmarshall(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	protected T unmarshall(String xml) {
		try {
			return tryUnmarshall(xml);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private T tryUnmarshall(InputStream xml) throws JAXBException {
		return extract((JAXBElement<?>) createUnmarshaller().unmarshal(xml));
	}

	private T tryUnmarshall(String xml) throws JAXBException {
		StringReader r = new StringReader(xml);
		return extract((JAXBElement<?>) createUnmarshaller().unmarshal(r));
	}

	private T extract(JAXBElement<?> element) {
		return type.cast(element.getValue());
	}

	private Unmarshaller createUnmarshaller() throws JAXBException {
		JAXBContext c = new ContextCache().getContext(objectFactoryTypes);
		Unmarshaller um = c.createUnmarshaller();
		um.setSchema(schema);
		return um;
	}

	protected void setSchema(Schema schema) {
		this.schema = schema;
	}
}
