package br.com.delphos.billing.util;

public class StringUtils {
	
	public static String capitaliza(String texto) {
	    //String source = "hello good old world";
	    StringBuffer res = new StringBuffer();

	    String[] strArr = texto.split(" ");
	    for (String str : strArr) {
	        char[] stringArray = str.trim().toCharArray();
	        stringArray[0] = Character.toUpperCase(stringArray[0]);
	        str = new String(stringArray);

	        res.append(str).append(" ");
	    }
	    
	    //System.out.print("Result: " + res.toString().trim());
	    
	    return res.toString().trim();
	}
	
	public static String dashToUpperCase(String input) {

        StringBuilder result = new StringBuilder();
        boolean toUpper = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-') {
                toUpper = true;
            } else {
                result.append(toUpper ? Character.toUpperCase(c) : c);
                toUpper = false;
            }
        }

        return result.toString();
    }
    
	public static String capitalsToUnderscore(String input) {
    	return input.replaceAll("([A-Z])", "_$1").toLowerCase();
    }

}
