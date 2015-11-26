package eu.vytenis.cv.builders;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstBuilderTest {
	@Test
	public void buildsGivenValue() {
		assertEquals("x", new ConstBuilder<String>("x").build());
	}
}
