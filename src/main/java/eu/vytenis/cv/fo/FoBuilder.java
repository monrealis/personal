package eu.vytenis.cv.fo;

import java.util.List;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Flow;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoBuilder {
	private Root root;

	public FoBuilder() {
		root = new FoRootBuilder().build();
	}

	public void add(String key, String value) {
		Block b = new Block();
		b.getContent().add(key + " = " + value);
		content().add(b);
	}

	public String buildXml() {
		return new FoMarshaller().marshall(root);
	}

	private List<Object> content() {
		return flow().getMarkerOrBlockOrBlockContainer();
	}

	private Flow flow() {
		return root.getPageSequence().get(0).getFlow();
	}
}
