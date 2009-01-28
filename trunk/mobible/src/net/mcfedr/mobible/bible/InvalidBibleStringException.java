package net.mcfedr.mobible.bible;

public class InvalidBibleStringException extends Exception {

	public Book book;
	public Chapter chapter;
	public String search;

	public InvalidBibleStringException() {
		super("Invalid Bible String");
	}
	public InvalidBibleStringException(Book b, String s) {
		this();
		book = b;
		search = s;
	}
	public InvalidBibleStringException(Chapter c, String s) {
		this();
		chapter = c;
		search = s;
	}
}
