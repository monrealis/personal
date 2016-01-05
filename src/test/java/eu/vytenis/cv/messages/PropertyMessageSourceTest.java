package eu.vytenis.cv.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PropertyMessageSourceTest {
	@Test
	public void nullForMissing() {
		assertNull(get("missing"));
	}

	@Test
	public void valueFromFile() {
		assertEquals("value1", get("key1"));
	}

	private String get(String key) {
		return new PropertyMessageSource("test.properties")
				.getMessage(() -> key);
	}

}
