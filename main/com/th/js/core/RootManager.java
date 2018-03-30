package com.th.js.core;

import com.th.js.container.AnalysisResult;
import com.th.js.container.Content;

/**
 * 管理器 所有code的储存容器
 *
 */
public class RootManager {

	private Status currentStatus;
	private JSDocument document;
	private Content currentContent;

	public RootManager() {
		currentStatus = Status.READ;
		document = new JSDocument();
	}

	/**
	 * 发送填充驱动参数
	 * 
	 * @param jsBaseDrive
	 */
	public void fullDrive(JsBaseDrive jsBaseDrive) {
		jsBaseDrive.result.change(getCurrentStatus());
		jsBaseDrive.result.setBeforeContent(currentContent);
	}

	/**
	 * 接收并修订整体文档
	 * 
	 * @param result
	 */
	public void receive(AnalysisResult result) {
		currentStatus = result.status();
		currentContent = result.context();
		if (result.isAllow()) {
			document.append(currentContent);
		}
		result.setLastStatus(currentStatus);
		currentStatus = result.getNextStatus();
	}

	public JSDocument getDocument() {
		return document;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}


}
