package eu.vytenis.cv.builders;

public class ConstBuilder<T> implements Builder<T> {
	private final T object;

	public ConstBuilder(T object) {
		this.object = object;
	}

	@Override
	public T build() {
		return object;
	}
}
