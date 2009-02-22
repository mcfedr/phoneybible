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

import java.util.Vector;

import net.mcfedr.mobible.*;
import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.helper.*;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

import javax.microedition.lcdui.Command;

public class FavouritesScreen extends List implements BibleListenable, Updatable {

	Vector favourites;
	Bible bible;
	BibleListener listener;
	
	public static final Command DELETE_COMMAND = new Command(Locale.get("delete"), Command.ITEM, 0);
	public static final Command CANCEL_COMMAND = new Command(Locale.get("back"), Command.CANCEL, 0);
	
	public FavouritesScreen(Vector favourites, Verse v) {
		super(Locale.get("favorites"), List.IMPLICIT);
		init(favourites, v);
	}

	public FavouritesScreen(Vector favourites, Verse v, Style style) {
		super(Locale.get("favorites"), List.IMPLICIT, style);
		init(favourites, v);
	}

	private void init(Vector f, Verse v) {
		favourites = f;
		bible = v.getChapter().getBook().getBible();
		addCommand(CANCEL_COMMAND);
		addCommand(DELETE_COMMAND);
	}
	
	public void setBibleListener(BibleListener l) {
		listener = l;
		setCommandListener(l);
	}
	public void update(Verse v) {
		if (v.getChapter().getBook().getBible() != bible) {
			bible = v.getChapter().getBook().getBible();
		}
	}
	public void hideNotify() {
		super.hideNotify();
	}
	public void showNotify() {
		deleteAll();
		for(int i = 0;i<favourites.size();i++) {
			try {
				//#style booklistitem
				append(((Favourite)favourites.elementAt(i)).getVerse(bible).toString(), null);
			}
			catch(BibleNotFoundException e) {
				//#style booklistitem
				append(Locale.get("brokenfavorite"), null);
			}
		}
		super.showNotify();
	}
	public Verse getSelectedFavourite() throws BibleNotFoundException {
		return  ((Favourite)favourites.elementAt(getSelectedIndex())).getVerse(bible);
	}
}
