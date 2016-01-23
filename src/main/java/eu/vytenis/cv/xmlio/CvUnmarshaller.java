package eu.vytenis.cv.xmlio;

import java.io.InputStream;

import eu.vytenis.cv.CV;
import eu.vytenis.cv.ObjectFactory;

public class CvUnmarshaller extends BaseUnmarshaller<CV> {
	public CvUnmarshaller() {
		super(CV.class, ObjectFactory.class);
	}

	@Override
	public CV unmarshall(InputStream xml) {
		return super.unmarshall(xml);
	}
}
