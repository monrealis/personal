package eu.vytenis.cv.fo;

import org.junit.Test;
import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TableBody;
import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableColumn;
import org.w3._1999.xsl.format.TableRow;

import eu.vytenis.cv.xmlio.FoMarshaller;

public class FoTableBuilderTest {
	private boolean print = true;

	@Test
	public void buildsTable() {
		Table table = createTable();
		print(new FoMarshaller().marshallObject(table));
	}

	private Table createTable() {
		Table table = new Table();
		table.getTableColumn().add(new TableColumn());
		table.getTableColumn().add(new TableColumn());
		TableBody body = new TableBody();
		table.getTableBody().add(body);
		TableRow row = new TableRow();
		row.getTableCell().add(new TableCell());
		row.getTableCell().add(new TableCell());
		body.getTableRow().add(row);
		return table;
	}

	private void print(String xml) {
		if (print)
			System.out.println(xml);
	}
}
