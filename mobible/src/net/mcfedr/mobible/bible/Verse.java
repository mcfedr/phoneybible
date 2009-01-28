package net.mcfedr.mobible.bible;

public class Verse {

	Chapter chapter;
	int id;

	Verse(Chapter c, int i) {
		chapter = c;
		id = i;
	}

	public String toString() {
		return chapter.toString() + ":" + (id + 1);
	}

	public Verse getNextVerse() {
		try {
			return chapter.getVerse(id + 1);
		}
		catch (BibleNotFoundException e) {
			if (chapter.getNextChapter() != chapter) {
				return chapter.getNextChapter().firstVerse();
			}
			else {
				return this;
			}
		}
	}

	public Verse getPrevVerse() {
		try {
			return chapter.getVerse(id - 1);
		}
		catch (BibleNotFoundException e) {
			if (chapter.getPrevChapter() != chapter) {
				return chapter.getPrevChapter().lastVerse();
			}
			else {
				return this;
			}
		}
	}

	public Chapter getChapter() {
		return chapter;
	}

	public int getId() {
		return id;
	}
}
