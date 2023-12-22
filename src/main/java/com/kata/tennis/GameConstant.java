package com.kata.tennis;

public abstract class GameConstant {

	private GameConstant() {
		throw new IllegalStateException("Static class");
	}

	public static final char PLAYER = 'A';
	public static final char OPPONENT = 'B';

}