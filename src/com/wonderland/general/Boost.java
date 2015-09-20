package com.wonderland.general;

public class Boost {

	public enum Type {
		GOOD(0), BAD(1);

		private int id;

		Type(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
	};

	public Type type;
	public String text;

	private static String[] stats = { "Atk", "Def", "SpA", "SpD", "Spe" };
	private static String[] badboosts = { "0.67", "0.5", "0.4", "0.33", "0.29", "0.25" };
	private static String[] goodboosts = { "1.5", "2", "2.5", "3", "3.5", "4" };

	public int[] formatBoost() {
		String[] workArray = null;
		switch (type) {
		case GOOD:
			workArray = goodboosts;
			break;
		case BAD:
			workArray = badboosts;
			break;
		default:
			break;
		}
		String workingText = text.replace("&nbsp;", " ");
		if (workingText.contains("x")) {
			String boost = workingText.substring(0, workingText.indexOf('×'));
			String writtentype = workingText.substring(workingText.indexOf(' ') + 1);

			return new int[] { type.getID(), getIndex(workArray, boost), getIndex(stats, writtentype) };
		} else {
			return null;
		}
	}

	private int getIndex(String[] search, String find) {
		for (int i = 0; i < search.length; i++) {
			if (search[i] == find) {
				return i;
			}
		}
		return -1;
	}

	public static String getBoost(String boost) {
		if (boost == "--") {
			return "1,0";
		}
		if (boost.contains("+")) {
			boost = boost.replace("+", "");
			int boo = Integer.parseInt(boost) - 1;
			return goodboosts[boo].replace('.', ',');
		}
		if (boost.contains("-")) {
			boost = boost.replace("-", "");
			int boo = Integer.parseInt(boost) - 1;
			return badboosts[boo].replace('.', ',');
		}
		return "1,0";
	}
}
