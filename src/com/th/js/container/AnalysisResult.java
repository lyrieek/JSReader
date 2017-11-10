package com.th.js.container;

import com.th.js.core.CharPoint;
import com.th.js.core.ContextBlack;
import com.th.js.core.Status;

public class AnalysisResult {
	
	private Status status;
	
	private Status nextStatus;
	
	private Content lastContent;
	
	private Content content;
	
	private CharPoint point;
	
	private boolean allow = true;
	
	
	public AnalysisResult() {
		status = Status.READ;
		content = new Content();
	}
	
	/**
	 * @param text
	 */
	public void full(String text) {
		this.content = new Content(builderContextBlack(text));
	}

	public void prepend(String text) {
		this.content.prepend(builderContextBlack(text));;
	}
	
	public ContextBlack builderContextBlack(String text) {
		ContextBlack cb = new ContextBlack(point);
		cb.full(text);
		cb.setStatus(status);
		return cb;
	}

	public void merge() {
		content.merge();
		allow = true;
	}
	
	/**
	 * 推进
	 */
	public void further() {
		lastContent.addAll(content.getAllBlack());
		content = lastContent;
	}
	
	/**
	 * 更换最后一次与以后的状态
	 * @param status
	 */
	public void change(Status status) {
		this.status = nextStatus = status;
		if (content.getLastContext() != null) {
			content.getLastContext().setStatus(status);
		}
	}
	
	/**
	 * 变更当前状态(瞬间消失)
	 * @param status
	 */
	public void temporary(Status status) {
		Status source = this.status;
		change(status);
		lazyChange(source);
	}

	
	public Status status() {
		return status;
	}
	
	public boolean is(Status status) {
		return this.status.equals(status);
	}
	
	public void lazyChange(Status status) {
		nextStatus = status;
	}
	
	public Status getNextStatus() {
		return nextStatus;
	}

	public Content context() {
		return content;
	}
	
	public CharPoint point() {
		return point;
	}
	
	public void setPoint(CharPoint point) {
		this.point = point;
	}
	
	public void setLastContent(Content content) {
		this.lastContent = content;
	}

	public void clear() {
		content.clear();
	}
	
	public void lazyCommit() {
		allow = false;
	}
	
	public boolean isAllow() {
		return allow;
	}
	
	@Override
	public String toString() {
		return "AnalysisResult [status=" + status + ", content=" + content + "]";
	}

	
	
}
enum Type{
	
	ADD,
	CHANGE
	
}
