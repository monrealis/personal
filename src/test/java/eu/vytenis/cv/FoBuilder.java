package eu.vytenis.cv;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Flow;
import org.w3._1999.xsl.format.LayoutMasterSet;
import org.w3._1999.xsl.format.PageSequence;
import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.SimplePageMaster;

public class FoBuilder {
	public Root build() {
		Root r = new Root();
		LayoutMasterSet ms = new LayoutMasterSet();
		SimplePageMaster m = new SimplePageMaster();
		m.setMasterName("A4-portrait");
		m.setPageHeight("29.7cm");
		m.setPageWidth("21.0cm");
		m.getMargin().add("2cm");
		ms.getSimplePageMasterOrPageSequenceMaster().add(m);
		r.setLayoutMasterSet(ms);
		PageSequence sequence = new PageSequence();
		Flow flow = new Flow();
		flow.setFlowName("xsl-region-body");
		Block block = new Block();
		flow.getMarkerOrBlockOrBlockContainer().add(block);
		sequence.setFlow(flow);
		r.getPageSequence().add(sequence);
		return r;
	}

}
