package eu.vytenis.cv.fo;

import org.junit.Test;
import org.w3._1999.xsl.format.Table;

import eu.vytenis.cv.fop.FopExecutor;
import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoTableBuilderTest {
	private boolean print = true;

	@Test
	public void buildsTable() {
		Table table = createTable();
		print(new FoMarshaller().marshallObject(table));
	}

	@Test
	public void addsTableToPdf() {
		FoBuilder builder = new FoBuilder();
		builder.addContent(createTable());
		new FopExecutor(builder, print).run();
	}

	private Table createTable() {
		return new FoTableBuilder().withEmptyRow().build();
	}

	private void print(String xml) {
		if (print)
			System.out.println(xml);
	}
}
