package com.th.js;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import com.th.js.container.ContextBlack;
import com.th.js.core.JSDocument;
import com.th.js.core.JSFactory;
import com.th.js.core.Status;

public class JSReader {

	public final static String charset = "UTF-8";

	public static void main(String[] args) throws Exception {
		// String module = "tag"; // member
		JSFactory js = new JSFactory();
		// js.load(FileUtils.readFileToString(
		// new File("g:/cms/CmsWebApp/client/app/" + module + "/" + module +
		// ".controller.js"),
		// Charset.forName(charset)));
		// js.load(FileUtils.readFileToString(new File("F:\\lodash.src.js"),
		// Charset.forName(charset)));
		js.load(FileUtils.readFileToString(new File("F:\\jquery-baidu-1.10.2.min.js"), Charset.forName(charset)));
		// double[] times = new double[5];
		// js.scanner();
		// for (int i = 0; i < times.length; i++) {
		// long start = System.currentTimeMillis();
		// js.scanner();
		// times[i] = (System.currentTimeMillis() - start) / 1000D;
		//// System.out.println(times[i] = ((System.currentTimeMillis() - start)
		// / 1000D));
		// System.out.println(times[i]);
		// }
		js.scanner().printf();
		// write(js.scanner());
	}

	public static void write(JSDocument doc) throws IOException {
		StringBuffer buffer = new StringBuffer();
		for (ContextBlack black : doc.codes()) {
			String item = black.item();
			item = StringEscapeUtils.escapeHtml4(item);
			if (black.status() == Status.KEYWORDS) {
				item = wrap(item, "color:#c678dd;");
			} else if (black.status() == Status.READ) {
				item = wrap(item, "color:#61afef;");
			} else if (black.status() == Status.REMARK) {
				item = wrap(item, "color:#5c6370;font-style: italic;");
			} else if (black.status() == Status.STRING) {
				item = wrap(item, "color:#98c379;");
			} else if (black.status() == Status.MARK || black.status() == Status.DECLARE) {
				item = wrap(item, "color:#abb2bf;");
			} else if (black.status() == Status.NUMBER || black.status() == Status.BOOLEAN) {
				item = wrap(item, "color:#d19a66;");
			} else if (black.status() == Status.REGEX) {
				item = wrap(item, "color:red;");
			}
			buffer.append(item);
		}
		FileUtils.write(new File("G:\\th\\js.html"),
				"<head><meta http-equiv='content-type' content='text/html;charset=utf-8'></head><body style='background:#282c34;font-size:21px' contenteditable='true'><pre>"
						+ buffer.toString().replace("\n", "<br />"),
				Charset.forName(charset), false);
	}

	private static String wrap(String item, String style) {
		return ("<label style='$value'>" + item + "</label>").replace("$value", style);
	}

}
