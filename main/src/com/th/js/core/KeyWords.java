package com.th.js.core;

public class KeyWords {

    public final static String MARKS = ".={}:;,()[]";

    private final static String[] KEYWORDS = new String[]{"abstract", "arguments", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "debugger", "default", "delete", "do", "double", "else", "enum", "eval", "export", "extends", "false", "final", "finally", "float", "for", "function", "goto", "if", "implements", "import", "in", "instanceof", "int", "interface", "let", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "typeof", "var", "void", "volatile", "while", "with", "yield"};

    public static boolean contains(String chars) {
        for (String item : KEYWORDS) {
            if (item.equals(chars)) {
                return true;
            }
        }
        return false;
    }

}
