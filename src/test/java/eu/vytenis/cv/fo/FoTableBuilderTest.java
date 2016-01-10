package eu.vytenis.cv.fo;

import org.junit.Test;
import org.w3._1999.xsl.format.Table;

import eu.vytenis.cv.fop.FopExecutor;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoTableBuilderTest {
	private boolean openFile = false;

	@Test
	public void buildsTable() {
		Table table = createTable();
		print(new FoMarshaller() {
			public String marshallObject(Object o) {
				return super.marshallObject(o);
			};
		}.marshallObject(table));
	}

	@Test
	public void addsTableToPdf() {
		FoBuilder builder = new FoBuilder();
		builder.addContent(createTable());
		new FopExecutor(builder, openFile).run();
	}

	private Table createTable() {
		return new FoTableBuilder().withEmptyRow().build();
	}

	private void print(String xml) {
		if (openFile)
			System.out.println(xml);
	}
}
