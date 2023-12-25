package com.kata.tennis.displayer;

public enum DisplayedPlayerScore {
	ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("Adv");

	private String value;

	DisplayedPlayerScore(String value) {
		this.value = value;
	}

	String value() {
		return this.value;
	}
}
