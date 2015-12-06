package eu.vytenis.cv.fo;

import java.util.function.Consumer;

import eu.vytenis.cv.function.ListConsumer;

public class Formatters<T> {
	private final ListConsumer<T> formatters;

	public Formatters(ListConsumer<T> formatters) {
		this.formatters = formatters;
	}

	public Formatters<T> push(Consumer<T> formatter) {
		formatters.add(formatter);
		return this;
	}

	public Formatters<T> pop() {
		formatters.removeLast();
		return this;
	}
}
