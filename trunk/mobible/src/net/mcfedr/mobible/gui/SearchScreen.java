package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.helper.*;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

import java.util.Vector;
import javax.microedition.lcdui.Command;

public class SearchScreen extends List implements BibleListenable {

	Bible bible;
	BibleListener listener;

	String search;
	int limit;
	Verse start;
	Vector finds;
	Vector quotes;
	int page;
	boolean morePages;

	private static final int NO_LIMIT = 0;
	private static final int BOOK_LIMIT = 1;
	private static final int CHAPTER_LIMIT = 2;
	private static final int QUOTE_LENGTH = 25;

	public static final Command NEXT_COMMAND = new Command(Locale.get("next"), Command.ITEM, 0);
	public static final Command PREV_COMMAND = new Command(Locale.get("prev"), Command.ITEM, 0);
	public static final Command CANCEL_COMMAND = new Command(Locale.get("back"), Command.CANCEL, 0);

	public SearchScreen(Verse v) {
		super(Locale.get("results"), List.IMPLICIT);
		init(v);
	}

	public SearchScreen(Verse v, Style style) {
		super(Locale.get("results"), List.IMPLICIT, style);
		init(v);
	}

	private void init(Verse v) {
		bible = v.getChapter().getBook().getBible();
		addCommand(CANCEL_COMMAND);
		addCommand(NEXT_COMMAND);
		addCommand(PREV_COMMAND);
	}

	public void init(Bible b, String str) {
		search = str;
		limit = NO_LIMIT;
		bible = b;
		start = b.firstBook().firstChapter().firstVerse();
		finds = new Vector();
		quotes = new Vector();
		morePages = true;
		setPage(0);
	}

	public void init(Book b, String str) {
		search = str;
		limit = BOOK_LIMIT;
		bible = b.getBible();
		start = b.firstChapter().firstVerse();
		finds = new Vector();
		quotes = new Vector();
		morePages = true;
		setPage(0);
	}

	public void init(Chapter c, String str) {
		search = str;
		limit = CHAPTER_LIMIT;
		bible = c.getBook().getBible();
		start = c.firstVerse();
		finds = new Vector();
		quotes = new Vector();
		morePages = true;
		setPage(0);
	}

	public void nextPage() {
		if(morePages) {
		 setPage(page + 1);
		}
	}

	public void prevPage() {
		morePages = true;
		setPage(page - 1 > 0 ? page - 1 : 0);
	}

	public Verse getSelectedVerse() {
		return (Verse) finds.elementAt(getSelectedIndex() + page * 10);
	}

	private void setPage(int p) {
		page = p;
		fillup();
		deleteAll();
		for (int i = page * 10; i < (page + 1) * 10 && i < finds.size(); i++) {
			//#style booklistitem
			append(((Verse) finds.elementAt(i)).toString() + " - " + (String) quotes.elementAt(i), null);
		}
	}

	private void fillup() {
		if (finds.size() < (page + 1) * 10) {
			Verse prev = finds.size() > 0 ? ((Verse) finds.elementAt(size() - 1)).getNextVerse() : start;
			Chapter loadedChapter = null;
			String[] chapter = null;
			for (int i = 0; i < 10;) {
				if (!inLimit(prev)) {
					morePages = false;
					break;
				}
				if (loadedChapter != prev.getChapter()) {
					loadedChapter = prev.getChapter();
					chapter = loadedChapter.getVerses();
				}
				int pos = chapter[prev.getId()].toLowerCase().indexOf(search);
				//System.out.println("searching for " + search + " in " + prev.toString() + " - " + chapter[prev.getId()].toLowerCase());
				if (pos != -1) {
					finds.addElement(prev);
					quotes.addElement(makeQuote(chapter[prev.getId()], pos));
					i++;
				}
				if (prev.getNextVerse() != prev)
					prev = prev.getNextVerse();
				else {
					morePages = false;
					break;
				}

			}
		}
	}

	private boolean inLimit(Verse v) {
		switch (limit) {
		case NO_LIMIT:
			return true;
		case BOOK_LIMIT:
			return v.getChapter().getBook() == start.getChapter().getBook();
		case CHAPTER_LIMIT:
			return v.getChapter() == start.getChapter();
		default:
			return false;
		}
	}

	private String makeQuote(String str, int pos) {
		String quote;
		if (pos > QUOTE_LENGTH) {
			quote = "...";
			int space = search.lastIndexOf(' ', pos - QUOTE_LENGTH);
			if (space != -1 && pos - space < 10) {
				quote += str.substring(space, pos + search.length());
			}
			else {
				quote += str.substring(pos - QUOTE_LENGTH, pos + search.length());
			}

		}
		else {
			quote = str.substring(0, pos + search.length());
		}
		if (str.length() - pos - search.length() > QUOTE_LENGTH) {
			int space = search.indexOf(' ', pos + search.length() + QUOTE_LENGTH);
			if (space != -1 && space - pos - search.length() < 10) {
				quote += str.substring(pos + search.length(), space);
			}
			else {
				quote += str.substring(pos + search.length(), pos + search.length() + QUOTE_LENGTH);
			}
			quote += "...";
		}
		else {
			quote += str.substring(pos + search.length());
		}
		return quote;
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
		setCommandListener(l);
	}
}
