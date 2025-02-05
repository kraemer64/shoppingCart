package com.springprojects.shoppingcart.exceptions;

public class AlreadyExistsException extends RuntimeException{

	/**
	 * Version id.
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Default constructor.
	 */
	public AlreadyExistsException (final String message) {
		super(message);
	}
}
