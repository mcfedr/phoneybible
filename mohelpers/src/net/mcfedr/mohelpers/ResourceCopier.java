/*
Copyright (c) 2009 Fred Nicollson, All Rights Reserved.

This file is part of PhoneyBible.

PhoneyBible is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PhoneyBible is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with phoneybible.  If not, see <http://www.gnu.org/licenses/>.
*/

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
