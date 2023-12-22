package com.kata.tennis;

import java.util.List;
import static com.kata.tennis.GameConstant.*;

public class TennisScoring {

	private TennisScoring() {
		throw new IllegalStateException("Static class");
	}

	public static List<String> calculateScore(String input) {

		TennisGame game = new TennisGame(new Player("Rami"), new Player("Patrick"));

		for (char c : input.toCharArray()) {
			if (c == PLAYER) {
				game.updateScorePlayer();
			} else if (c == OPPONENT) {
				game.updateScoreOpponent();
			}
			game.displayScore();
		}
		return game.getScores();
	}
}
