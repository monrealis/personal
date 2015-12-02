package eu.vytenis.cv.cvfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.w3._1999.xsl.format.Block;
import org.w3._1999.xsl.format.Root;
import org.w3._1999.xsl.format.Table;
import org.w3._1999.xsl.format.TextAlignType;

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
		workExperience = createTable("Darbo patirtis");
		education = createTable("Išsilavinimas");
		personalSkills = createTable("Asmeniniai gebėjimai");
		additionalInformation = createAdditionalInformation();
		addTables();
		return builder.build();
	}

	private Table createGeneralInformation() {
		FoTableBuilder b = createTableBuilder("Asmeninė informacija");
		b.add("Vardas, pavardė", "Vardenis Pavardenis");
		b.add("Adresas", "Pirma gatvė, Kaunas");
		b.add("Telefonas", "8 000 44444");
		b.add("El. paštas", "vpavardenis@vpavardenis.lt");
		b.add("Pilietybė", "Lietuvos");
		b.add("Gimimo data", "1970-12-31");
		b.add("Lytis", "Vyras");
		return b.build();
	}

	private Table createAdditionalInformation() {
		FoTableBuilder b = new FoTableBuilder().withTextAlignOfFirstColumn(
				TextAlignType.RIGHT).withColumWidth(1, 2);
		b.add("Papildoma informacija", "Mokymai:");
		addBlock(b, "Mokymai1", 1);
		addBlock(b, "Mokymai2", 1);
		addBlock(b, "Mokymai3", 1);
		addBlock(b, "Mokymai4", 1);
		return b.build();
	}

	private void addBlock(FoTableBuilder tableBuilder, String text, int colIndex) {
		addBlock(tableBuilder, text, b -> {
		}, colIndex);
	}

	private void addBlock(FoTableBuilder tableBuilder, String text,
			Consumer<Block> adjuster, int colIndex) {
		Block b = new Block();
		b.getContent().add(text);
		adjuster.accept(b);
		tableBuilder.getCellAt(colIndex).getMarkerOrBlockOrBlockContainer()
				.add(b);
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

	private Table createTable(String header) {
		return createTableBuilder(header).withEmptyRow().build();
	}

	private FoTableBuilder createTableBuilder(String header) {
		FoTableBuilder b = new FoTableBuilder().withEmptyRow()
				.withTextAlignOfFirstColumn(TextAlignType.RIGHT);
		Consumer<Block> adjuster = a -> {
			a.setFontWeight("bold");
			a.setFontSize("12pt");
			a.setTextAlign(TextAlignType.RIGHT);
		};
		addBlock(b, header, adjuster, 0);
		return b.withColumWidth(1, 2);
	}

}
