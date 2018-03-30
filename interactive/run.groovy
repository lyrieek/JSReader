import com.th.js.container.ContextBlack
import com.th.js.core.JSFactory
import pers.th.util.io.IOUtils

import java.nio.charset.Charset

final String charset = "UTF-8"

def input_path = "C:\\Users\\user\\Desktop\\jquery.js"
def output_path = "G:\\th\\js.html"

def template_path = "G:\\idea\\JSReader\\html.template"

JSFactory js = new JSFactory()
js.load(IOUtils.reader(input_path, Charset.forName(charset)))

StringBuilder buffer = new StringBuilder()
for (ContextBlack black : js.scanner().codes()) {
    String item = black.item().replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("&", "&amp;")
            .replace(" ", "&nbsp;")
    buffer.append(black.status().html(item))
}
def content = buffer.toString().replaceAll("\n", "</li>\n<li style='white-space: pre;'>")
String template = IOUtils.reader(template_path, Charset.forName(charset))
        .replace("\${content}", content)
IOUtils.write(output_path, template, Charset.forName(charset))