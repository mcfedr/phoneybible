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