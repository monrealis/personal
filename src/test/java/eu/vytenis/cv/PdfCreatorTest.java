package eu.vytenis.cv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.junit.Test;
import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.xmlio.FoMarshaller;

public class PdfCreatorTest {
	@Test
	public void run() throws Exception {
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, bos);
		Transformer t = TransformerFactory.newInstance().newTransformer();
		StreamSource source = new StreamSource(build());
		t.transform(source, new SAXResult(fop.getDefaultHandler()));
		try (FileOutputStream fos = new FileOutputStream("target/test.pdf");) {
			fos.write(bos.toByteArray());
		}
	}

	private Reader build() {
		Root root = new FoBuilder().build();
		String xml = new FoMarshaller().marshall(root);
		System.out.println(xml);
		return new StringReader(xml);
	}

}
