package net.mcfedr.mohelpers;

import java.io.*;

public final class XMLTest {
	/*
	 * @args 0:input file
	 * @args 1:output folder
	 */
	public static void main(String[] args) {
		try {
			//SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			//saxParser.parse(new File(args[0]), new XMLBibleHandler(new File(args[1]), 0));
			
			DataInputStream data = new DataInputStream(new FileInputStream(new File(args[1], "versions")));
			int count = data.readInt();
			System.out.println("versions:\ncount:"+count);
			for (int i = 0; i < count; i++) {
				System.out.println(i+":"+data.readUTF());
			}
			
			data = new DataInputStream(new FileInputStream(new File(args[1], "0/books")));
			count = data.readInt();
			System.out.println("books:\ncount:"+count);
			for (int i = 0; i < count; i++) {
				System.out.println(i+":"+data.readUTF());
			}
			
			data = new DataInputStream(new FileInputStream(new File(args[1], "0/0/chapters")));
			count = data.readInt();
			System.out.println("chapters:\ncount:"+count);
			
			data = new DataInputStream(new FileInputStream(new File(args[1], "0/0/0")));
			count = data.readInt();
			System.out.println("verses:\ncount:"+count);
			for (int i = 0; i < count; i++) {
				System.out.println(i+":"+data.readUTF());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}