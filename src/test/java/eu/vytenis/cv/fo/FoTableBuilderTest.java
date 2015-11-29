package eu.vytenis.cv.fo;

import org.junit.Test;
import org.w3._1999.xsl.format.Table;

import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoTableBuilderTest {
	private boolean print = true;

	@Test
	public void buildsTable() {
		Table table = createTable();
		print(new FoMarshaller().marshallObject(table));
	}

	private Table createTable() {
		return new FoTableBuilder().build();
	}

	private void print(String xml) {
		if (print)
			System.out.println(xml);
	}
}
