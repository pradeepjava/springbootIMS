package com.ims.exception;

public class ObjectAlreadyExistsInDBException extends RuntimeException {

	private static final long serialVersionUID = 343962749141277288L;

	public ObjectAlreadyExistsInDBException(final String message) {
		super(message);
	}
}
