package eu.vytenis.cv.lstrings;

import java.util.List;

import eu.vytenis.cv.TLString;

public class Localizer implements LStrings {
	private final String language;

	public Localizer(String language) {
		this.language = language;
	}

	public String getString(List<TLString> items) {
		new LanguageHolders(language).getOne(items, ls -> ls.getLanguage());
		return items.iterator().next().getValue();
	}
}
