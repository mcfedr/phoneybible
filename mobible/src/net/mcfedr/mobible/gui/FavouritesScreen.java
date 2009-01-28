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
