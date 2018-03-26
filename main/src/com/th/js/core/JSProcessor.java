package com.th.js.core;

import com.th.js.container.AnalysisResult;

/**
 * 处理器
 *
 */
public class JSProcessor extends JsBaseDrive {

	public JSProcessor(Variables vals) {
		super(vals);
	}

	@Override
	public AnalysisResult translation(JSScanner scanner) {
		super.translation(scanner);
		if (result.is(Status.READ)) {
			read();
			return result;
		}
		result.further();
		boolean equals = result.is(Status.STRING) && intercept(storage.getString("last.string.identifier"));
		equals = equals || (result.is(Status.REMARK) && intercept(storage.getString("last.remark.identifier")));
		equals = equals || (result.is(Status.REGEX) && item.length() % 2 == 1 && intercept(item));
		return result;
	}

	public boolean intercept(String equalsText) {
		if (!item.equals(equalsText)) {
			result.lazyCommit();
			return false;
		}
		scanner.release();
		result.prepend((CharPoint) storage.get("last.point"), scanner.pull());
		result.merge();
		result.lazyChange(Status.READ);
		return true;
	}

	public void read() {
		if (item.trim().isEmpty()) {
			result.temporary(Status.EMPTY);
		} else if (item.equals("=")) {
			result.temporary(Status.DECLARE);
		} else if (item.length() == 1 && KeyWords.MARKS.contains(item)) {
			result.temporary(Status.MARK);
		} else if (item.matches("((\\-)?\\d{1,}(\\.{1}\\d+)?)")) {
			result.temporary(Status.NUMBER);
		} else if (KeyWords.contains(item)) {
			result.temporary(Status.KEYWORDS);
		} else if (item.equals("true") || item.equals("false")) {
			result.temporary(Status.BOOLEAN);
		} else if (item.matches("('|\"|`)")) {
			result.change(Status.STRING);
			putIntercept("(\\\\)?" + item);
			storage.update("last.string.identifier", item);
		} else if (item.equals("//") || item.equals("/*")) {
			result.change(Status.REMARK);
			putIntercept(item.equals("//") ? JSScanner.lineSeparator : "\\*/");
			storage.update("last.remark.identifier", item.equals("//") ? JSScanner.lineSeparator : "*/");
		} else if (item.equals("/") && result.getLastStatus() == Status.DECLARE) {
			result.change(Status.REGEX);
			putIntercept("\\\\*/");
		}
	}

	/**
	 * 设置拦截器
	 * @param regex
	 */
	public void putIntercept(String regex) {
		result.lazyCommit();
		result.clear();
		storage.update("last.point", result.point());
		scanner.mark();
		scanner.hang(regex);
	}

}
