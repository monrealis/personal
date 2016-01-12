package eu.vytenis.cv.fo;

import java.util.function.Consumer;

import org.w3._1999.xsl.format.Block;

public class FoBlockBuilder {
	public static Block createBlock(String text) {
		Block b = new Block();
		b.getContent().add(text);
		return b;
	}

	public static Block createBlock(String text, Consumer<Block> adjuster) {
		Block b = new Block();
		b.getContent().add(text);
		adjuster.accept(b);
		return b;
	}

	public static Consumer<Block> nullAdjuster() {
		return b -> {
		};
	}
}
