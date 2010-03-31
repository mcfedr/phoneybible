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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class BibleScreen extends List implements Updatable, BibleListenable {

	public static final Command ADD_FAVOURITE_COMMAND = new Command(Locale.get("addfav"), Command.ITEM, 0);

	BibleListener listener;
	Chapter chapter;
	Verse verse;

	public BibleScreen(Verse v) {
		super(null, List.IMPLICIT);
		init(v);
	}

	public BibleScreen(Verse v, Style style) {
		super(null, List.IMPLICIT, style);
		init(v);
	}

	private void init(Verse v) {
		addCommand(ADD_FAVOURITE_COMMAND);
		update(v);
	}

	public void update(Verse v) {
		verse = v;
		if(isShown()) {
			display(verse);
		}
	}
	public void display(Verse v) {
		verse = v;
		if (verse.getChapter() != chapter) {
			chapter = verse.getChapter();
			deleteAll();
			String[] verses = chapter.getVerses();
			for (int i = 0; i < verses.length; i++) {
				//#style chapterlistitem
				ChoiceItem item = new ChoiceItem(verses[i], null,
						Choice.IMPLICIT);
				append(item);
			}
		}
		setTitle(chapter.toString());
		setSelectedIndex(verse.getId(), true);
		container.focusChild(getSelectedIndex());
	}

	public boolean handleKeyPressed(int keyCode, int gameAction) {
		if (gameAction == Canvas.LEFT) {
			if (listener != null)
				listener.gotoPrevChapter();
			return true;
		}
		else if (gameAction == Canvas.RIGHT) {
			if (listener != null)
				listener.gotoNextChapter();
			return true;
		}
		else if (gameAction == Canvas.UP) {
			if (listener != null)
				listener.gotoPrevVerse();
			return true;
		}
		else if (gameAction == Canvas.DOWN) {
			if (listener != null)
				listener.gotoNextVerse();
			return true;
		}
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void showNotify() {
		display(verse);
		super.showNotify();
		setSelectedIndex(verse.getId(), true);
		container.focusChild(getSelectedIndex());
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
		setCommandListener(listener);
	}
}
