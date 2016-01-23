package eu.vytenis.cv.xmlio;

public class BaseUnmarshaller<T> {
	private final Class<T> type;
	private final Class<?>[] objectFactoryTypes;

	public BaseUnmarshaller(Class<T> type, Class<?>... objectFactoryTypes) {
		this.type = type;
		this.objectFactoryTypes = objectFactoryTypes;
	}

}
