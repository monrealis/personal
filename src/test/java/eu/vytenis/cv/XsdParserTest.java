package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class XsdParserTest {
	@Test
	public void readsCvXsd() {
		assertNotNull(new XsdParser().parse());
	}
}
