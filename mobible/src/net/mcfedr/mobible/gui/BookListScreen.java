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

package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.helper.*;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class BookListScreen extends List implements BibleListenable, Updatable {

	Bible bible;
	BibleListener listener;

	public BookListScreen(Verse v) {
		super(Locale.get("chooseabook"), List.IMPLICIT);
		init(v);
	}

	public BookListScreen(Verse v, Style style) {
		super(Locale.get("chooseabook"), List.IMPLICIT, style);
		init(v);
	}

	private void init(Verse v) {
		bible = v.getChapter().getBook().getBible();
		String[] books = bible.getBooks();
		for (int i = 0; i < books.length; i++) {
			//#style booklistitem
			append(books[i], null);
		}
		setSelectedIndex(v.getChapter().getBook().getId(), true);
		container.focus(getSelectedIndex());
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
		setCommandListener(l);
	}

	public void update(Verse v) {
		if (v.getChapter().getBook().getBible() != bible) {
			deleteAll();
			init(v);
		}
		setSelectedIndex(v.getChapter().getBook().getId(), true);
		container.focus(getSelectedIndex());
	}

	public Book getSelectedBook() {
		try {
			return bible.getBook(getSelectedIndex());
		}
		catch (BibleNotFoundException e) {
			e.printStackTrace();
			return bible.firstBook();
		}
	}
}
