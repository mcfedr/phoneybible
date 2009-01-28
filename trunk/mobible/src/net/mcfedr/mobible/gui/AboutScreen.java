package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.gui.helper.*;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class AboutScreen extends Form implements BibleListenable {
	Button aboutItm;
	
	public AboutScreen() {
		super(Locale.get("mobible"));
		init();
	}
	public AboutScreen(Style s) {
		super(Locale.get("mobible"), s);
		init();
	}
	private void init() {
		append(getAboutItm());
	}
	
	public Button getAboutItm() {
		if(aboutItm == null) {
			//#style about
			aboutItm = new Button("", Locale.get("abouttext"));
		}
		return aboutItm;
	}
	public void setBibleListener(BibleListener l) {
		getAboutItm().setBibleListener(l);
	}
}
