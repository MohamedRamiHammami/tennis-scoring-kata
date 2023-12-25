package com.kata.tennis.displayer;

public abstract class DisplayerConstant {

	private DisplayerConstant() {
		throw new IllegalStateException("Static class");
	}

	public static final String PLAYER_OPPONENT_SCORES = "Player %s : %s / Player %s : %s";
	public static final String PLAYER_WINS_THE_GAME = "Player %s wins the game";
}