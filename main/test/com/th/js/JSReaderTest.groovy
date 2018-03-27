package com.th.js

import com.th.js.container.ContextBlack
import com.th.js.core.JSFactory
import com.th.js.core.Status
import pers.th.util.io.IOUtils

import java.nio.charset.Charset

final String charset = "UTF-8"

static wrap(String item, String style) {
    return ("<label style='${style}'>${item}</label>")
}

JSFactory js = new JSFactory()
js.load(IOUtils.reader("C:\\Users\\user\\Desktop\\jquery.js",
        Charset.forName(charset)))

StringBuilder buffer = new StringBuilder()
for (ContextBlack black : js.scanner().codes()) {
    String item = black.item()
    item = item.replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("&", "&amp;")
            .replace(" ", "&nbsp;")
//			item = StringEscapeUtils.escapeHtml4(item);
    buffer.append(black.status().html(item))
}
def content = buffer.toString().replaceAll("\n", "</li>\n<li style='white-space: pre;'>")
String template = IOUtils.reader("G:\\idea\\JSReader\\html.template", Charset.forName(charset))
        .replace("\${content}", content)
IOUtils.write("G:\\th\\js.html", template, Charset.forName(charset))