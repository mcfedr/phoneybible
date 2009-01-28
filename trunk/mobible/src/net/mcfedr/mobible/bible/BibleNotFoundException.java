package net.mcfedr.mobible.bible;

public class BibleNotFoundException extends Exception {

	public BibleNotFoundException() {
		super("Bible index out of bounds");
	}

	public BibleNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
