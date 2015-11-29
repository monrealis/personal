package eu.vytenis.cv.fop;

import java.io.File;

import org.w3._1999.xsl.format.Root;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.desktop.FileOpener;
import eu.vytenis.cv.pdf.PdfCreator;
import eu.vytenis.cv.pdf.PdfWriter;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class FopExecutor {
	private final Builder<Root> foRootBuilder;
	private final boolean openFile;

	public FopExecutor(Builder<Root> xmlBuilder, boolean openFile) {
		this.foRootBuilder = xmlBuilder;
		this.openFile = openFile;
	}

	public void run() {
		Builder<String> xmlBuilder = () -> new FoMarshaller()
				.marshall(foRootBuilder.build());
		PdfCreator creator = new PdfCreator(xmlBuilder);
		Builder<byte[]> pdfBuilder = () -> creator.createPdf();
		File file = new PdfWriter(pdfBuilder).writeToFile();
		new FileOpener(file, openFile).open();
	}
}
