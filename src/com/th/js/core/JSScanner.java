package com.th.js.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 核心扫描器<br />
 * 暂时使用此扫描引擎
 *
 */
public class JSScanner {

	private int point;
	private int markIndex = - 1;
	private String item;
	private String source;
	private boolean needSkip = false;
	private Matcher matcher;
	private Matcher hangMatcher;
	public final static String lineSeparator = "\n";//System.lineSeparator();

	public JSScanner(String sourceText, String searchChar) {
		point = 0;
		this.source = sourceText;
		change(searchChar);
	}

//	public static void main(String[] args) {
//		JSScanner reader = new JSScanner("123123 asdasd 583245 i sfsd sfdsf asdada 15 dsfsf 3434 fse 2", "\\S+");
//		while (reader.hasNext()) {
//			if (reader.item().equals("i")) {
//				reader.hang("\\d+");
//			}
//			if (reader.item().equals("15")) {
//				reader.release();
//			}
//			System.out.println(reader.item);
//		}
//	}

	public CharPoint getCharPoint() {
		CharPoint textPoint = new CharPoint();
		textPoint.setColumn(column());
		textPoint.setLine(line());
		textPoint.setIndex(point);
		return textPoint;
	}

	public int line() {
		return before().split(lineSeparator).length;
	}

	public int column() {
		int lastLine = before().lastIndexOf(lineSeparator);
		lastLine = (lastLine == -1) ? 0 : lastLine + 1;
		return point - lastLine + 1;//应当从1开始计算
	}

	/**
	 * 标记当前位置
	 */
	public void mark() {
		markIndex = point;
	}

	/**
	 * 从记住的位置开始拉取到当前位置
	 */
	public String pull() {
		return source.substring(markIndex, point);
	}

	/**
	 * 下一项
	 * @return
	 */
	public boolean hasNext() {
		skip();
		if (!matcher.find(point)) {
			needSkip = false;
			return false;
		}
		push(matcher.start(), matcher.group());
		return true;
	}

	/**
	 * 更换正则
	 * @param regex
	 */
	public void change(String regex) {
		this.matcher = Pattern.compile(regex).matcher(source);
	}
	
	/**
	 * 挂起当前搜索并更换新搜索
	 */
	public void hang(String newRegex) {
		hangMatcher = matcher;
		change(newRegex);
	}
	
	/**
	 * 释放被挂起的搜索
	 */
	public void release() {
		matcher = hangMatcher;
	}


	// process

	/**
	 * 跳过已扫描项
	 */
	public void skip() {
		if (needSkip && item != null) {
			needSkip = false;
			this.point += item.length();
		}
	}

	/**
	 * 推进           
	 */
	public void push(int location, String search) {
		move(location);
		needSkip = true;
		item = search;
	}

	/**
	 * 移动索引
	 * @param point
	 */
	public void move(int point) {
		this.point = point;
	}

	public int location() {
		return point;
	}

	public String before() {
		return source.substring(0, point);
	}

	public String after() {
		return source.substring(point);
	}

	public String item() {
		return item == null ? "" : item;
	}

	@Override
	public String toString() {
		return point + ":" + item;
	}

}
