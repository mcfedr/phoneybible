package net.mcfedr.mobible.gui.helper;

import net.mcfedr.mobible.bible.*;

import de.enough.polish.ui.Style;
import de.enough.polish.util.Locale;

import javax.microedition.lcdui.*;

public class BookItem extends Button implements Updatable {

	public BookItem() {
		super(Locale.get("book") + ":", "");
	}

	public BookItem(Style style) {
		super(Locale.get("book") + ":", "", style);
	}

	public boolean handleKeyPressed(int keyCode, int gameAction) {
		if (gameAction == Canvas.LEFT) {
			if (listener != null)
				listener.gotoPrevBook();
			return true;
		}
		else if (gameAction == Canvas.RIGHT) {
			if (listener != null)
				listener.gotoNextBook();
			return true;
		}
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void update(Verse v) {
		setText(v.getChapter().getBook().toString());
	}

}
