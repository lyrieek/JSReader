package com.th.js.core;

import java.util.LinkedList;

import com.th.js.container.Content;
import com.th.js.container.ContextBlack;

/**
 * 整体文档
 *
 */
public class JSDocument {

	// 已读代码
	private LinkedList<ContextBlack> codes;

	public JSDocument() {
		codes = new LinkedList<ContextBlack>();
	}

	public void append(Content context) {
		for (ContextBlack contextBlack : context.getAllBlack()) {
			codes.add(contextBlack);
		}
	}

	public void printf() {
		StringBuffer buffer = new StringBuffer();
		for (ContextBlack contextBlack : codes) {
			 if (!contextBlack.of(Status.STRING)) {
			 continue;
			 }
			String item = contextBlack.item();
			System.out.print(contextBlack.getStartIndex() + ">>");
			if (contextBlack.status() != null) {
				System.out.print(contextBlack.status() + ":");
			}
			System.out.println(contextBlack.isEmpty() ? "<<" : item + "<<");
			buffer.append(item);
		}
		// System.out.println(buffer.toString().split("\n").length);
		// 250 10108
	}

	public LinkedList<ContextBlack> codes() {
		return codes;
	}

	public void setCodes(LinkedList<ContextBlack> codes) {
		this.codes = codes;
	}

}
