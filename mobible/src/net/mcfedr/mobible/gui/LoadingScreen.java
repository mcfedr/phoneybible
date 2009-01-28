package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.gui.helper.*;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class LoadingScreen extends Form implements BibleListenable {
	Button loadingItm;
	
	public LoadingScreen() {
		super(Locale.get("mobible"));
		init();
	}
	public LoadingScreen(Style s) {
		super(Locale.get("mobible"), s);
		init();
	}
	private void init() {
		append(getLoadingItm());
	}
	
	public Button getLoadingItm() {
		if(loadingItm == null) {
			//#style about
			loadingItm = new Button("", Locale.get("loading"));
		}
		return loadingItm;
	}
	public void setBibleListener(BibleListener l) {
		getLoadingItm().setBibleListener(l);
	}
}
