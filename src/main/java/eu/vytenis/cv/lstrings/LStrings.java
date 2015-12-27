package eu.vytenis.cv.lstrings;

import java.util.List;

import eu.vytenis.cv.TLString;

public class LStrings {
	private final String language;

	public LStrings(String language) {
		this.language = language;
	}

	public <T> String getString(List<TLString> items) {
		new LanguageHolders(language).getOne(items, ls -> ls.getLanguage());
		return items.iterator().next().getValue();
	}
}
