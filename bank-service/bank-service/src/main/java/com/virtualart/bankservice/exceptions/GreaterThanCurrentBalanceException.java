package com.virtualart.bankservice.exceptions;
public class GreaterThanCurrentBalanceException extends Exception {
	private static final long serialVersionUID = 1L;
	public GreaterThanCurrentBalanceException(String message) {
		super(message);
	}
}
