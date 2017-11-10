package com.th.js.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.th.js.container.Content;

/**
 * 整体文档
 *
 */
public class JSDocument {

	// 文本总长度
	private long length;

	// 扫描到的长度
	private long index;

	// 已读代码

	private LinkedList<ContextBlack> codes;

	// 未关闭的代码
	private List<ContextBlack> notClose;

	public JSDocument() {
		codes = new LinkedList<ContextBlack>();
		notClose = new ArrayList<ContextBlack>();
	}

	public void append(Content context) {
		for (ContextBlack contextBlack : context.getAllBlack()) {
			codes.add(contextBlack);
		}
	}

	public void printf() {
		StringBuffer buffer = new StringBuffer();
		for (ContextBlack contextBlack : codes) {
//			if (!contextBlack.of(Status.REMARK)) {
//				continue;
//			}
			String item = contextBlack.item();
			System.out.print(contextBlack.getStartIndex() + ">>");
			if (contextBlack.status() != null) {
				System.out.print(contextBlack.status() + ":");
			}
			System.out.println(contextBlack.isEmpty() ? "<<" : contextBlack.item() + "<<");
			buffer.append(item);
		}
		// System.out.println(buffer.toString().split("\n").length);
		// 250 10108
	}

	public long index() {
		length += index;
		return index;
	}

	public long length() {
		return length;
	}

	public LinkedList<ContextBlack> codes() {
		return codes;
	}

	public void setCodes(LinkedList<ContextBlack> codes) {
		this.codes = codes;
	}

	public List<ContextBlack> getNotClose() {
		return notClose;
	}

	public void setNotClose(List<ContextBlack> notClose) {
		this.notClose = notClose;
	}

}
