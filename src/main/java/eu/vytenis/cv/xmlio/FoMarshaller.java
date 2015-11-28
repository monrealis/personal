package eu.vytenis.cv.xmlio;

import org.w3._1999.xsl.format.ObjectFactory;
import org.w3._1999.xsl.format.Root;

public class FoMarshaller extends BaseMarshaller {
	public FoMarshaller() {
		super(ObjectFactory.class);
	}

	public String marshall(Root root) {
		return marshallObject(root);
	}
	
	@Override
	public String marshallObject(Object o) {
		return super.marshallObject(o);
	}
}
