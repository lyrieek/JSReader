package com.th.js.core;

public enum Status {

    //正常文本
    READ("color:#61afef;"),

    //注释
    REMARK("color:#5c6370;font-style: italic;"),

    STRING("color:#98c379;"),

    NUMBER("color:#d19a66;"),

    //符号
    MARK("color:#abb2bf;"),

    //关键字
    KEYWORDS("color:#c678dd;"),

    BOOLEAN("color:#d19a66;"),

    REGEX("color:red;"),

    EMPTY(),

    //赋值
    DECLARE("color:#abb2bf;");

    private String styles;

    private String attributes;

    Status() {
    }

    Status(String style) {
        styles = style;
    }

    Status(String style, String attribute) {
        this(style);
        attributes = attribute;
    }

    public String styles() {
        return styles;
    }

    public String attributes() {
        return attributes;
    }

    public String html(String content) {
        String before = "<label data-status='" + name() + "' ";
        if (attributes != null && !attributes.isEmpty()) {
            before += attributes + " ";
        }
        if (styles != null && !styles.isEmpty()) {
            before += "style='" + styles() + "' ";
        }
        return before + ">" + content
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("&", "&amp;")
                .replace(" ", "&nbsp;") + "</label>";
    }

}
