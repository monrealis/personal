package eu.vytenis.cv.lstrings;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.cv.TLString;
import eu.vytenis.cv.localizer.Localizer;

public class LocalizerTest {
	private TLString string = new TLString();
	private String language = null;

	@Before
	public void before() {
		string.setValue("X");
	}

	@Test
	public void returnsForNullLangauge() {
		assertEquals("X", getString());
	}

	@Test
	public void returnsForOtherLanguage() {
		string.setLanguage(language = "lt");
		assertEquals("X", getString());
	}

	@Test
	public void doesNotReturnIfNotFound() {
		string.setLanguage("lt");
		assertEquals("X", getString());
	}

	private String getString() {
		return new Localizer(language).getString(asList(string));
	}
}