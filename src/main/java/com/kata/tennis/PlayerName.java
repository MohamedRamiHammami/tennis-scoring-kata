package com.kata.tennis;

public enum PlayerName {
	A('A'), B('B');

	private char value;

	PlayerName(char value) {
		this.value = value;
	}

	char value() {
		return this.value;
	}
}
