package net.mcfedr.mobible.gui.helper;

import net.mcfedr.mobible.bible.*;

import javax.microedition.lcdui.Canvas;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class ChapterItem extends TextField implements Updatable,
		BibleListenable {
	BibleListener listener;

	public ChapterItem() {
		super(Locale.get("chapter") + ":", "", 3, NUMERIC);
	}

	public ChapterItem(Style style) {
		super(Locale.get("chapter") + ":", "", 3, NUMERIC, style);
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
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void update(Verse v) {
		setText(String.valueOf(v.getChapter().getId() + 1));
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
	}
}
