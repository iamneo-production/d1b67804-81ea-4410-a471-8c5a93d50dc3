package com.virtualart.bankservice.exceptions;

public class AccountDoesNotExistsException extends Exception {
	private static final long serialVersionUID = 1L;
	public AccountDoesNotExistsException(String message) {
		super(message);
	}
}
