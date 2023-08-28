package com.virtualart.authentication.exceptions;
public class NotValidRoleException extends Exception {
	private static final long serialVersionUID = 1L;
	public NotValidRoleException(String message) {
		super(message);
	}
}
