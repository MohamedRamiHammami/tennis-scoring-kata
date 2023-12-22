package com.kata.tennis;

import lombok.Data;

@Data
public class Player {
	private String name;
	private GameScore score;

	public Player(String name) {
		this.name = name;
		this.score = GameScore.P0;
	}
}