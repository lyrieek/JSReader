import com.th.js.container.ContextBlack
import com.th.js.core.JSFactory
import pers.th.util.io.IOUtils
import pers.th.util.text.StrBuffer

import java.nio.charset.Charset

final charset = Charset.forName("UTF-8")

def file = "../resource/sample2.js"
def template_path = "../html.template"
def output_path = "../output/effect.html"

JSFactory js = new JSFactory()
js.load(IOUtils.reader(file, charset))

StrBuffer buffer = new StrBuffer()
for (ContextBlack black : js.scanner().codes()) {
    buffer.append(black.status().html(black.item()))
}
def content = buffer.replaceAll("\n", "</li>\n<li style='white-space: pre;'>")
String template = IOUtils.reader(template_path, charset).replace("\${content}", content)
IOUtils.write(output_path, template, charset)
