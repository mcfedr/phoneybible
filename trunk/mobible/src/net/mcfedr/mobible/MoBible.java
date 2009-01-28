package net.mcfedr.mobible;

import net.mcfedr.mobible.bible.*;
import net.mcfedr.mobible.gui.*;
import net.mcfedr.mobible.gui.helper.*;
import net.mcfedr.mobible.rms.*;

import java.util.Vector;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

import de.enough.polish.ui.Item;

public class MoBible extends MIDlet implements BibleListener, Runnable {

	Verse currentVerse;
	DataReader reader;
	MainScreen mainScreen;
	BibleScreen bibleScreen;
	BookListScreen bookListScreen;
	AboutScreen aboutScreen;
	FavouritesScreen favouritesScreen;
	SearchScreen searchScreen;
	LoadingScreen loadingScreen;

	Vector favourites;

	public MoBible() {
	}

	protected void startApp() {
		Display.getDisplay(this).setCurrent(getLoadingScreen());
		Display.getDisplay(this).callSerially(this);
	}
	public void run() {
		loadRMS();
		Display.getDisplay(this).setCurrent(getMainScreen());
	}

	protected void pauseApp() {
		saveRMS();
	}

	protected void destroyApp(boolean unconditional) {
		saveRMS();
		notifyDestroyed();
	}

	public DataReader getReader() {
		if (reader == null) {
			reader = new DataReader();
		}
		return reader;
	}

	public MainScreen getMainScreen() {
		if (mainScreen == null) {
			//#style mainform
			mainScreen = new MainScreen();
			mainScreen.setBibleListener(this);
			mainScreen.update(currentVerse);
		}
		return mainScreen;
	}

	public BibleScreen getBibleScreen() {
		if (bibleScreen == null) {
			//#style chapterlist
			bibleScreen = new BibleScreen(currentVerse);
			bibleScreen.setBibleListener(this);
		}
		return bibleScreen;
	}

	public BookListScreen getBookListScreen() {
		if (bookListScreen == null) {
			//#style booklist
			bookListScreen = new BookListScreen(currentVerse);
			bookListScreen.setBibleListener(this);
		}
		return bookListScreen;
	}

	public AboutScreen getAboutScreen() {
		if (aboutScreen == null) {
			//#style about
			aboutScreen = new AboutScreen();
			aboutScreen.setBibleListener(this);
		}
		return aboutScreen;
	}

	public FavouritesScreen getFavouritesScreen() {
		if (favouritesScreen == null) {
			//#style booklist
			favouritesScreen = new FavouritesScreen(favourites, currentVerse);
			favouritesScreen.setBibleListener(this);
		}
		return favouritesScreen;
	}

	public SearchScreen getSearchScreen() {
		if (searchScreen == null) {
			//#style booklist
			searchScreen = new SearchScreen(currentVerse);
			searchScreen.setBibleListener(this);
		}
		return searchScreen;
	}
	public LoadingScreen getLoadingScreen() {
		if (loadingScreen == null) {
			//#style about
			loadingScreen = new LoadingScreen();
			loadingScreen.setBibleListener(this);
		}
		return loadingScreen;
	}
	public void update() {
		getMainScreen().update(currentVerse);
		getBibleScreen().update(currentVerse);
		getBookListScreen().update(currentVerse);
		getFavouritesScreen().update(currentVerse);
	}

	public void gotoNextBook() {
		currentVerse = currentVerse.getChapter().getBook().getNextBook()
				.firstChapter().firstVerse();
		update();
	}

	public void gotoNextChapter() {
		currentVerse = currentVerse.getChapter().getNextChapter().firstVerse();
		update();
	}

	public void gotoNextVerse() {
		currentVerse = currentVerse.getNextVerse();
		update();
	}

	public void gotoNextVersion() {
		currentVerse = currentVerse.getChapter().getBook().getBible()
				.getNextBible().firstBook().firstChapter().firstVerse();
		update();
	}

	public void gotoPrevBook() {
		currentVerse = currentVerse.getChapter().getBook().getPrevBook()
				.lastChapter().lastVerse();
		update();
	}

	public void gotoPrevChapter() {
		currentVerse = currentVerse.getChapter().getPrevChapter().lastVerse();
		update();
	}

	public void gotoPrevVerse() {
		currentVerse = currentVerse.getPrevVerse();
		update();
	}

	public void gotoPrevVersion() {
		currentVerse = currentVerse.getChapter().getBook().getBible()
				.getPrevBible().firstBook().firstChapter().firstVerse();
		update();
	}

	public void commandAction(Command c, Item i) {
		if (i == getMainScreen().getSearchBtn()) {
			String search = getMainScreen().getSearchFld().getString().toLowerCase().trim().replace(':', ' ');
			try {
				currentVerse = currentVerse.getChapter().getBook().getBible()
						.parseString(search);
				update();
				Display.getDisplay(this).setCurrent(getBibleScreen());
			}
			catch (InvalidBibleStringException e) {
				Display.getDisplay(this).setCurrent(getLoadingScreen());
				if (e.chapter != null) {
					getSearchScreen().init(e.chapter, e.search);
				}
				else if (e.book != null) {
					getSearchScreen().init(e.book, e.search);
				}
				else {
					getSearchScreen().init(
							currentVerse.getChapter().getBook().getBible(),
							search);
				}
				Display.getDisplay(this).setCurrent(getSearchScreen());
			}
			catch (BibleNotFoundException e) {
				//TODO search workings...
				e.printStackTrace();
			}
		}
		else if (i == getMainScreen().getBookFld()) {
			Display.getDisplay(this).setCurrent(getBookListScreen());
		}
		else if (i == getMainScreen().getGoBtn()) {
			Display.getDisplay(this).setCurrent(getBibleScreen());
		}
		else if (i == getMainScreen().getFavsBtn()) {
			Display.getDisplay(this).setCurrent(getFavouritesScreen());
		}
		else if (i == getMainScreen().getAboutBtn()) {
			Display.getDisplay(this).setCurrent(getAboutScreen());
		}
		else if (i == getMainScreen().getMinismiseBtn()) {
			Display.getDisplay(this).setCurrent(null);
			notifyPaused();
		}
		else if (i == getMainScreen().getExitBtn()) {
			destroyApp(false);
		}
		else if (i == getAboutScreen().getAboutItm()) {
			Display.getDisplay(this).setCurrent(getMainScreen());
		}
	}

	public void commandAction(Command c, Displayable d) {
		if (d == bibleScreen && c == BibleScreen.SELECT_COMMAND) {
			Display.getDisplay(this).setCurrent(getMainScreen());
		}
		else if (d == bibleScreen && c == BibleScreen.ADD_FAVOURITE_COMMAND) {
			favourites.addElement(new Favourite(currentVerse));
		}
		else if (d == bookListScreen && c == BookListScreen.SELECT_COMMAND) {
			currentVerse = getBookListScreen().getSelectedBook().firstChapter()
					.firstVerse();
			update();
			Display.getDisplay(this).setCurrent(getMainScreen());
		}
		else if (d == favouritesScreen && c == FavouritesScreen.SELECT_COMMAND) {
			try {
				currentVerse = favouritesScreen.getSelectedFavourite();
				update();
			}
			catch (BibleNotFoundException e) {

			}
			Display.getDisplay(this).setCurrent(getBibleScreen());
		}
		else if (d == favouritesScreen && c == FavouritesScreen.CANCEL_COMMAND) {
			Display.getDisplay(this).setCurrent(getMainScreen());
		}
		else if (d == favouritesScreen && c == FavouritesScreen.DELETE_COMMAND) {
			favourites
					.removeElementAt(getFavouritesScreen().getSelectedIndex());
			getFavouritesScreen().showNotify();
		}
		else if(d == searchScreen && c == SearchScreen.NEXT_COMMAND) {
			Display.getDisplay(this).setCurrent(getLoadingScreen());
			searchScreen.nextPage();
			Display.getDisplay(this).setCurrent(getSearchScreen());
		}
		else if(d == searchScreen && c == SearchScreen.PREV_COMMAND) {
			Display.getDisplay(this).setCurrent(getLoadingScreen());
			searchScreen.prevPage();
			Display.getDisplay(this).setCurrent(getSearchScreen());
		}
		else if(d == searchScreen && c == SearchScreen.CANCEL_COMMAND) {
			Display.getDisplay(this).setCurrent(getMainScreen());
		}
		else if(d == searchScreen && c == SearchScreen.SELECT_COMMAND) {
			currentVerse = searchScreen.getSelectedVerse();
			update();
			Display.getDisplay(this).setCurrent(getBibleScreen());
		}
	}

	public void itemStateChanged(Item i) {
		if (i == getMainScreen().getVerseFld()
				&& !getMainScreen().getVerseFld().getString().equals("")) {
			int num = Integer.parseInt(getMainScreen().getVerseFld()
					.getString()) - 1;
			try {
				currentVerse = currentVerse.getChapter().getVerse(num);
			}
			catch (BibleNotFoundException e) {

			}
			update();
		}
		else if (i == getMainScreen().getChapterFld()
				&& !getMainScreen().getChapterFld().getString().equals("")) {
			int num = Integer.parseInt(getMainScreen().getChapterFld()
					.getString()) - 1;
			try {
				currentVerse = currentVerse.getChapter().getBook().getChapter(
						num).firstVerse();
			}
			catch (BibleNotFoundException e) {

			}
			update();
		}
	}

	private void loadRMS() {
		try {
			Storage rms = new Storage();
			favourites = rms.getFavourites();
			if (favourites == null) {
				favourites = new Vector();
			}
			String version = rms.getCurrentVersion();
			String[] avalible = getReader().getBibles();
			int i = 0;
			while (i < avalible.length) {
				if (avalible[i].equals(version)) {
					break;
				}
				i++;
			}
			Bible bible;
			if (i != avalible.length)
				bible = getReader().getBible(i);
			else
				bible = getReader().getBible(0);

			currentVerse = bible.getBook(rms.getCurrentBook()).getChapter(
					rms.getCurrentChapter()).getVerse(rms.getCurrentVerse());
		}
		catch (BibleNotFoundException e) {
			try {
				currentVerse = getReader().getBible(0).firstBook()
						.firstChapter().firstVerse();
			}
			catch (BibleNotFoundException e2) {
				e2.printStackTrace();
				return;
			}
		}
	}

	private void saveRMS() {
		Storage rms = new Storage();
		rms.setCurrentVerse(currentVerse.getId());
		rms.setCurrentChaper(currentVerse.getChapter().getId());
		rms.setCurrentBook(currentVerse.getChapter().getBook().getId());
		rms.setCurrentVersion(currentVerse.getChapter().getBook().getBible()
				.toString());
		rms.setFavourites(favourites);
	}
}