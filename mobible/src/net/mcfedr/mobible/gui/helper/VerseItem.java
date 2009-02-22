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

package net.mcfedr.mobible.gui.helper;

import javax.microedition.lcdui.Canvas;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;
import net.mcfedr.mobible.bible.Verse;

public class VerseItem extends TextField implements Updatable, BibleListenable {

	BibleListener listener;

	public VerseItem() {
		super(Locale.get("verse") + ":", "", 3, NUMERIC);
	}

	public VerseItem(Style style) {
		super(Locale.get("verse") + ":", "", 3, NUMERIC, style);
	}

	public boolean handleKeyPressed(int keyCode, int gameAction) {
		if (gameAction == Canvas.LEFT) {
			if (listener != null)
				listener.gotoPrevVerse();
			return true;
		}
		else if (gameAction == Canvas.RIGHT) {
			if (listener != null)
				listener.gotoNextVerse();
			return true;
		}
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void update(Verse v) {
		setText(String.valueOf(v.getId() + 1));
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
	}

}
