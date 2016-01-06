package eu.vytenis.cv.messages;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

public class PropertyMessageSource {
	private Map<String, String> messages = new TreeMap<>();

	public PropertyMessageSource(String resource) {
		loadEntries(resource).forEach(this::put);
	}

	private void put(Entry<Object, Object> entry) {
		String key = entry.getKey().toString();
		String value = entry.getValue().toString();
		messages.put(key, value);
	}

	private Set<Entry<Object, Object>> loadEntries(String resource) {
		return load(resource).entrySet();
	}

	private Properties load(String resource) {
		try {
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream(resource));
			return p;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getMessage(MessageCode messageCode) {
		return messages.get(messageCode.getCode());
	}
}
