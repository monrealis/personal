package eu.vytenis.cv.fo;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.BorderCollapseType;
import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TableBody;
import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableColumn;
import org.w3._1999.xsl.format.TableLayoutType;
import org.w3._1999.xsl.format.TableRow;
import org.w3._1999.xsl.format.TextAlignType;

import eu.vytenis.cv.builders.Builder;

public class FoTableBuilder implements Builder<Table> {
	private Table table = createTable();
	private List<TableRow> rows = new ArrayList<>();
	private TextAlignType textAlignOfFirstColumn;
	private List<Integer> columnWidths = new ArrayList<Integer>(asList(1, 1));

	public Table build() {
		addColumns();
		addBody();
		return table;
	}

	public FoTableBuilder withEmptyRow() {
		rows.add(createDefaultRow());
		return this;
	}

	private TableRow createDefaultRow() {
		TableRow row = new TableRow();
		row.getTableCell().add(createCell());
		row.getTableCell().add(createCell());
		return row;
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
		for (int w : columnWidths)
			table.getTableColumn().add(createColumn(w));
	}

	private TableColumn createColumn(int width) {
		TableColumn c = new TableColumn();
		c.getColumnWidth().add(
				String.format("proportional-column-width(%s)", width));
		return c;
	}

	private TableBody createBody() {
		TableBody body = new TableBody();
		body.getTableRow().addAll(rows);
		return body;
	}

	public void add(String key, String value) {
		TableRow row = new TableRow();
		row.getTableCell().add(createCell(key, textAlignOfFirstColumn));
		row.getTableCell().add(createCell(value, null));
		rows.add(row);
	}

	private TableCell createCell() {
		return createCell(" ", null);
	}

	private TableCell createCell(String text, TextAlignType textAlign) {
		TableCell cell = new TableCell();
		cell.setTextAlign(textAlign);
		cell.setPaddingRight("0.5cm");
		Block block = createBlock(text);
		cell.getMarkerOrBlockOrBlockContainer().add(block);
		return cell;
	}

	private Block createBlock(String text) {
		Block block = new Block();
		block.getContent().add(text);
		return block;
	}

	public FoTableBuilder withTextAlignOfFirstColumn(
			TextAlignType textAlignOfFirstColumn) {
		this.textAlignOfFirstColumn = textAlignOfFirstColumn;
		return this;
	}

	public FoTableBuilder withColumWidth(int columnIndex, int width) {
		columnWidths.set(columnIndex, width);
		return this;
	}

	public TableCell getCellAt(int columnIndex) {
		return getLastRow().getTableCell().get(columnIndex);
	}

	private TableRow getLastRow() {
		return rows.get(rows.size() - 1);
	}
}
