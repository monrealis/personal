package eu.vytenis.cv.messages;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class PropertyMessageSource {
	private Map<String, String> messages = new TreeMap<>();

	public PropertyMessageSource(String resource) {
		Properties p = new Properties();
		try {
			p.load(getClass().getResourceAsStream(resource));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		p.entrySet().forEach(
				e -> messages.put(e.getKey().toString(), e.getValue()
						.toString()));
	}

	public String getMessage(MessageCode messageCode) {
		return messages.get(messageCode.getCode());
	}
}
