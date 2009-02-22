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
