package eu.vytenis.cv.lstrings;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LanguageHoldersTest {
	private Object o1 = new Object();
	private Object o2 = new Object();
	private List<Object> objects = asList(o1, o2);
	private LanguageHolders holders = new LanguageHolders();
	private Map<Object, String> languages = new HashMap<>();
	private LanguageMapper<Object> mapper = o -> languages.get(o);

	@Before
	public void before() {
		languages.put(o2, "lt");
	}

	@Test
	public void withoutLanguage() {
		assertEquals(o1, holders.getOne(objects, mapper));
	}

	@Test
	public void withCorrectLanguage() {
		holders.setLanguage("lt");
		assertEquals(o2, holders.getOne(objects, mapper));
	}

	@Test
	public void withIncorrectLanguage() {
		holders.setLanguage("invalid");
		assertNull(holders.getOne(objects, mapper));
	}
}
