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

import net.mcfedr.mobible.bible.Verse;
import net.mcfedr.mobible.gui.helper.*;
import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class OptionsSection implements Section {

	StringItem optionSpc;
	Button favsBtn;
	VersionItem versionFld;
	Button aboutBtn;
	Button minismiseBtn;
	Button exitBtn;

	public OptionsSection() {
	}

	public void appendTo(Form f) {
		f.append(getOptionSpc());
		f.append(getFavsBtn());
		f.append(getVersionFld());
		f.append(getAboutBtn());
		f.append(getMinismiseBtn());
		f.append(getExitBtn());
	}

	public StringItem getOptionSpc() {
		if (optionSpc == null) {
			//#style section
			optionSpc = new StringItem("", Locale.get("more"));
		}
		return optionSpc;
	}

	public Button getFavsBtn() {
		if (favsBtn == null) {
			//#style hyperlink
			favsBtn = new Button("", Locale.get("favorites"));
		}
		return favsBtn;
	}

	public VersionItem getVersionFld() {
		if (versionFld == null) {
			//#style field
			versionFld = new VersionItem();
		}
		return versionFld;
	}

	public Button getAboutBtn() {
		if (aboutBtn == null) {
			//#style hyperlink
			aboutBtn = new Button("", Locale.get("about"));
		}
		return aboutBtn;
	}

	public Button getMinismiseBtn() {
		if (minismiseBtn == null) {
			//#style hyperlink
			minismiseBtn = new Button("", Locale.get("min"));
		}
		return minismiseBtn;
	}

	public Button getExitBtn() {
		if (exitBtn == null) {
			//#style hyperlink
			exitBtn = new Button("", Locale.get("exit"));
		}
		return exitBtn;
	}

	public void update(Verse v) {
		getVersionFld().update(v);
	}

	public void setBibleListener(BibleListener l) {
		getFavsBtn().setBibleListener(l);
		getVersionFld().setBibleListener(l);
		getAboutBtn().setBibleListener(l);
		getMinismiseBtn().setBibleListener(l);
		getExitBtn().setBibleListener(l);
	}
}
