package com.springprojects.shoppingcart.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * Version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message contains the message.
	 */
	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
