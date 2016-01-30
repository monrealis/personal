package eu.vytenis.cv.xmlio;

import java.io.InputStream;

import javax.xml.validation.Schema;

import eu.vytenis.cv.CV;
import eu.vytenis.cv.ObjectFactory;
import eu.vytenis.cv.xsd.CvXsdParser;

public class CvUnmarshaller extends BaseUnmarshaller<CV> {
	public CvUnmarshaller() {
		super(CV.class, ObjectFactory.class);
	}

	@Override
	public CV unmarshall(InputStream xml) {
		return super.unmarshall(xml);
	}

	public void setValidating(boolean validating) {
		setSchema(getSchemaIfValidating(validating));
	}

	private Schema getSchemaIfValidating(boolean validating) {
		if (!validating)
			return null;
		return parseXsd();
	}

	private Schema parseXsd() {
		return new CvXsdParser().parse();
	}
}
