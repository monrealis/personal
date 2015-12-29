package eu.vytenis.cv.language;

import java.util.List;

import eu.vytenis.cv.TLanguageLabels;

public interface LanguageLabelsSelector {
	TLanguageLabels getLanguageLabels(List<TLanguageLabels> items);
}
