package eu.vytenis.cv.desktop;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileOpener {
	private final boolean doOpen;
	private final File file;

	public FileOpener(File file, boolean doOpen) {
		this.doOpen = doOpen;
		this.file = file;
	}

	public void open() {
		if (!doOpen)
			return;
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
