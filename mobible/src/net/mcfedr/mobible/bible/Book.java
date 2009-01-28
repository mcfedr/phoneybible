package net.mcfedr.mobible.bible;

import java.io.*;

public class Book {

	private static final String CHAPTERS_FILE = "chapters";
	int id;
	Bible bible;

	int length = 0;

	Book(Bible b, int i) {
		id = i;
		bible = b;
	}

	/*
	 * Converts a String into a Verse 
	 * Strings in the format:
	 * 2 2
	 * 2
	 */
	public Verse parseString(String str) throws InvalidBibleStringException, BibleNotFoundException {
		int x = str.indexOf(' ');
		try {
			if (x == -1) {
				return getChapter(Integer.parseInt(str) - 1).firstVerse();

			}
			else {
				return getChapter(Integer.parseInt(str.substring(0, x)) - 1)
						.parseString(str.substring(x + 1));
			}
		}
		catch (NumberFormatException e) {
			throw new InvalidBibleStringException(this, str);
		}
	}

	public int getLength() {
		if (length == 0) {
			loadLength();
		}
		return length;
	}

	public Chapter getChapter(int i) throws BibleNotFoundException {
		if (i < getLength() && i >= 0) {
			return new Chapter(this, i);
		}
		else {
			throw new BibleNotFoundException();
		}
	}

	public Chapter firstChapter() {
		return new Chapter(this, 0);
	}

	public Chapter lastChapter() {
		return new Chapter(this, getLength() - 1);
	}

	public String toString() {
		return bible.getBooks()[id];
	}

	public Book getNextBook() {
		try {
			return bible.getBook(id + 1);
		}
		catch (BibleNotFoundException e) {
			return this;
		}
	}

	public Book getPrevBook() {
		try {
			return bible.getBook(id - 1);
		}
		catch (BibleNotFoundException e) {
			return this;
		}
	}

	public int getId() {
		return id;
	}

	private void loadLength() {
		try {
			// DATAINPUT //
			/*
			 * DataInputStream reader = new
			 * DataInputStream(getClass().getResourceAsStream( "/" + bible.id +
			 * "/" + id + "/" + CHAPTERS_FILE)); length = reader.readInt();
			 * reader.close();
			 */
			// READER //
			String file = DataReader.readFile("/" + bible.id + "/" + id + "/"
					+ CHAPTERS_FILE, this);
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

	public Bible getBible() {
		return bible;
	}
}
