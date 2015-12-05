package eu.vytenis.cv.fo;

import static java.util.stream.Collectors.toList;

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
	private Table table = createTable();
	private List<TableRow> rows = new ArrayList<>();
	private Map<Integer, String> columnWidths = new HashMap<>();
	private Map<Integer, TextAlignType> textAligns = new HashMap<>();
	private final ListConsumer<Block> formatters = new ListConsumer<>();

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
					createColumn(columnWidths.getOrDefault(i,
							"proportional-column-width(1)")));
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
		if (formatters != null)
			formatters.accept(block);
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

	public List<TableCell> getAllCells() {
		return rows.stream().map(TableRow::getTableCell).flatMap(List::stream)
				.collect(toList());
	}

	public FoTableBuilder clearFormatters() {
		formatters.clear();
		return this;
	}

	public FoTableBuilder overrideFormatter(Consumer<Block> newFormatters) {
		formatters.clear();
		formatters.add(newFormatters);
		return this;
	}

	public FoTableBuilder addFormatter(Consumer<Block> formatter) {
		formatters.add(formatter);
		return this;
	}

	public FoTableBuilder addFormatters(List<Consumer<Block>> formatterList) {
		formatterList.forEach(formatters::add);
		return this;
	}
}
