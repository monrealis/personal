package eu.vytenis.cv.fo;

import java.io.File;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.desktop.FileOpener;
import eu.vytenis.cv.pdf.PdfCreator;
import eu.vytenis.cv.pdf.PdfWriter;

public class FopExecutor {
	private final Builder<String> xmlBuilder;
	private final boolean openFile;

	public FopExecutor(Builder<String> xmlBuilder, boolean openFile) {
		this.xmlBuilder = xmlBuilder;
		this.openFile = openFile;
	}

	public void run() {
		PdfCreator creator = new PdfCreator(xmlBuilder);
		Builder<byte[]> pdfBuilder = () -> creator.createPdf();
		File file = new PdfWriter(pdfBuilder).writeToFile();
		new FileOpener(file, openFile).open();
	}
}
