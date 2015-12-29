package eu.vytenis.cv.localizer;

import java.util.List;

import eu.vytenis.cv.TLString;
import eu.vytenis.cv.TLanguageLabels;
import eu.vytenis.cv.language.XmlElementSelector;

public class MapperBasedSelector implements XmlElementSelector {
	private final MapperBasedGeneralSelector holders;

	public MapperBasedSelector(String language) {
		holders = new MapperBasedGeneralSelector(language);
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
