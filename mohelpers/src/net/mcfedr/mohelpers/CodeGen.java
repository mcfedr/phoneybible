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

public class CodeGen {
    public CodeGen(String email) {
		try {
			char[] begin = email.substring(0, email.indexOf('@')).toCharArray();
			char[] end = email.substring(email.indexOf('@')+1).toCharArray();
			long beginT = 0;
			long prev = 7;
			long prev2 = 12;
			for(int i = 0;i<begin.length;i++) {
				beginT += (long)begin[i]*prev*prev2;
				prev2 = (long)begin[i]*prev;
				prev = (long)begin[i];
				
				/*System.out.println("begin"+beginT);
				System.out.println("begin"+beginT);
				System.out.println("begin"+beginT);*/
			}
			long endT = 0;
			for(int i = 0;i<end.length;i++) {
				endT += (long)end[i]*prev*prev2;
				prev2 = (long)end[i]*prev;
				prev = (long)end[i];
			}
			String out = "";
			out += beginT;
			out += endT;
			out += "7364930284123412";
			out = out.substring(0, 10);
			System.out.println(out);
		}
		catch(Exception e) {
		}
	}
	public static void main(String[] args) {
		new CodeGen(args[0]);
	}
}