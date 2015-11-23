package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

public class XsdParserTest {
	@Test
	public void readsCvXsd() throws SAXException {
		SchemaFactory f = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		InputStream is = getClass().getResourceAsStream("/cv.xsd");
		Schema schema = f.newSchema(new StreamSource(is));
		assertNotNull(schema);
	}
}
