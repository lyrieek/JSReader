package com.th.js.container;

import java.util.LinkedList;
import java.util.List;

import com.th.js.core.ContextBlack;

public class Content {

	private LinkedList<ContextBlack> black;

	private ContextBlack lastContext;

	private long movePoint = 0;

	public Content() {
		black = new LinkedList<>();
	}

	public Content(ContextBlack cb) {
		this();
		add(cb);
	}

	public void add(ContextBlack cb) {
		movePoint += cb.length();
		black.add(lastContext = cb);
	}

	public void addAll(List<ContextBlack> allBlack) {
		black.addAll(allBlack);
	}

	public void prepend(ContextBlack black) {
		getAllBlack().addFirst(black);
	}

	public void merge() {
		ContextBlack cBlack = black.getFirst();
		for (int i = 1; i < black.size(); i++) {
			cBlack.append(black.get(i).item());
		}
		cBlack.setEndInedx(black.getLast().getEndInedx());
		black.clear();
		black.add(cBlack);
	}

	public ContextBlack getLastContext() {
		return lastContext;
	}

	public boolean isEmpty() {
		return black.size() == 0;
	}

	public boolean isSingle() {
		return black.size() == 1;
	}

	public LinkedList<ContextBlack> getAllBlack() {
		return black;
	}

	public ContextBlack getSingleContextBlack() {
		return isSingle() ? black.get(0) : null;
	}

	public long getMovePoint() {
		return movePoint;
	}

	public void clear() {
		black.clear();
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "Content [black is empty, movePoint=" + movePoint + "]";
		}
		if (isSingle()) {
			return "Content [single black=" + black.get(0) + ", movePoint=" + movePoint + "]";
		}
		return "Content [black=" + black + ", movePoint=" + movePoint + "]";
	}

}
