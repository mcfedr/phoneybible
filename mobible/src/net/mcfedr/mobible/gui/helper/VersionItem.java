package net.mcfedr.mobible.gui.helper;

import javax.microedition.lcdui.Canvas;

import de.enough.polish.ui.*;
import net.mcfedr.mobible.bible.Verse;

public class VersionItem  extends Button implements Updatable {

	public VersionItem() {
		super("", "");
	}

	public VersionItem(Style style) {
		super("", "", style);
	}

	public boolean handleKeyPressed(int keyCode, int gameAction) {
		if (gameAction == Canvas.LEFT) {
			if (listener != null)
				listener.gotoPrevVersion();
			return true;
		}
		else if (gameAction == Canvas.RIGHT) {
			if (listener != null)
				listener.gotoNextVersion();
			return true;
		}
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void update(Verse v) {
		setText(v.getChapter().getBook().getBible().toString());
	}
}
