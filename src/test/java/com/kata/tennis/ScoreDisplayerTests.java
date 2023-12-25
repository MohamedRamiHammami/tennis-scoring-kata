package com.kata.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.kata.tennis.displayer.ScoreDiplay;
import com.kata.tennis.displayer.ScoreDisplayer;
import com.kata.tennis.displayer.ScoreDisplayerImpl;

class ScoreDisplayerTests {

	private ScoreDisplayer displayer;

	public ScoreDisplayerTests() {
		displayer = new ScoreDisplayerImpl();
	}

	@Test
	void newGame() {
		TennisGamePoint game = initNewGamePoint();
		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", false);
	}

	@Test
	void player15Opponent0() {
		TennisGamePoint game = initNewGamePoint();
		game.playerScores();
		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", false)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.A15_B00);
	}

	@Test
	void player0Opponent15() {
		TennisGamePoint game = initNewGamePoint();
		game.opponentScores();
		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", false)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.A00_B15);
	}

	@Test
	void cleanWinPlayer() {
		TennisGamePoint game = initNewGamePoint();
		game.playerScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", true)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.PLAYER_A_WINS);
	}

	@Test
	void cleanWinOpponent() {
		TennisGamePoint game = initNewGamePoint();
		game.opponentScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", true)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.PLAYER_B_WINS);
	}

	@Test
	void playerWinTo30() {
		TennisGamePoint game = initNewGamePoint();
		game.opponentScores(2);
		game.playerScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", true)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.PLAYER_A_WINS);
	}

	@Test
	void opponentWinTo30() {
		TennisGamePoint game = initNewGamePoint();
		game.playerScores(2);
		game.opponentScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", true)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.PLAYER_B_WINS);
	}

	@Test
	void advPlayer() {
		TennisGamePoint game = initNewGamePoint();
		game.opponentScores(3);
		game.playerScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", false)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.AAdv_B40);
	}

	@Test
	void advOpponent() {
		TennisGamePoint game = initNewGamePoint();
		game.playerScores(3);
		game.opponentScores(4);

		ScoreDiplay score = displayer.displayScore(game);
		assertThat(score).isNotNull().hasFieldOrPropertyWithValue("finalPoint", false)
				.hasFieldOrPropertyWithValue("printedScore", TestsConstant.A40_BAdv);
	}

	private TennisGamePoint initNewGamePoint() {
		return new TennisGamePoint(PlayerName.A, PlayerName.B);
	}

}
