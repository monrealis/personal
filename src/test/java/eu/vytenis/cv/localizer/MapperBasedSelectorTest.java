package eu.vytenis.cv.localizer;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.cv.TAdditionalInformationSection;
import eu.vytenis.cv.TLString;

public class MapperBasedSelectorTest {
	private TLString string = new TLString();
	private TAdditionalInformationSection section1 = new TAdditionalInformationSection();
	private TAdditionalInformationSection section2 = new TAdditionalInformationSection();
	private List<TAdditionalInformationSection> sections = new ArrayList<>(
			asList(section1, section2));
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
		return create().getString(asList(string));
	}

	@Test
	public void getSections_returnsForNullLanguage() {
		assertEquals(asList(section1, section2), getSections());
	}

	@Test
	public void getSections_doesNotReturnIfNotFound() {
		language = "lt";
		assertEquals(emptyList(), getSections());
	}

	@Test
	public void getSections_returnsByLanguage() {
		language = "lt";
		section2.setLanguage(language);
		assertEquals(asList(section2), getSections());
	}

	private List<TAdditionalInformationSection> getSections() {
		return create().getAdditionalInformationSection(sections);
	}

	private MapperBasedSelector create() {
		return new MapperBasedSelector(language, false);
	}
}
