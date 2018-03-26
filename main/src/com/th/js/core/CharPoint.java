package com.th.js.core;

public class CharPoint {

	private int line;

	private int column;

	private long index;

	public CharPoint() {
	}
	
	public static CharPoint get(long i) {
		CharPoint charPoint = new CharPoint();
		charPoint.setIndex(i);
		return charPoint;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "{CharPoint [line:" + line + ";column:" + column + ";]}";
	}
	
}
