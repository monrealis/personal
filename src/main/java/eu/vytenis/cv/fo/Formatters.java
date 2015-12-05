package eu.vytenis.cv.fo;

import java.util.function.Consumer;

import org.w3._1999.xsl.format.Block;

import eu.vytenis.cv.function.ListConsumer;

public class Formatters {
	private final ListConsumer<Block> formatters;

	public Formatters(ListConsumer<Block> formatters) {
		this.formatters = formatters;
	}

	public Formatters push(Consumer<Block> formatter) {
		formatters.add(formatter);
		return this;
	}

	public Formatters pop() {
		formatters.removeLast();
		return this;
	}
}
