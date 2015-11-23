package eu.vytenis.cv;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CvBuilderTest {
	@Test
	public void buildsNotNull() {
		assertNotNull(new CvBuilder().build());
	}
}
