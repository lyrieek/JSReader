package com.th.js.container;

import java.util.LinkedList;
import java.util.List;

public class Content {

	private LinkedList<ContextBlack> black;

	public Content() {
		black = new LinkedList<>();
	}

	public Content(ContextBlack cb) {
		this();
		append(cb);
	}

	// public static void main(String[] args) {
	// Content content = new Content();
	// content.append("1231");
	// content.append("adfsfd");
	// content.append("bgre43");
	// content.append("cre4r");
	// content.append("dgre43");
	// System.out.println(content.getFirstContext());
	// System.out.println(content.getLastContext());
	// System.out.println(content.merge().children());
	// }

	public void append(ContextBlack cb) {
		black.add(cb);
	}

	public boolean appendAll(List<ContextBlack> allBlack) {
		if (allBlack == null || allBlack.size() == 0) {
			return false;
		}
		return black.addAll(allBlack);
	}

	public void prepend(ContextBlack black) {
		getAllBlack().addFirst(black);
	}

	public ContextBlack merge() {
		if (isEmpty()) {
			return null;
		}
		if (isSingle()) {
			return black.getFirst();
		}
		ContextBlack result = black.getFirst();
		for (int i = 1; i < black.size() - 1; i++) {
			result.children().add(black.get(i));
			result.append(black.get(i).item());
		}
		result.append(black.getLast());
		black.clear();
		black.add(result);
		return result;
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

	/**
	 * size is 1:return this; <br />
	 * size is 0 or more:return null;
	 */
	public ContextBlack getSingle() {
		return isSingle() ? black.get(0) : null;
	}

	public void clear() {
		black.clear();
	}

	public ContextBlack last() {
		if (isEmpty()) {
			return null;
		}
		return black.getLast();
	}

	public ContextBlack first() {
		if (isEmpty()) {
			return null;
		}
		return black.getFirst();
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "Content [black is empty]";
		}
		if (isSingle()) {
			return "Content [single:" + black.get(0) + "]";
		}
		return "Content [black=" + black + "]";
	}

}
