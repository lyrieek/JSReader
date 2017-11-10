package com.th.js.core;

import com.th.js.container.AnalysisResult;

/**
 * 处理器
 *
 */
public class JSProcessor extends JsBaseDrive {

	public final static String MARKS = ".={}:;,()[]";

	public JSProcessor(Variables vals) {
		super(vals);
	}

	@Override
	public AnalysisResult translation(JSScanner scanner) {
		super.translation(scanner);
		if (result.is(Status.READ)) {
			read(scanner);
			return result;
		}
		result.further();
		if (result.is(Status.STRING)) {
			string(scanner.item());
		} else if (result.is(Status.REMARK)) {
			remark(scanner);
		}
		return result;
	}

	public void read(JSScanner scanner) {
		String item = scanner.item();
		if (item.trim().isEmpty()) {
			result.temporary(Status.EMPTY);
		} else if (item.length() == 1 && MARKS.contains(item)) {
			result.temporary(Status.MARK);
		} else if (item.matches("('|\"|`)")) {
			result.change(Status.STRING);
			result.lazyCommit();
			storage.update("last.string.identifier", item);
		} else if (item.equals("//") || item.equals("/*")) {
			result.change(Status.REMARK);
			result.lazyCommit();
			result.clear();
			scanner.mark();
			scanner.hang(item.equals("//") ? JSScanner.lineSeparator : "\\*/");
			storage.update("last.remark.identifier", item.equals("//") ? JSScanner.lineSeparator : "*/");
		} else if (item.matches("((\\-)?\\d{1,}(\\.{1}\\d+)?)")) {
			result.temporary(Status.NUMBER);
		} else if (KeyWords.contains(item)) {
			result.temporary(Status.KEYWORDS);
		} else if (item.equals("true") || item.equals("false")) {
			result.temporary(Status.BOOLEAN);
		}
	}

	public void remark(JSScanner scanner) {
		String item = scanner.item();
		if (!item.equals(storage.getString("last.remark.identifier"))) {
			result.lazyCommit();
			return;
		}
		scanner.release();
		result.prepend(scanner.pull());
		result.merge();
		result.lazyChange(Status.READ);
	}

	public void string(String item) {
		if (!item.equals(storage.getString("last.string.identifier"))) {
			result.lazyCommit();
			return;
		}
		result.merge();
		result.lazyChange(Status.READ);
	}

}
