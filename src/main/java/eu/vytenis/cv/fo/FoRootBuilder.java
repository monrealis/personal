package eu.vytenis.cv.fo;

import java.util.List;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Flow;
import org.w3._1999.xsl.format.LayoutMasterSet;
import org.w3._1999.xsl.format.PageSequence;
import org.w3._1999.xsl.format.RegionBody;
import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.SimplePageMaster;

public class FoRootBuilder {
	private Root root;
	private String masterName = "A4-portrait";
	private String fontFamily = "Liberation Sans Narrow";

	public Root build() {
		root = createRoot();
		flowContent().add(createBlock());
		return root;
	}

	private Root createRoot() {
		Root r = new Root();
		r.setLayoutMasterSet(createMasterSet());
		r.getPageSequence().add(createPageSequence());
		return r;
	}

	private LayoutMasterSet createMasterSet() {
		LayoutMasterSet ms = new LayoutMasterSet();
		ms.getSimplePageMasterOrPageSequenceMaster().add(createMaster());
		return ms;
	}

	private SimplePageMaster createMaster() {
		SimplePageMaster m = new SimplePageMaster();
		m.setMasterName(masterName);
		m.setPageHeight("29.7cm");
		m.setPageWidth("21.0cm");
		m.getMargin().add("2cm");
		m.setRegionBody(new RegionBody());
		return m;
	}

	private Flow createFlow() {
		Flow flow = new Flow();
		flow.setFlowName("xsl-region-body");
		flow.setFontFamily(fontFamily);
		return flow;
	}

	private PageSequence createPageSequence() {
		PageSequence sequence = new PageSequence();
		sequence.setMasterReference(masterName);
		sequence.setFlow(createFlow());
		return sequence;
	}

	private Block createBlock() {
		Block block = new Block();
		return block;
	}

	private List<Object> flowContent() {
		return flow().getMarkerOrBlockOrBlockContainer();
	}

	private Flow flow() {
		return root.getPageSequence().get(0).getFlow();
	}
}
