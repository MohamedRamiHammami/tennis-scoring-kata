package com.kata.tennis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class TennisScoringTest {

	@Test
	void GivenEmptyReturnEmpty() {
		List<String> scores = TennisScoring.calculateScore("");
		assertThat(scores).isNotNull().isEmpty();
	}

}
