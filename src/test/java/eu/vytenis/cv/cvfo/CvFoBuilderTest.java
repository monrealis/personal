package eu.vytenis.cv.cvfo;

import org.junit.Test;

import eu.vytenis.cv.fop.FopExecutor;

public class CvFoBuilderTest {
	@Test
	public void buildsCv() {
		new FopExecutor(new CvFoBuilder(), true).run();
	}

}
