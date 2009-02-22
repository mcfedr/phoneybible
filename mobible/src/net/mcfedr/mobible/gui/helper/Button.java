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