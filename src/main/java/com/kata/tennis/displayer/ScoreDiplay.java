package com.kata.tennis.displayer;

public record ScoreDiplay(String printedScore, boolean finalPoint) {

	public ScoreDiplay(String printedScore) {
		this(printedScore, false);
	}

}
