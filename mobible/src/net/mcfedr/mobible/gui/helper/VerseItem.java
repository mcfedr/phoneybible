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
