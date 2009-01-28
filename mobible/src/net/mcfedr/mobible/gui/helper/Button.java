package net.mcfedr.mobible.gui.helper;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Canvas;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class Button extends StringItem implements BibleListenable {

	protected BibleListener listener;

	public static Command BUTTON_COMMAND = new Command(Locale.get("ok"),
			Command.ITEM, 0);

	public Button(String label, String text) {
		super(label, text, StringItem.BUTTON);
		setDefaultCommand(BUTTON_COMMAND);
	}

	public Button(String label, String text, Style style) {
		super(label, text, StringItem.BUTTON, style);
		setDefaultCommand(BUTTON_COMMAND);
	}

	public boolean handleKeyPressed(int keyCode, int gameAction) {
		if (gameAction == Canvas.FIRE) {
			if (listener != null)
				listener.commandAction(BUTTON_COMMAND, this);
			return true;
		}
		return super.handleKeyPressed(keyCode, gameAction);
	}

	public void setBibleListener(BibleListener l) {
		listener = l;
		setItemCommandListener(l);
	}
}