package com.kata.tennis;

public enum GameScore {
	P0(0), P15(15), P30(30), P40(40), ADVANTAGE(45), WIN(50);

	Integer value;

	GameScore(Integer value) {
		this.value = value;
	}
}
