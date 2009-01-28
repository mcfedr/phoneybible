package net.mcfedr.mobible.rms;

import java.io.IOException;

import de.enough.polish.io.RmsStorage;

public class Storage {
	
	private static final String CURRENT_BOOK_KEY = "cbook";
	private static final String CURRENT_CHAPTER_KEY = "cchapter";
	private static final String CURRENT_VERSE_KEY = "cverse";
	private static final String CURRENT_VERSION_KEY = "cversion";
	private static final String FAVOURITES_KEY = "favs";
	private static final String SERIAL_KEY = "serial";
	private static final String EMAIL_KEY = "email";
	public Storage() {
		
	}
	public void setCurrentBook(int book) {
		setInt(book, CURRENT_BOOK_KEY);
	}
	public void setCurrentChaper(int chapter) {
		setInt(chapter, CURRENT_CHAPTER_KEY);
	}
	public void setCurrentVerse(int verse) {
		setInt(verse, CURRENT_VERSE_KEY);
	}
	public int getCurrentBook() {
		return getInt(CURRENT_BOOK_KEY);
	}
	public int getCurrentChapter() {
		return getInt(CURRENT_CHAPTER_KEY);
	}
	public int getCurrentVerse() {
		return getInt(CURRENT_VERSE_KEY);
	}
	public java.util.Vector getFavourites() {
		RmsStorage store = new RmsStorage();
		try {
			return (java.util.Vector)store.read(FAVOURITES_KEY);
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
			return null;
		}
	}
	public void setFavourites(java.util.Vector favs) {
		RmsStorage store = new RmsStorage();
		try {
			store.save(favs, FAVOURITES_KEY);
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
		}
	}
	public String getCurrentVersion() {
		return getString(CURRENT_VERSION_KEY);
	}
	public void setCurrentVersion(String version) {
		setString(version, CURRENT_VERSION_KEY);
	}
	
	public String getSerial() {
		return getString(CURRENT_VERSION_KEY);
	}
	public void setSerial(String serial) {
		setString(serial, SERIAL_KEY);
	}
	public String getEmail() {
		return getString(CURRENT_VERSION_KEY);
	}
	public void setEmail(String email) {
		setString(email, EMAIL_KEY);
	}
	
	private void setInt(int i, String key) {
		RmsStorage store = new RmsStorage();
		try {
			store.save(new Integer(i), key);
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
		}
	}
	private int getInt(String key) {
		RmsStorage store = new RmsStorage();
		try {
			return ((Integer)store.read(key)).intValue();
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
			return 0;
		}
	}
	private void setString(String str, String key) {
		RmsStorage store = new RmsStorage();
		try {
			store.save(str, key);
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
		}
	}
	private String getString(String key) {
		RmsStorage store = new RmsStorage();
		try {
			return ((String)store.read(key));
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
			return null;
		}
	}
}