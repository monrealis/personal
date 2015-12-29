package eu.vytenis.cv.localizer;

import java.util.List;

import eu.vytenis.cv.TLString;
import eu.vytenis.cv.TLanguageLabels;
import eu.vytenis.cv.language.LStrings;
import eu.vytenis.cv.language.LanguageLabels;

public class Localizer implements LStrings, LanguageLabels {
	private final LanguageHolders holders;

	public Localizer(String language) {
		holders = new LanguageHolders(language);
	}

	@Override
	public String getString(List<TLString> items) {
		holders.getOne(items, ls -> ls.getLanguage());
		return items.iterator().next().getValue();
	}

	@Override
	public TLanguageLabels getLanguageLabels(List<TLanguageLabels> items) {
		return holders.getOne(items, ll -> ll.getLanguage());
	}
}
