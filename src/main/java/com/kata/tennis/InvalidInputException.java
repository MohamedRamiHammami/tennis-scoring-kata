package com.kata.tennis;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = -357151208839789563L;

	private final String message;

	@Override
	public String getMessage() {
		return message;
	}

}
