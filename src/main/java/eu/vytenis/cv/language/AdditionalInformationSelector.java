package eu.vytenis.cv.language;

import java.util.List;

import eu.vytenis.cv.TAdditionalInformationSection;

public interface AdditionalInformationSelector {
	List<TAdditionalInformationSection> getAdditionalInformationSection(
			List<TAdditionalInformationSection> items);
}
