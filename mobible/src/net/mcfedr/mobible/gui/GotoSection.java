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

public class GotoSection implements Section {

	StringItem gotoSpc;
	BookItem bookFld;
	ChapterItem chapterFld;
	VerseItem verseFld;
	Button gotoBtn;

	public GotoSection() {
	}

	public void appendTo(Form f) {
		f.append(getGotoSpc());
		f.append(getBookFld());
		f.append(getChapterFld());
		f.append(getVerseFld());
		f.append(getGoBtn());
	}
	public StringItem getGotoSpc() {
		if (gotoSpc == null) {
			//#style section
			gotoSpc = new StringItem("", Locale.get("goto"));
		}
		return gotoSpc;
	}


	public BookItem getBookFld() {
		if (bookFld == null) {
			//#style field
			bookFld = new BookItem();
		}
		return bookFld;
	}

	public ChapterItem getChapterFld() {
		if (chapterFld == null) {
			//#style field
			chapterFld = new ChapterItem();
		}
		return chapterFld;
	}

	public VerseItem getVerseFld() {
		if (verseFld == null) {
			//#style field
			verseFld = new VerseItem();
		}
		return verseFld;
	}

	public Button getGoBtn() {
		if (gotoBtn == null) {
			//#style button
			gotoBtn = new Button("", Locale.get("goto"));
		}
		return gotoBtn;
	}

	public void update(Verse v) {
		bookFld.update(v);
		chapterFld.update(v);
		verseFld.update(v);
	}
	public void setBibleListener(BibleListener l) {
		getBookFld().setBibleListener(l);
		getChapterFld().setBibleListener(l);
		getVerseFld().setBibleListener(l);
		getGoBtn().setBibleListener(l);
	}
}