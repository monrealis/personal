package eu.vytenis.cv.xmlio;

import eu.vytenis.cv.CV;
import eu.vytenis.cv.ObjectFactory;

public class CvMarshaller extends BaseMarshaller {
	public CvMarshaller() {
		super(ObjectFactory.class);
	}

	public String marshall(CV cv) {
		return marshallObject(new ObjectFactory().createCv(cv));
	}
}
