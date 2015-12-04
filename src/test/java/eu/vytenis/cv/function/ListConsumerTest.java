package eu.vytenis.cv.function;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import org.junit.Test;

public class ListConsumerTest implements Consumer<String> {
	private String values = "";
	private ListConsumer<String> listConsumer = new ListConsumer<>();

	@Test
	public void acceptsWithNoConsumers() {
		acceptTwice();
		assertEquals("", values);
	}

	@Test
	public void acceptsWithClearedConsumers() {
		addThisTwice();
		listConsumer.clear();
		acceptTwice();
		assertEquals("", values);
	}

	@Test
	public void acceptsWithFilledAndConsumers() {
		addThisTwice();
		acceptTwice();
		assertEquals("XXYY", values);
	}

	private void addThisTwice() {
		listConsumer.add(this);
		listConsumer.add(this);
	}

	private void acceptTwice() {
		listConsumer.accept("X");
		listConsumer.accept("Y");
	}

	@Override
	public void accept(String value) {
		this.values += value;
	}

}
