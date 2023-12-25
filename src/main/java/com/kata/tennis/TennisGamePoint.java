package com.kata.tennis;

import lombok.Data;

@Data
public class TennisGamePoint {

	private int playerScore = 0;
	private int opponentScore = 0;
	private PlayerName playerName;
	private PlayerName opponentName;

	public TennisGamePoint(PlayerName playerName, PlayerName opponentName) {
		this.playerName = playerName;
		this.opponentName = opponentName;
	}

	public void updateScore(char c) {
		if (c == PlayerName.A.value()) {
			playerScores();
		} else if (c == PlayerName.B.value()) {
			opponentScores();
		} else
			throw new InvalidInputException("Invalid player Name " + c);
	}

	public boolean hasWinner() {
		return isPlayerWinner() || isOpponentWinner();
	}

	private boolean isPlayerWinner() {
		return playerScore >= 4 && playerScore >= opponentScore + 2;
	}

	private boolean isOpponentWinner() {
		return opponentScore >= 4 && opponentScore >= playerScore + 2;
	}

	public boolean hasAdvantage() {
		return isPlayerAdvantaged() || isOpponentAdvantaged();
	}

	private boolean isPlayerAdvantaged() {
		return playerScore >= 4 && playerScore == opponentScore + 1;
	}

	private boolean isOpponentAdvantaged() {
		return opponentScore >= 4 && opponentScore == playerScore + 1;
	}

	public String playerWithHighestScore() {
		if (getPlayerScore() > getOpponentScore()) {
			return String.valueOf(getPlayerName());
		} else {
			return String.valueOf(getOpponentName());
		}
	}

	public void playerScores() {
		playerScore++;
	}

	public void playerScores(Integer points) {
		playerScore += points;
	}

	public void opponentScores() {
		opponentScore++;
	}

	public void opponentScores(Integer points) {
		opponentScore += points;
	}

}