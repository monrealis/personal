package eu.vytenis.cv.localizer;

import java.util.List;

import eu.vytenis.cv.TAdditionalInformationSection;
import eu.vytenis.cv.TLString;
import eu.vytenis.cv.TLanguageLabels;
import eu.vytenis.cv.language.XmlElementSelector;

public class MapperBasedSelector implements XmlElementSelector {
	private final MapperBasedGeneralSelector holders;

	public MapperBasedSelector(String language, boolean withFallback) {
		holders = new MapperBasedGeneralSelector(language, withFallback);
	}

	@Override
	public String getString(List<TLString> items) {
		TLString r = holders.getOne(items, ls -> ls.getLanguage());
		if (r == null)
			return null;
		return r.getValue();
	}

	@Override
	public TLanguageLabels getLanguageLabels(List<TLanguageLabels> items) {
		return holders.getOne(items, ll -> ll.getLanguage());
	}

	@Override
	public List<TAdditionalInformationSection> getAdditionalInformationSection(
			List<TAdditionalInformationSection> items) {
		return holders.getMany(items, ai -> ai.getLanguage());
	}
}
