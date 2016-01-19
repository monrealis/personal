package eu.vytenis.cv.xsd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CvXsdParserTest {
	@Test
	public void readsCvXsd() {
		assertNotNull(new CvXsdParser().parse());
	}
}
