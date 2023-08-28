package com.virtualart.sponsorservice.exceptions;

public class FeignExceptions extends Exception{
	private static final long serialVersionUID = 1L;
	public FeignExceptions(String message) {
		super(message);
	}
}
