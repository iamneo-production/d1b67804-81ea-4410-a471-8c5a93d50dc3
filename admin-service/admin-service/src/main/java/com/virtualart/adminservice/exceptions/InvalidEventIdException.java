package com.virtualart.adminservice.exceptions;

public class InvalidEventIdException extends Exception {
	private static final long serialVersionUID = 1L;
	public  InvalidEventIdException(String message) {
		super(message);
	}
}
