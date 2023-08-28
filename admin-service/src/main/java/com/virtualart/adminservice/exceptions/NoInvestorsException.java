package com.virtualart.adminservice.exceptions;

public class NoInvestorsException extends Exception {
	private static final long serialVersionUID = 1L;
	public NoInvestorsException(String message) {
		super(message);
	}
}
