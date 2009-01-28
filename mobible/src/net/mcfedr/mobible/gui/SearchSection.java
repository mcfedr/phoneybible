package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.helper.*;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class SearchSection implements Section {

	TextField searchFld;
	Button searchBtn;

	public SearchSection() {
	}

	public void appendTo(Form f) {
		f.append(getSearchFld());
		f.append(getSearchBtn());
	}

	public TextField getSearchFld() {
		if (searchFld == null) {
			//#style field
			searchFld = new TextField("", "", 30, TextField.ANY);
		}
		return searchFld;
	}

	public Button getSearchBtn() {
		if (searchBtn == null) {
			//#style button
			searchBtn = new Button("", Locale.get("search"));
		}
		return searchBtn;
	}

	public void setBibleListener(BibleListener listener) {
		getSearchBtn().setBibleListener(listener);
	}

	public void update(Verse v) {
		getSearchFld().setText(v.toString());
	}
}
