package net.mcfedr.mobible;

import de.enough.polish.io.Serializable;
import net.mcfedr.mobible.bible.*;

public class Favourite implements Serializable {
	int verse;
	int chapter;
	int book;
	
	public Favourite(int v, int c, int b) {
		verse = v;
		chapter = c;
		book = b;
	}
	public Favourite(Verse v) {
		verse = v.getId();
		chapter = v.getChapter().getId();
		book = v.getChapter().getBook().getId();
	}
	
	public int getVerse() {
		return verse;
	}
	public int getChapter() {
		return chapter;
	}
	public int getBook() {
		return book;
	}
	public Verse getVerse(Bible b) throws BibleNotFoundException {
		return b.getBook(book).getChapter(chapter).getVerse(verse);
	}
}
