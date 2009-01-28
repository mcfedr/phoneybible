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