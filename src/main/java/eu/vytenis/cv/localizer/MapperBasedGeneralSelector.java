package eu.vytenis.cv.localizer;

import java.util.ArrayList;
import java.util.List;

class MapperBasedGeneralSelector {
	private String language;
	private boolean withFallback;

	public MapperBasedGeneralSelector(String language, boolean withFallback) {
		this.language = language;
		this.withFallback = withFallback;
	}

	public <T> T getOne(List<T> items, LanguageMapper<T> mapper) {
		List<T> r = getMany(items, mapper);
		return !r.isEmpty() ? r.iterator().next() : null;
	}

	public <T> List<T> getMany(List<T> items, LanguageMapper<T> mapper) {
		List<T> r = new ArrayList<>();
		for (T o : items) {
			String objectLanguage = mapper.getLanguage(o);
			if (language == null && objectLanguage == null)
				r.add(o);
			if (language != null && language.equals(objectLanguage))
				r.add(o);
		}
		return r;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
