package com.kata.tennis;

import java.util.List;

public interface TennisGamePlay {

	public List<String> playGame(String sequence) throws InvalidInputException;

}