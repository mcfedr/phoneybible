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
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public final class XMLBibleHandler extends DefaultHandler {

	File targetDir;
	int versionID;

	String versionName;
	List<String> bookNames;
	int bookI;

	int chapterI;

	int verseI;
	boolean verseGather;
	StringBuffer verseStr;
	List<String> verses;

	public XMLBibleHandler(File t, int v) {
		targetDir = t;
		versionID = v;
	}

	public void startElement(String uri, String localName, String qName, Attributes atts) {
		if (qName.equals("v")) {
			verseGather = true;
			verseStr = new StringBuffer();
		}
		else if (qName.equals("c")) {
			verses = new ArrayList<String>();
			verseI = 0;
		}
		else if (qName.equals("b")) {
			bookNames.add(atts.getValue("n"));
			chapterI = 0;
		}
		else if (qName.equals("bible")) {
			bookNames = new java.util.ArrayList<String>();
			versionName = atts.getValue("version");
			bookI = 0;
		}
	}

	public void characters(char[] ch, int start, int length) {
		if (verseGather) {
			verseStr.append(new String(ch, start, length));
		}
	}

	public void endElement(String uri, String localName, String qName) {
		try {
			if (qName.equals("v")) {
				verseGather = false;
				verses.add(verseStr.toString());
				verseI++;
			}
			else if (qName.equals("c")) {
				File dirFile = new File(targetDir, versionID + "/" + bookI);
				dirFile.mkdirs();
				File dataFile = new File(targetDir, versionID + "/" + bookI + "/" + chapterI);
				dataFile.createNewFile();
				// DATAOUTPUT //
				/*
				DataOutputStream data = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
						dataFile)));
				data.writeInt(verseI);
				for (String v : verses) {
					data.writeUTF(v);
				}
				data.close();
				*/
				// WRITER //
				BufferedWriter data = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(dataFile), java.nio.charset.Charset.forName("UTF-8")));
				data.write(verseI + "\n");
				for(String v : verses) {
					data.write(v + "\n");
				}
				data.close();
				chapterI++;
			}
			else if (qName.equals("b")) {
				File dirFile = new File(targetDir, versionID + "/" + bookI);
				dirFile.mkdirs();
				File dataFile = new File(targetDir, versionID + "/" + bookI + "/chapters");
				dataFile.createNewFile();
				// DATAOUTPUT //
				/*
				DataOutputStream data = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(dataFile)));
				data.writeInt(chapterI);
				data.close();
				*/
				// WRITER //
				BufferedWriter data = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(dataFile), java.nio.charset.Charset.forName("UTF-8")));
				data.write(chapterI + "\n");
				data.close();
				bookI++;
			}
			else if (qName.equals("bible")) {
				File dirFile = new File(targetDir, "" + versionID);
				dirFile.mkdirs();
				File dataFile = new File(targetDir, versionID + "/" + "books");
				dataFile.createNewFile();
				// DATAOUTPUT //
				/*
				DataOutputStream data = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(dataFile)));
				data.writeInt(bookI);
				for(String b : bookNames ) {
					data.writeUTF(b);
				}
				data.close();
				*/
				// WRITER //
				BufferedWriter data = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(dataFile), java.nio.charset.Charset.forName("UTF-8")));
				data.write(bookI + "\n");
				for (String b : bookNames) {
					data.write(b + "\n");
				}
				data.close();

				dataFile = new File(targetDir, "versions");
				String[] versions;
				if (dataFile.exists()) {
					// DATAINPUT //
					/*DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
					int count = reader.readInt();
					versions = new String[count+1];
					for(int i = 0;i<count;i++) {
						versions[i] = reader.readUTF();
					}
					reader.close();*/
					// READER //
					BufferedReader reader = new BufferedReader(new InputStreamReader(
							new FileInputStream(dataFile), java.nio.charset.Charset
									.forName("UTF-8")));
					int count = Integer.parseInt(reader.readLine());
					versions = new String[count + 1];
					for (int i = 0; i < count; i++) {
						versions[i] = reader.readLine();
					}
					reader.close();
				}
				else {
					versions = new String[1];
				}
				versions[versions.length - 1] = versionName;
				// DATAOUTPUT //
				/*
				data = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(dataFile)));
				data.writeInt(versions.length);
				for(String v : versions) {
					data.writeUTF(v);
				}
				data.close();
				*/
				// WRITER //
				data = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile),
						java.nio.charset.Charset.forName("UTF-8")));
				data.write(versions.length + "\n");
				for (String v : versions) {
					data.write(v + "\n");
				}
				data.close();
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
