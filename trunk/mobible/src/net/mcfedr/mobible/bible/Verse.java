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

public class Verse {

	Chapter chapter;
	int id;

	Verse(Chapter c, int i) {
		chapter = c;
		id = i;
	}

	public String toString() {
		return chapter.toString() + ":" + (id + 1);
	}

	public Verse getNextVerse() {
		try {
			return chapter.getVerse(id + 1);
		}
		catch (BibleNotFoundException e) {
			if (chapter.getNextChapter() != chapter) {
				return chapter.getNextChapter().firstVerse();
			}
			else {
				return this;
			}
		}
	}

	public Verse getPrevVerse() {
		try {
			return chapter.getVerse(id - 1);
		}
		catch (BibleNotFoundException e) {
			if (chapter.getPrevChapter() != chapter) {
				return chapter.getPrevChapter().lastVerse();
			}
			else {
				return this;
			}
		}
	}

	public Chapter getChapter() {
		return chapter;
	}

	public int getId() {
		return id;
	}
}
