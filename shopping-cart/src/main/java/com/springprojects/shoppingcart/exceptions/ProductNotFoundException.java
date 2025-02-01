package com.springprojects.shoppingcart.exceptions;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(final String message) {
		super(message);
	}
}
