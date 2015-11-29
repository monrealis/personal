package eu.vytenis.cv.fo;

import static java.util.Arrays.asList;

import java.util.List;

import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TableBody;
import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableColumn;
import org.w3._1999.xsl.format.TableRow;

import eu.vytenis.cv.builders.Builder;

public class FoTableBuilder implements Builder<Table> {
	private Table table = new Table();

	public Table build() {
		addColumns();
		addBody();
		return table;
	}

	private void addBody() {
		table.getTableBody().add(createBody());
	}

	private void addColumns() {
		table.getTableColumn().add(new TableColumn());
		table.getTableColumn().add(new TableColumn());
	}

	private TableBody createBody() {
		TableBody body = new TableBody();
		body.getTableRow().addAll(createRows());
		return body;
	}

	private List<TableRow> createRows() {
		return asList(createRow());
	}

	private TableRow createRow() {
		TableRow row = new TableRow();
		row.getTableCell().add(new TableCell());
		row.getTableCell().add(new TableCell());
		return row;
	}
}
