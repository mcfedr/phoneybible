package net.mcfedr.mohelpers;

import java.io.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

public final class ResourceCopier extends de.enough.polish.resources.ResourceCopier {
	public void copyResources(de.enough.polish.Device device, java.util.Locale locale,
			java.io.File[] resources, java.io.File targetDir) {
		int bibleCount = 0;
		for (File res : resources) {
			System.out.println(res.getAbsolutePath());
			if (res.getName().contains("bible.xml")) {
				processXMLBible(res, targetDir, bibleCount++);
			}
			else if (res.getName().startsWith(".")) {
			}
			else {
				try {
					de.enough.polish.util.FileUtil.copy(res, targetDir);
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	public void processXMLBible(File bible, File targetDir, int versionID) {
		DefaultHandler handler = new XMLBibleHandler(targetDir, versionID);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(bible, handler);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
