package eu.vytenis.cv.fo;

import java.util.ArrayList;
import java.util.List;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Flow;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.builders.Builder;

public class FoBuilder implements Builder<Root> {
	private Root root;
	private List<Object> content = new ArrayList<>();

	public FoBuilder() {
		root = new FoRootBuilder().build();
	}

	public void add(String key, String value) {
		Block b = new Block();
		b.getContent().add(key + " = " + value);
		content().add(b);
	}

	public Root build() {
		content.forEach(c -> content().add(c));
		return root;
	}

	private List<Object> content() {
		return flow().getMarkerOrBlockOrBlockContainer();
	}

	private Flow flow() {
		return root.getPageSequence().get(0).getFlow();
	}

	public void addContent(Object o) {
		content.add(o);
	}
}
