package com.th.js.container;

import java.util.ArrayList;
import java.util.List;

import com.th.js.core.CharPoint;
import com.th.js.core.Status;

/** 
 * 代码块
 */
public class ContextBlack {

	private String id;

	private CharPoint startPoint;

	private int level;

	private StringBuffer item;

	private ContextBlack father;
	
	private List<ContextBlack> children;

	private boolean isClosed;

	private String remark;

	private Status status;
	
	public ContextBlack(CharPoint start) {
		if (start == null) {
			throw new NullPointerException("Need start point");
		}
		startPoint = start;
		isClosed = false;
		id = Integer.toHexString((int) (Math.random() * 0xFFFFFFF))
				+ Integer.toHexString((int) (Math.random() * 0xFFFFFFF)) + hashCode();
		children = new ArrayList<>();
	}

	public static ContextBlack builder(CharPoint point,String text,Status status) {
		ContextBlack cb = new ContextBlack(point);
		cb.full(text);
		cb.setStatus(status);
		return cb;
	}

	public ContextBlack full(String text) {
		setItem(text);
		close();
		return this;
	}

	public void append(Object chars) {
		if (chars == null || chars.toString().isEmpty()) {
			return;
		}
		if (item == null) {
			item = new StringBuffer(chars.toString());
			return;
		}
		item.append(chars.toString());
	}

	public int length() {
		return item().length();
	}

	public void close() {
		this.isClosed = true;
	}

	public boolean isEmpty() {
		return item().trim().isEmpty();
	}

	public String item() {
		return item == null ? "" : item.toString();
	}

	public void setItem(String item) {
		this.item = new StringBuffer(item);
	}
	
	public boolean of(Status status) {
		return this.status == status;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public CharPoint getStartIndex() {
		return startPoint;
	}

	public void setStartIndex(CharPoint startPoint) {
		this.startPoint = startPoint;
	}

	// public CharPoint getEndInedx() {
	// return endPoint;
	// }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status status() {
		return status;
	}

	public void setFather(ContextBlack cb) {
		this.father = cb;
	}

	public ContextBlack getFather() {
		return father;
	}
	
	public void setChildren(List<ContextBlack> children) {
		this.children = children;
	}
	
	public List<ContextBlack> children() {
		return children;
	}
	
	public String id() {
		return id;
	}
	
	@Override
	public String toString() {
		return item();
	}
 	
}
