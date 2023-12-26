package com.kata.tennis.displayer;

import static com.kata.tennis.displayer.DisplayerConstant.PLAYER_OPPONENT_SCORES;
import static com.kata.tennis.displayer.DisplayerConstant.PLAYER_WINS_THE_GAME;

import com.kata.tennis.TennisGamePoint;

public class ScoreDisplayerImpl implements ScoreDisplayer {

	public ScoreDiplay displayScore(TennisGamePoint game) {
		if (game.hasWinner()) {
			return displayWinner(game);
		}

		if (game.hasAdvantage()) {
			return displayAdvantage(game);
		}

		return displayPending(game);
	}

	private static ScoreDiplay displayWinner(TennisGamePoint game) {
		String printedScore = PLAYER_WINS_THE_GAME.formatted(game.playerWithHighestScore());
		return new ScoreDiplay(printedScore, true);
	}

	private static ScoreDiplay displayAdvantage(TennisGamePoint game) {
		String printedScore = "";
		if (game.getPlayerScore() > game.getOpponentScore()) {
			printedScore = PLAYER_OPPONENT_SCORES.formatted(game.getPlayerName(),
					DisplayedPlayerScore.ADVANTAGE.value(), game.getOpponentName(), DisplayedPlayerScore.FORTY.value());
			return new ScoreDiplay(printedScore, false);
		} else {
			printedScore = PLAYER_OPPONENT_SCORES.formatted(game.getPlayerName(), DisplayedPlayerScore.FORTY.value(),
					game.getOpponentName(), DisplayedPlayerScore.ADVANTAGE.value());
		}
		return new ScoreDiplay(printedScore, false);
	}

	private static ScoreDiplay displayPending(TennisGamePoint game) {
		String printedScore = PLAYER_OPPONENT_SCORES.formatted(game.getPlayerName(), formatScore(game.getPlayerScore()),
				game.getOpponentName(), formatScore(game.getOpponentScore()));
		return new ScoreDiplay(printedScore, false);
	}

	private static String formatScore(int score) {
		return switch (score) {
		case 0 -> DisplayedPlayerScore.ZERO.value();
		case 1 -> DisplayedPlayerScore.FIFTEEN.value();
		case 2 -> DisplayedPlayerScore.THIRTY.value();
		default -> DisplayedPlayerScore.FORTY.value();
		};
	}

}