package eu.vytenis.cv.fo;

import static java.util.Arrays.stream;

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
import eu.vytenis.cv.function.ListConsumer;

public class FoTableBuilder implements Builder<Table> {
	private int numberOfColumns = 2;
	private final Table table = createTable();
	private final List<TableRow> rows = new ArrayList<>();
	private final Map<Integer, String> columnWidths = new HashMap<>();
	private final Map<Integer, TextAlignType> textAligns = new HashMap<>();
	private final ListConsumer<Block> blockFormatters = new ListConsumer<>();
	private final ListConsumer<TableCell> cellFormatters = new ListConsumer<>();
	private final ListConsumer<Table> tableFormatters = new ListConsumer<>();
	private String paddingRight = "0.5cm";

	public FoTableBuilder() {
	}

	public FoTableBuilder(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public Table build() {
		addColumns();
		addBody();
		format();
		return table;
	}

	private void format() {
		tableFormatters.accept(table);
	}

	public FoTableBuilder withEmptyRow() {
		rows.add(createDefaultRow());
		return this;
	}

	private TableRow createDefaultRow() {
		TableRow row = new TableRow();
		row.getTableCell().add(createEmptyCell());
		row.getTableCell().add(createEmptyCell());
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
		List<TableColumn> columns = table.getTableColumn();
		for (int i = 0; i < numberOfColumns; ++i)
			columns.add(createColumn(getColumnWidth(i)));
	}

	private String getColumnWidth(int columnIndex) {
		String defaultWidth = "proportional-column-width(1)";
		return columnWidths.getOrDefault(columnIndex, defaultWidth);
	}

	private TableColumn createColumn(String width) {
		TableColumn c = new TableColumn();
		c.getColumnWidth().add(width);
		return c;
	}

	private TableBody createBody() {
		TableBody body = new TableBody();
		body.getTableRow().addAll(rows);
		return body;
	}

	public FoTableBuilder add(Object... values) {
		TableRow row = new TableRow();
		appendCells(row, values);
		rows.add(row);
		return this;
	}

	public FoTableBuilder append(Object... values) {
		appendCells(getLastRow(), values);
		return this;
	}

	private void appendCells(TableRow row, Object... values) {
		List<TableCell> cells = row.getTableCell();
		int count = cells.size();
		for (int i = 0; i < values.length; ++i)
			cells.add(createCell(values[i], getTextAlignAt(count + i)));
	}

	private TextAlignType getTextAlignAt(int columnIndex) {
		return textAligns.get(columnIndex);
	}

	private TableCell createEmptyCell() {
		return createCell(" ", null);
	}

	private TableCell createCell(Object object, TextAlignType textAlign) {
		TableCell cell = createCell();
		cell.setTextAlign(textAlign);
		cell.setPaddingRight(paddingRight);
		cell.getMarkerOrBlockOrBlockContainer().add(toFoObject(object));
		return cell;
	}

	private TableCell createCell() {
		TableCell cell = new TableCell();
		cellFormatters.accept(cell);
		return cell;
	}

	private Block createBlock(String text) {
		Block block = new Block();
		block.getContent().add(text);
		blockFormatters.accept(block);
		return block;
	}

	public FoTableBuilder withTextAlign(int columnIndex, TextAlignType textAlign) {
		textAligns.put(columnIndex, textAlign);
		return this;
	}

	public FoTableBuilder withColumnWidth(int width, Integer... columnIndexes) {
		for (int columnIndex : columnIndexes)
			columnWidths.put(columnIndex,
					String.format("proportional-column-width(%s)", width));
		return this;
	}

	public FoTableBuilder withColumnWidth(String width,
			Integer... columnIndexes) {
		for (int columnIndex : columnIndexes)
			columnWidths.put(columnIndex, width);
		return this;
	}

	public void addBlock(String text, int colIndex, Consumer<Block> formatter) {
		blocks().push(formatter);
		addBlock(text, colIndex);
		blocks().pop();
	}

	public void addBlock(String text, int colIndex) {
		Block b = createBlock(text);
		getCellAt(colIndex).getMarkerOrBlockOrBlockContainer().add(b);
	}

	public FoTableBuilder clearCellAt(int columnIndex) {
		getCellAt(columnIndex).getMarkerOrBlockOrBlockContainer().clear();
		return this;
	}

	public FoTableBuilder appendToCell(int columnIndex, Object object) {
		Object o = toFoObject(object);
		getCellAt(columnIndex).getMarkerOrBlockOrBlockContainer().add(o);
		return this;
	}

	private Object toFoObject(Object object) {
		if (object instanceof String)
			return createBlock((String) object);
		return object;
	}

	public FoTableBuilder setColSpan(String colspan, Integer... columnIndexes) {
		stream(columnIndexes).forEach(
				i -> getCellAt(i).setNumberColumnsSpanned(colspan));
		return this;
	}

	public FoTableBuilder setRowSpan(String rowspan, Integer... columnIndexes) {
		stream(columnIndexes).forEach(
				i -> getCellAt(i).setNumberRowsSpanned(rowspan));
		return this;
	}

	public TableCell getCellAt(int columnIndex) {
		return getLastRow().getTableCell().get(columnIndex);
	}

	private TableRow getLastRow() {
		return rows.get(rows.size() - 1);
	}

	public Formatters<Block> blocks() {
		return new Formatters<>(blockFormatters);
	}

	public Formatters<TableCell> cells() {
		return new Formatters<>(cellFormatters);
	}

	public Formatters<Table> tables() {
		return new Formatters<>(tableFormatters);
	}

	public FoTableBuilder setCellPaddingRight(String paddingRight) {
		this.paddingRight = paddingRight;
		return this;
	}
}
