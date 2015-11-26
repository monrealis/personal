package eu.vytenis.cv.pdf;

import java.io.File;
import java.io.StringReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.builders.ConstBuilder;

public class PdfCreator {
	private final Builder<String> xmlBuilder;

	public PdfCreator(String xml) {
		this.xmlBuilder = new ConstBuilder<String>(xml);
	}

	public byte[] createPdf() {
		try {
			return tryCreatePdf();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] tryCreatePdf() throws Exception {
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, bos);
		Transformer t = TransformerFactory.newInstance().newTransformer();
		StreamSource source = createSource();
		t.transform(source, createResult(fop));
		byte[] bytes = bos.toByteArray();
		return bytes;
	}

	private StreamSource createSource() {
		return new StreamSource(new StringReader(xmlBuilder.build()));
	}

	private SAXResult createResult(Fop fop) throws FOPException {
		return new SAXResult(fop.getDefaultHandler());
	}

}
