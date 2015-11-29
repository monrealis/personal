package eu.vytenis.cv.fo;

import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TableBody;
import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableColumn;
import org.w3._1999.xsl.format.TableRow;

import eu.vytenis.cv.builders.Builder;

public class FoTableBuilder implements Builder<Table> {
	public Table build() {
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

}
