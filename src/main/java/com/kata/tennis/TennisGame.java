package com.kata.tennis;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TennisGame {
	private Player player;
	private Player opponent;
	private List<String> scores = new ArrayList<>();

	public TennisGame(Player player, Player opponent) {
		this.player = player;
		this.opponent = opponent;
	}

	public void updateScorePlayer() {
		// TODO Auto-generated method stub

	}

	public void updateScoreOpponent() {
		// TODO Auto-generated method stub

	}

	public void displayScore() {
		// TODO Auto-generated method stub

	}
}