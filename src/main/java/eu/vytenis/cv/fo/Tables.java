package eu.vytenis.cv.fo;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.w3._1999.xsl.format.TableCell;
import org.w3._1999.xsl.format.TableRow;

public class Tables {
	public static List<TableCell> getAllCells(List<TableRow> rows) {
		return rows.stream().map(TableRow::getTableCell).flatMap(List::stream)
				.collect(toList());
	}
}
