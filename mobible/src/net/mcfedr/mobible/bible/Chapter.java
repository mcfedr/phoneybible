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

package net.mcfedr.mobible.bible;

import java.io.*;

public class Chapter {

	Book book;
	int id;
	int length = 0;

	Chapter(Book b, int i) {
		book = b;
		id = i;
	}
	/*
	 * Converts a String into a Verse
	 * Strings in the format:
	 * 1
	 */
	public Verse parseString(String str) throws InvalidBibleStringException, BibleNotFoundException {
		try {
			return getVerse(Integer.parseInt(str) - 1);
		}
		catch(NumberFormatException e) {
			throw new InvalidBibleStringException(this, str);
		}
	}
	public int getLength() {
		if (length == 0) {
			loadLength();
		}
		return length;
	}

	public Verse getVerse(int i) throws BibleNotFoundException {
		if (i < getLength() && i >= 0) {
			return new Verse(this, i);
		}
		else {
			throw new BibleNotFoundException();
		}
	}

	public Verse firstVerse() {
		return new Verse(this, 0);
	}

	public Verse lastVerse() {
		return new Verse(this, getLength() - 1);
	}

	public String[] getVerses() {
		try {
			// DATAINPUT //
			/*
			DataInputStream reader = new DataInputStream(getClass().getResourceAsStream(
					"/" + book.bible.id + "/" + book.id + "/" + id));
			length = reader.readInt();
			String[] verses = new String[length];
			for (int i = 0; i < length; i++) {
				verses[i] = (i+1) + ". " + reader.readUTF();
			}
			reader.close();
			*/
			// READER //
			String file = DataReader.readFile("/" + book.bible.id + "/" + book.id + "/" + id, this);
			int pos = file.indexOf('\n');
			length = Integer.parseInt(file.substring(0, pos));
			String[] verses = new String[length];
			for (int i = 0; i < length; i++) {
				int old = pos + 1;
				pos = file.indexOf('\n', old);
				verses[i] = (i+1) + ". " + file.substring(old, pos);
			}
			return verses;
		}
		catch (IOException ioe) {
			// #ifdef trial.version
			ioe.printStackTrace();
			// #endif
			return null;
		}
	}

	public String toString() {
		return book.toString() + " " + (id + 1);
	}

	public Chapter getNextChapter() {
		try {
			return book.getChapter(id + 1);
		}
		catch (BibleNotFoundException e) {
			if (book.getNextBook() != book) {
				return book.getNextBook().firstChapter();
			}
			else {
				return this;
			}
		}
	}

	public Chapter getPrevChapter() {
		try {
			return book.getChapter(id - 1);
		}
		catch (BibleNotFoundException e) {
			if (book.getPrevBook() != book) {
				return book.lastChapter();
			}
			else {
				return this;
			}
		}
	}

	public Book getBook() {
		return book;
	}

	public int getId() {
		return id;
	}

	private void loadLength() {
		try {
			// DATAINPUT //
			/*
			DataInputStream reader = new DataInputStream(getClass().getResourceAsStream(
					"/" + book.bible.id + "/" + book.id + "/" + id));
			length = reader.readInt();
			reader.close();
			*/
			// READER //
			String file = DataReader.readFile("/" + book.bible.id + "/" + book.id + "/" + id, this);
			int pos = file.indexOf('\n');
			length = Integer.parseInt(file.substring(0, pos));
		}
		catch (IOException ioe) {
			// #ifdef trial.version
			ioe.printStackTrace();
			// #endif
			length = 0;
		}
	}
}
