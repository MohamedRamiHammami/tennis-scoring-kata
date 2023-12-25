package com.kata.tennis;

import static com.kata.tennis.TestsConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.kata.tennis.displayer.ScoreDisplayer;
import com.kata.tennis.displayer.ScoreDisplayerImpl;

class TennisGameFunctionalTests {

	private ScoreDisplayer displayer;
	private TennisGamePlay gamePlay;

	public TennisGameFunctionalTests() {
		displayer = new ScoreDisplayerImpl();
		gamePlay = new TennisGamePlayImpl(displayer);
	}

	@Test
	void emptyInput() {
		List<String> scores = gamePlay.playGame("");
		assertThat(scores).isEmpty();
	}

	@Test
	void invalidInput() {
		InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
			gamePlay.playGame("AABCAB");
		});

		assertEquals("Invalid player Name C", exception.getMessage());
	}

	@Test
	void invalidInput2() {
		InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
			gamePlay.playGame("/");
		});

		assertEquals("Invalid player Name /", exception.getMessage());
	}

	@Test
	void cleanWinPlayer() {
		List<String> scores = gamePlay.playGame(AAAA);
		List<String> expectedScoresList = List.of(A15_B00, A30_B00, A40_B00, PLAYER_A_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void cleanWinOpponent() {
		List<String> scores = gamePlay.playGame(BBBB);
		List<String> expectedScoresList = List.of(A00_B15, A00_B30, A00_B40, PLAYER_B_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void playerWinTo30() {
		List<String> scores = gamePlay.playGame(ABABAA);
		List<String> expectedScoresList = List.of(A15_B00, A15_B15, A30_B15, A30_B30, A40_B30, PLAYER_A_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void opponentWinTo30() {
		List<String> scores = gamePlay.playGame(BABABB);
		List<String> expectedScoresList = List.of(A00_B15, A15_B15, A15_B30, A30_B30, A30_B40, PLAYER_B_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void advPlayer() {
		List<String> scores = gamePlay.playGame(ABABABA);
		List<String> expectedScoresList = List.of(A15_B00, A15_B15, A30_B15, A30_B30, A40_B30, A40_B40, AAdv_B40);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void advOpponent() {
		List<String> scores = gamePlay.playGame(BABABAB);
		List<String> expectedScoresList = List.of(A00_B15, A15_B15, A15_B30, A30_B30, A30_B40, A40_B40, A40_BAdv);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void playertWinsAfterAdv() {
		List<String> scores = gamePlay.playGame(ABABABAA);
		List<String> expectedScoresList = List.of(A15_B00, A15_B15, A30_B15, A30_B30, A40_B30, A40_B40, AAdv_B40,
				PLAYER_A_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void opponentWinsAfterAdv() {
		List<String> scores = gamePlay.playGame(BABABABB);
		List<String> expectedScoresList = List.of(A00_B15, A15_B15, A15_B30, A30_B30, A30_B40, A40_B40, A40_BAdv,
				PLAYER_B_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void extraInput() {
		List<String> scores = gamePlay.playGame(AAAAAA);
		List<String> expectedScoresList = List.of(A15_B00, A30_B00, A40_B00, PLAYER_A_WINS);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

	@Test
	void longGame() {
		List<String> scores = gamePlay.playGame(ABABABABBA);
		List<String> expectedScoresList = List.of(A15_B00, A15_B15, A30_B15, A30_B30, A40_B30, A40_B40, AAdv_B40,
				A40_BAdv, AAdv_B40, A40_BAdv);

		assertThat(scores).isNotEmpty().hasSize(expectedScoresList.size()).hasSameElementsAs(expectedScoresList);
	}

}
