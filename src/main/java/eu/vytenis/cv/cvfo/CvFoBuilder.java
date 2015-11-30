package eu.vytenis.cv.cvfo;

import java.util.ArrayList;
import java.util.List;

import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.Table;

import eu.vytenis.cv.builders.Builder;
import eu.vytenis.cv.fo.FoBuilder;
import eu.vytenis.cv.fo.FoTableBuilder;

public class CvFoBuilder implements Builder<Root> {
	private Table generalInformation;
	private Table workExperience;
	private Table education;
	private Table personalSkills;
	private Table additionalInformation;
	private FoBuilder builder = new FoBuilder();

	@Override
	public Root build() {
		generalInformation = createGeneralInformation();
		workExperience = createTable();
		education = createTable();
		personalSkills = createTable();
		additionalInformation = createTable();
		addTables();
		return builder.build();
	}

	private Table createGeneralInformation() {
		FoTableBuilder b = new FoTableBuilder();
		b.add("Vardas, pavardė", "Vardenis Pavardenis");
		b.add("Adresas", "Pirma gatvė, Kaunas");
		b.add("Telefonas", "8 000 44444");
		b.add("El. paštas", "vpavardenis@vpavardenis.lt");
		b.add("Pilietybė", "Lietuvos");
		b.add("Gimimo data", "1970-12-31");
		b.add("Lytis", "Vyras");
		return b.build();
	}

	private void addTables() {
		buildTables().forEach(t -> builder.addContent(t));
	}

	private List<Table> buildTables() {
		List<Table> tables = new ArrayList<>();
		tables.add(generalInformation);
		tables.add(workExperience);
		tables.add(education);
		tables.add(personalSkills);
		tables.add(additionalInformation);
		return tables;
	}

	private Table createTable() {
		return new FoTableBuilder().withEmptyRow().build();
	}

}
