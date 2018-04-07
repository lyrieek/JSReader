package com.th.js.core;

import com.th.js.container.AnalysisResult;

/**
 * 驱动处理器 所有处理器的基类
 */
public abstract class JsBaseDrive {

	protected AnalysisResult result;
	protected Variables storage;
	protected String item;
	JSScanner scanner;

	JsBaseDrive(Variables val) {
		result = new AnalysisResult();
		storage = val;
	}

	public void clear() {
		storage.clear();
	}

	/**
	 * 解释JS
	 */
	public AnalysisResult translation(JSScanner scanner) {
		if (result == null) {
			System.err.println("JsDrive.translation():AnalysisResult need instance,item:" + scanner.item());
			return null;
		}
		this.scanner = scanner;
		result.initPoint(scanner.getCharPoint());
		result.full(item = scanner.item());
		return result;
	}

	/**
	 * 实例化自身参数
	 * @param manager 主管理器
	 */
	public void instance(RootManager manager) {
		result.restore();
		manager.fullDrive(this);
	}
	
	public AnalysisResult result() {
		return result;
	}
	
}
