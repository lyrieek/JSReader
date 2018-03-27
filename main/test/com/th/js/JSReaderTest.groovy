package com.th.js

import com.th.js.container.ContextBlack
import com.th.js.core.JSFactory
import com.th.js.core.Status
import pers.th.util.io.IOUtils

import java.nio.charset.Charset

final String charset = "UTF-8"

static String wrap(String item, String style) {
    return ("<label style='${style}'>${item}</label>")
}

JSFactory js = new JSFactory()
js.load(IOUtils.reader("C:\\Users\\user\\Desktop\\jquery.js",
        Charset.forName(charset)))

StringBuilder buffer = new StringBuilder()
for (ContextBlack black : js.scanner().codes()) {
    String item = black.item()
//			item = StringEscapeUtils.escapeHtml4(item);
    if (black.status() == Status.KEYWORDS) {
        item = wrap(item, "color:#c678dd;")
    } else if (black.status() == Status.READ) {
        item = wrap(item, 'color:#61afef;')
    } else if (black.status() == Status.REMARK) {
        item = wrap(item, "color:#5c6370;font-style: italic;")
    } else if (black.status() == Status.STRING) {
        item = wrap(item, "color:#98c379;")
    } else if (black.status() == Status.MARK || black.status() == Status.DECLARE) {
        item = wrap(item, "color:#abb2bf;")
    } else if (black.status() == Status.NUMBER || black.status() == Status.BOOLEAN) {
        item = wrap(item, "color:#d19a66;")
    } else if (black.status() == Status.REGEX) {
        item = wrap(item, "color:red;")
    }
    buffer.append(item)
}
String content = IOUtils.reader("C:\\Users\\user\\Desktop\\jquery.js.html", Charset.forName(charset))
content = content.replace("${content}",
        buffer.toString().replaceAll("\n", "</li>\n<li style='white-space: pre;'>"))
IOUtils.write("G:\\th\\js.html", content, Charset.forName(charset))