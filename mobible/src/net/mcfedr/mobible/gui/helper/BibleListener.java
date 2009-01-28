package net.mcfedr.mobible.gui.helper;

import de.enough.polish.ui.*;
import javax.microedition.lcdui.CommandListener;

public interface BibleListener extends CommandListener, ItemCommandListener, ItemStateListener {
	public void gotoNextVerse();

	public void gotoPrevVerse();

	public void gotoNextChapter();

	public void gotoPrevChapter();

	public void gotoNextBook();

	public void gotoPrevBook();
	
	public void gotoNextVersion();

	public void gotoPrevVersion();
}
