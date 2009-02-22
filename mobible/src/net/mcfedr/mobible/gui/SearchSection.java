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
