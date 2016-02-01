package eu.vytenis.cv.xsd;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class CvXsdParser {
	public Schema parse() {
		try {
			return tryParse();
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	private Schema tryParse() throws SAXException {
		SchemaFactory f = getSchemaFactory();
		InputStream is = getClass().getResourceAsStream("/cv.xsd");
		Schema schema = f.newSchema(new StreamSource(is));
		return schema;
	}

	private SchemaFactory getSchemaFactory() {
		return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	}
}
