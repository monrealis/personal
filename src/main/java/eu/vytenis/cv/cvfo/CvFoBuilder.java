package eu.vytenis.cv.cvfo;

import java.util.ArrayList;
import java.util.List;

import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.Table;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.fo.FoBuilder;
import eu.vytenis.cv.fo.FoTableBuilder;

public class CvFoBuilder implements Builder<Root> {
	FoBuilder builder = new FoBuilder();

	@Override
	public Root build() {
		Table generalInformation = buildTable();
		Table workExperience = buildTable();
		Table education = buildTable();
		Table personalSkills = buildTable();
		Table additionalInformation = buildTable();
		List<Table> tables = new ArrayList<>();
		tables.add(generalInformation);
		tables.add(workExperience);
		tables.add(education);
		tables.add(personalSkills);
		tables.add(additionalInformation);
		tables.forEach(t -> builder.addContent(t));
		return builder.build();
	}

	private Table buildTable() {
		return new FoTableBuilder().build();
	}

}
