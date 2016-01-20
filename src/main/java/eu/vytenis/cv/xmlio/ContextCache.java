package eu.vytenis.cv.xmlio;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class ContextCache {
	private static Map<List<Class<?>>, JAXBContext> cache = new HashMap<>();

	public JAXBContext getContext(Class<?>[] types) throws JAXBException {
		JAXBContext context = cache.get(asList(types));
		if (context == null) {
			context = JAXBContext.newInstance(types);
			cache.put(asList(types), context);
		}
		return context;
	}
}
