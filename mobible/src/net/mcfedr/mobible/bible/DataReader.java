package net.mcfedr.mobible.bible;

import java.io.*;

public class DataReader {
	
	private static final String BIBLES_FILE = "versions";
	
	String[] bibles;

	public String[] getBibles() {
		if(bibles == null) {
			loadBibles();
		}
		return bibles;
	}
	public Bible getBible(int i) throws BibleNotFoundException {
		if(i < getBibles().length  && i >= 0) {
			return new Bible(this, i, bibles[i]);
		}
		else {
			throw new BibleNotFoundException();
		}
	}
	private void loadBibles() {
		try {
			// DATAINPUT //
			/*
			DataInputStream reader = new DataInputStream(getClass().getResourceAsStream("/"+BIBLES_FILE));
			int num = reader.readInt();
			bibles = new String[num];
			for(int i = 0;i<num;i++) {
				bibles[i] = reader.readUTF();
			}
			reader.close();
			*/
			// READER //
			String file = readFile("/" + BIBLES_FILE, this);
			int pos = file.indexOf('\n');
			int num = Integer.parseInt(file.substring(0, pos));
			bibles = new String[num];
			for(int i = 0;i<num;i++) {
				int old = pos+1;
				pos = file.indexOf('\n', old);
				bibles[i] = file.substring(old, pos);
			}
		}
		catch(IOException ioe) {
			//#ifdef trial.version
			ioe.printStackTrace();
			//#endif
		}
	}
	
	public static String readFile(String filename, Object loader) throws IOException {
		InputStreamReader input = new InputStreamReader(loader.getClass().getResourceAsStream(filename), "UTF-8");
		StringBuffer file = new StringBuffer();
		int c ;
		while ((c = input.read()) != -1) {
			file.append((char)c);
		}
		input.close();
		return file.toString();
	}
}
