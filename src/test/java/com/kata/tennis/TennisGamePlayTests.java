package com.kata.tennis;

import static com.kata.tennis.TestsConstant.A00_B15;
import static com.kata.tennis.TestsConstant.A00_B30;
import static com.kata.tennis.TestsConstant.A00_B40;
import static com.kata.tennis.TestsConstant.A15_B00;
import static com.kata.tennis.TestsConstant.A15_B15;
import static com.kata.tennis.TestsConstant.A15_B30;
import static com.kata.tennis.TestsConstant.A30_B00;
import static com.kata.tennis.TestsConstant.A30_B15;
import static com.kata.tennis.TestsConstant.A30_B30;
import static com.kata.tennis.TestsConstant.A30_B40;
import static com.kata.tennis.TestsConstant.A40_B00;
import static com.kata.tennis.TestsConstant.A40_B30;
import static com.kata.tennis.TestsConstant.A40_B40;
import static com.kata.tennis.TestsConstant.A40_BAdv;
import static com.kata.tennis.TestsConstant.AAAA;
import static com.kata.tennis.TestsConstant.AAAAAA;
import static com.kata.tennis.TestsConstant.AABCAB;
import static com.kata.tennis.TestsConstant.AAdv_B40;
import static com.kata.tennis.TestsConstant.ABABAA;
import static com.kata.tennis.TestsConstant.ABABABA;
import static com.kata.tennis.TestsConstant.ABABABAA;
import static com.kata.tennis.TestsConstant.ABABABABBA;
import static com.kata.tennis.TestsConstant.BABABAB;
import static com.kata.tennis.TestsConstant.BABABABB;
import static com.kata.tennis.TestsConstant.BABABB;
import static com.kata.tennis.TestsConstant.BBBB;
import static com.kata.tennis.TestsConstant.PLAYER_A_WINS;
import static com.kata.tennis.TestsConstant.PLAYER_B_WINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kata.tennis.displayer.ScoreDiplay;
import com.kata.tennis.displayer.ScoreDisplayerImpl;

class TennisGamePlayTests {

	@Mock
	private ScoreDisplayerImpl displayer;

	@InjectMocks
	private TennisGamePlayImpl gamePlay;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void emptyInput() {

		List<String> scores = gamePlay.playGame("");
		assertThat(scores).isEmpty();
	}

	@Test
	void invalidInput() {
		when(displayer.displayScore(any())).thenReturn(new ScoreDiplay(""));

		InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
			gamePlay.playGame(AABCAB);
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
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A30_B00),
				new ScoreDiplay(A40_B00), new ScoreDiplay(PLAYER_A_WINS, true));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(AAAA);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void cleanWinOpponent() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A00_B15), new ScoreDiplay(A00_B30),
				new ScoreDiplay(A00_B40), new ScoreDiplay(PLAYER_B_WINS, true));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(BBBB);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void playerWinTo30() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A30_B15), new ScoreDiplay(A30_B30), new ScoreDiplay(A40_B30),
				new ScoreDiplay(PLAYER_A_WINS, true));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(ABABAA);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void opponentWinTo30() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A00_B15), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A15_B30), new ScoreDiplay(A30_B30), new ScoreDiplay(A30_B40),
				new ScoreDiplay(PLAYER_B_WINS, true));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(BABABB);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void advPlayer() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A30_B15), new ScoreDiplay(A30_B30), new ScoreDiplay(A40_B30), new ScoreDiplay(A40_B40),
				new ScoreDiplay(AAdv_B40));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(ABABABA);

		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void advOpponent() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A00_B15), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A15_B30), new ScoreDiplay(A30_B30), new ScoreDiplay(A30_B40), new ScoreDiplay(A40_B40),
				new ScoreDiplay(A40_BAdv));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(BABABAB);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void playertWinsAfterAdv() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A30_B15), new ScoreDiplay(A30_B30), new ScoreDiplay(A40_B30), new ScoreDiplay(A40_B40),
				new ScoreDiplay(AAdv_B40), new ScoreDiplay(PLAYER_A_WINS));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(ABABABAA);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void opponentWinsAfterAdv() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A00_B15), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A15_B30), new ScoreDiplay(A30_B30), new ScoreDiplay(A30_B40), new ScoreDiplay(A40_B40),
				new ScoreDiplay(A40_BAdv), new ScoreDiplay(PLAYER_B_WINS));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(BABABABB);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void extraInput() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A30_B00),
				new ScoreDiplay(A40_B00), new ScoreDiplay(PLAYER_A_WINS, true));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(AAAAAA);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

	@Test
	void longGame() {
		List<ScoreDiplay> scoresDisplay = List.of(new ScoreDiplay(A15_B00), new ScoreDiplay(A15_B15),
				new ScoreDiplay(A30_B15), new ScoreDiplay(A30_B30), new ScoreDiplay(A40_B30), new ScoreDiplay(A40_B40),
				new ScoreDiplay(AAdv_B40), new ScoreDiplay(A40_B40), new ScoreDiplay(A40_BAdv),
				new ScoreDiplay(A40_B40));
		AtomicInteger invocationCount = new AtomicInteger(0);
		when(displayer.displayScore(any())).thenAnswer(invocation -> {
			return scoresDisplay.get(invocationCount.getAndIncrement());
		});

		List<String> scores = gamePlay.playGame(ABABABABBA);
		List<String> expectedScores = scoresDisplay.stream().map(ScoreDiplay::printedScore).toList();
		assertThat(scores).isNotEmpty().hasSize(scoresDisplay.size()).hasSameElementsAs(expectedScores);
	}

}
