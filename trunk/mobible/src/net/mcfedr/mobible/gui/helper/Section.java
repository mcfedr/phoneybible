package net.mcfedr.mobible.gui.helper;

import de.enough.polish.ui.*;

public interface Section extends Updatable, BibleListenable {
	public void appendTo(Form f);
}
