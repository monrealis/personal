package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;
import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Flow;
import org.w3._1999.xsl.format.LayoutMasterSet;
import org.w3._1999.xsl.format.ObjectFactory;
import org.w3._1999.xsl.format.PageSequence;
import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.SimplePageMaster;

public class FoMarshallerTest {
	@Test
	public void run() throws JAXBException {
		String xml = marshall(buildRoot());
		assertNotNull(xml);
		System.out.println(xml);
	}

	private Root buildRoot() {
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

	private String marshall(Root root) throws JAXBException {
		ObjectFactory f = new ObjectFactory();
		JAXBContext c = JAXBContext.newInstance(f.getClass());
		Marshaller m = c.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter w = new StringWriter();
		m.marshal(root, w);
		String xml = w.toString();
		return xml;
	}
}
