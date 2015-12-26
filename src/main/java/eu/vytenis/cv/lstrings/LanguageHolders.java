package eu.vytenis.cv.lstrings;

import java.util.ArrayList;
import java.util.List;

public class LanguageHolders {
	private String language;

	public <T> T getOne(List<T> items, LanguageMapper<T> mapper) {
		List<T> r = new ArrayList<>();
		for (T o : items) {
			String objectLanguage = mapper.getLanguage(o);
			if (language == null && objectLanguage == null)
				r.add(o);
			if (language != null && language.equals(objectLanguage))
				r.add(o);
		}
		return !r.isEmpty() ? r.iterator().next() : null;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}