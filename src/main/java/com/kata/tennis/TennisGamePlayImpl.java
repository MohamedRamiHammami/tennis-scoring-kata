package com.kata.tennis;

import java.util.ArrayList;
import java.util.List;

import com.kata.tennis.displayer.ScoreDiplay;
import com.kata.tennis.displayer.ScoreDisplayer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TennisGamePlayImpl implements TennisGamePlay {

	private final ScoreDisplayer displayer;

	public List<String> playGame(String sequence) throws InvalidInputException {
		System.out.println("\n" + sequence);
		TennisGamePoint game = new TennisGamePoint(PlayerName.A, PlayerName.B);
		List<String> scores = new ArrayList<>();

		for (char c : sequence.toCharArray()) {
			game.updateScore(c);

			ScoreDiplay score = displayer.displayScore(game);
			scores.add(score.printedScore());
			if (score.finalPoint())
				break;
		}
		scores.stream().forEach(System.out::println);
		return scores;
	}

}