package com.th.js.core;

public class KeyWords {

	public final static String[] KEYWORDS = new String[] { "new", "var", "function", "if", "for", "else", "return",
			"try", "delete" };

	public static boolean contains(String chars) {
		for (String item : KEYWORDS) {
			if (item.equals(chars)) {
				return true;
			}
		}
		return false;
	}

}
