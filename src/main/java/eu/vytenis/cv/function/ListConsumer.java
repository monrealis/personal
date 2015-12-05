package eu.vytenis.cv.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class ListConsumer<T> implements Consumer<T> {
	private List<Consumer<T>> consumers = new ArrayList<>();

	public ListConsumer() {
	}

	public ListConsumer(Collection<Consumer<T>> consumers) {
		this.consumers.addAll(consumers);
	}

	@Override
	public void accept(T t) {
		consumers.forEach(c -> c.accept(t));
	}

	public void add(Consumer<T> consumer) {
		consumers.add(consumer);
	}

	public void clear() {
		consumers.clear();
	}
}
