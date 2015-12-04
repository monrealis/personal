package eu.vytenis.cv.fo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
	private int numberOfColumns = 2;
	private Table table = createTable();
	private List<TableRow> rows = new ArrayList<>();
	private Map<Integer, Integer> columnWidths = new HashMap<>();
	private Map<Integer, TextAlignType> textAligns = new HashMap<>();

	public FoTableBuilder() {
	}

	public FoTableBuilder(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

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
		for (int i = 0; i < numberOfColumns; ++i)
			table.getTableColumn().add(
					createColumn(columnWidths.getOrDefault(i, 1)));
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

	public void add(String... values) {
		TableRow row = new TableRow();
		for (int i = 0; i < values.length; ++i)
			row.getTableCell().add(createCell(values[i], getTextAlignAt(i)));
		rows.add(row);
	}

	private TextAlignType getTextAlignAt(int columnIndex) {
		return textAligns.get(columnIndex);
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

	public FoTableBuilder withTextAlign(int columnIndex, TextAlignType textAlign) {
		textAligns.put(columnIndex, textAlign);
		return this;
	}

	public FoTableBuilder withColumnWidth(int columnIndex, int width) {
		columnWidths.put(columnIndex, width);
		return this;
	}

	public void addBlock(String text, Consumer<Block> adjuster, int colIndex) {
		Block b = FoBlockBuilder.createBlock(text, adjuster);
		getCellAt(colIndex).getMarkerOrBlockOrBlockContainer().add(b);
	}

	public TableCell getCellAt(int columnIndex) {
		return getLastRow().getTableCell().get(columnIndex);
	}

	private TableRow getLastRow() {
		return rows.get(rows.size() - 1);
	}
}
