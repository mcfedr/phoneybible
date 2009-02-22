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
