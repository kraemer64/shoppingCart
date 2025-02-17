package com.springprojects.shoppingcart.exceptions;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * Version id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message contains the message.
	 */
	public ProductNotFoundException(final String message) {
		super(message);
	}
}
