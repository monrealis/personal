package eu.vytenis.cv.fo;

import static java.util.Arrays.asList;

import java.util.List;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.BorderCollapseType;
import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TableBody;
import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableColumn;
import org.w3._1999.xsl.format.TableLayoutType;
import org.w3._1999.xsl.format.TableRow;

import eu.vytenis.cv.builders.Builder;

public class FoTableBuilder implements Builder<Table> {
	private Table table = createTable();

	public Table build() {
		addColumns();
		addBody();
		return table;
	}

	private Table createTable() {
		Table t = new Table();
		t.setWidth("100%");
		t.setBorderCollapse(BorderCollapseType.COLLAPSE);
		t.setTableLayout(TableLayoutType.FIXED);
		return t;
	}

	private void addBody() {
		table.getTableBody().add(createBody());
	}

	private void addColumns() {
		table.getTableColumn().add(createColumn());
		table.getTableColumn().add(createColumn());
	}

	private TableColumn createColumn() {
		TableColumn c = new TableColumn();
		c.getColumnWidth().add("proportional-column-width(1)");
		return c;
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
		row.getTableCell().add(createCell());
		row.getTableCell().add(createCell());
		return row;
	}

	private TableCell createCell() {
		TableCell cell = new TableCell();
		cell.setBorder("1pt solid black");
		Block block = new Block();
		block.getContent().add("-");
		cell.getMarkerOrBlockOrBlockContainer().add(block);
		return cell;
	}
}
