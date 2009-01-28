package net.mcfedr.mobible.bible;

import java.io.*;

public class Bible {
	private static final String BOOKS_FILE = "books";

	int id;
	String[] books;
	String name;
	DataReader reader;

	Bible(DataReader r, int i, String n) {
		reader = r;
		id = i;
		name = n;
	}

	/*
	 * Converts a String into a Verse
	 * Strings in the format:
	 * 1 Peter 2:2
	 * 1 Peter 2 2
	 * 1 Peter 2
	 * 1 Peter
	 */
	public Verse parseString(String str) throws InvalidBibleStringException,
			BibleNotFoundException {
		String[] books = getBooks();
		int i = 0;
		boolean found = false;
		do {
			if (str.startsWith(books[i].toLowerCase())) {
				found = true;
			}
			else {
				i++;
			}
		}
		while (!found && i < books.length);
		if (!found)
			throw new InvalidBibleStringException();
		else if (str.length() > books[i].length())
			return getBook(i).parseString(str.substring(books[i].length() + 1));
		else
			return getBook(i).firstChapter().firstVerse();
	}

	public String[] getBooks() {
		if (books == null) {
			loadBooks();
		}
		return books;
	}

	public Book getBook(int i) throws BibleNotFoundException {
		if (i < getBooks().length && i >= 0) {
			return new Book(this, i);
		}
		else {
			throw new BibleNotFoundException();
		}
	}

	public Book firstBook() {
		return new Book(this, 0);
	}

	public Book lastBook() {
		return new Book(this, getBooks().length - 1);
	}

	public Bible getPrevBible() {
		try {
			return reader.getBible(id - 1);
		}
		catch (BibleNotFoundException e) {
			return this;
		}
	}

	public Bible getNextBible() {
		try {
			return reader.getBible(id + 1);
		}
		catch (BibleNotFoundException e) {
			return this;
		}
	}

	public String toString() {
		return name;
	}

	private void loadBooks() {
		try {
			// DATAINPUT //
			/*
			DataInputStream reader = new DataInputStream(getClass().getResourceAsStream(
					"/" + id + "/" + BOOKS_FILE));
			int num = reader.readInt();
			books = new String[num];
			for (int i = 0; i < num; i++) {
				books[i] = reader.readUTF();
			}
			reader.close();
			*/
			// READER //
			String file = DataReader
					.readFile("/" + id + "/" + BOOKS_FILE, this);
			int pos = file.indexOf('\n');
			int num = Integer.parseInt(file.substring(0, pos));
			books = new String[num];
			for (int i = 0; i < num; i++) {
				int old = pos + 1;
				pos = file.indexOf('\n', old);
				books[i] = file.substring(old, pos);
			}
		}
		catch (IOException ioe) {
			// #ifdef trial.version
			ioe.printStackTrace();
			// #endif
			return;
		}
	}
}
