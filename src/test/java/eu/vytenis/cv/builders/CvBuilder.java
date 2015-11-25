package eu.vytenis.cv.builders;

import org.example.cv.CV;

public class CvBuilder {
	private CV cv;

	public CV build() {
		cv = new CV();
		return cv;
	}
}
