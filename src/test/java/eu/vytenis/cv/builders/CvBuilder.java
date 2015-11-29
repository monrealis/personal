package eu.vytenis.cv.builders;

import eu.vytenis.cv.CV;

public class CvBuilder {
	private CV cv;

	public CV build() {
		cv = new CV();
		return cv;
	}
}
