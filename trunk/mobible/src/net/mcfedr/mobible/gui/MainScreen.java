package net.mcfedr.mobible.gui;

import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.helper.*;

import de.enough.polish.ui.*;
import de.enough.polish.util.Locale;

public class MainScreen extends Form implements Updatable, BibleListenable {
	
	//Search Section
	TextField searchFld;
	Button searchBtn;
	// Goto Section
	StringItem gotoSpc;
	BookItem bookFld;
	ChapterItem chapterFld;
	VerseItem verseFld;
	Button gotoBtn;
	//Options Section
	StringItem optionSpc;
	Button favsBtn;
	VersionItem versionFld;
	Button aboutBtn;
	Button minismiseBtn;
	Button exitBtn;
	
	public MainScreen() {
		super(Locale.get("mobible"));
		init();
	}
	public MainScreen(Style s) {
		super(Locale.get("mobible"), s);
		init();
	}
	private void init() {
		//Search Section
		append(getSearchFld());
		append(getSearchBtn());
		// Goto Section
		append(getGotoSpc());
		append(getBookFld());
		append(getChapterFld());
		append(getVerseFld());
		append(getGoBtn());
		//Option Section
		append(getOptionSpc());
		append(getFavsBtn());
		append(getVersionFld());
		append(getAboutBtn());
		append(getMinismiseBtn());
		append(getExitBtn());
	}
	//Search Section
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
	//Goto Section
	public StringItem getGotoSpc() {
		if (gotoSpc == null) {
			//#style section
			gotoSpc = new StringItem("", Locale.get("goto"));
		}
		return gotoSpc;
	}
	public BookItem getBookFld() {
		if (bookFld == null) {
			//#style field
			bookFld = new BookItem();
		}
		return bookFld;
	}
	public ChapterItem getChapterFld() {
		if (chapterFld == null) {
			//#style field
			chapterFld = new ChapterItem();
		}
		return chapterFld;
	}
	public VerseItem getVerseFld() {
		if (verseFld == null) {
			//#style field
			verseFld = new VerseItem();
		}
		return verseFld;
	}
	public Button getGoBtn() {
		if (gotoBtn == null) {
			//#style button
			gotoBtn = new Button("", Locale.get("goto"));
		}
		return gotoBtn;
	}
	//Options Section
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
	public void setBibleListener(BibleListener l) {
		setItemStateListener(l);
		//Search Section
		getSearchBtn().setBibleListener(l);
		//Goto Section
		getBookFld().setBibleListener(l);
		getChapterFld().setBibleListener(l);
		getVerseFld().setBibleListener(l);
		getGoBtn().setBibleListener(l);
		//Options Section
		getFavsBtn().setBibleListener(l);
		getVersionFld().setBibleListener(l);
		getAboutBtn().setBibleListener(l);
		getMinismiseBtn().setBibleListener(l);
		getExitBtn().setBibleListener(l);
	}
	public void update(Verse v) {
		//SearchSection
		getSearchFld().setText(v.toString());
		//Goto Section
		bookFld.update(v);
		chapterFld.update(v);
		verseFld.update(v);
		//Options Section
		getVersionFld().update(v);
	}
}
