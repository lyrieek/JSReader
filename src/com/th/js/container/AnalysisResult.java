package com.th.js.container;

import com.th.js.core.CharPoint;
import com.th.js.core.Status;

public class AnalysisResult {
	
	/**
	 * 此次扫描到的状态
	 */
	private Status status;
	
	/**
	 * 上一次的状态
	 */
	private Status lastStatus;
	
	/**
	 * 推测下一次的状态
	 */
	private Status nextStatus;
	
	/**
	 * 上一次的内容
	 */
	private Content beforeContent;
	
	/**
	 * 此次返回的内容
	 */
	private Content content;
	
	/**
	 * 切入点
	 */
	private CharPoint point;
	
	/**
	 * 允许被载入
	 */
	private boolean allow = true;
	
	public AnalysisResult() {
		restore();
	}

	/**
	 * 还原/初始化
	 */
	public void restore() {
		status = Status.READ;
		content = new Content();
	}
	
	/**
	 * 填充文本
	 * @param text
	 */
	public void full(String text) {
		if (point == null) {
			throw new NullPointerException("Need Point");
		}
		this.content = new Content(ContextBlack.builder(point, text, status));
	}

	/**
	 * 添加到之前
	 * @param text
	 */
	public void prepend(CharPoint charPoint,String text) {
		this.content.prepend(ContextBlack.builder(charPoint, text, status));
	}
	
	/**
	 * 合并
	 * @return 
	 */
	public ContextBlack merge() {
		allow = true;
		return content.merge();
	}

	
	/**
	 * 推进,合并上一次的内容
	 */
	public void further() {
		beforeContent.appendAll(content.getAllBlack());
		content = beforeContent;
	}
	
	/**
	 * 更换今后的状态
	 * @param status
	 */
	public void change(Status status) {
		this.status = nextStatus = status;
		if (content.last() != null) {
			content.last().setStatus(status);
		}
	}
	
	/**
	 * 临时的变更当前状态
	 * @param status
	 */
	public void temporary(Status status) {
		nextStatus = this.status;
		this.status = status;
		if (content.last() != null) {
			content.last().setStatus(status);
		}
	}

	
	public Status status() {
		return status;
	}
	
	public boolean is(Status status) {
		return this.status.equals(status);
	}
	
	/**
	 * 更新下一次的状态
	 * @param status
	 */
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
	
	public void initPoint(CharPoint point) {
		this.point = point;
	}
	
	public void setBeforeContent(Content content) {
		this.beforeContent = content;
	}
	
	public Content getBeforeContent() {
		return beforeContent;
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
	
	public void setLastStatus(Status lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Status getLastStatus() {
		return lastStatus;
	}
	
	@Override
	public String toString() {
		return "AnalysisResult [status=" + status + ", content=" + content + "]";
	}
	
}
