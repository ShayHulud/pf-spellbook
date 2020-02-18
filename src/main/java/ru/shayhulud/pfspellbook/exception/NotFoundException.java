package ru.shayhulud.pfspellbook.exception;

import java.util.Set;

/**
 * Not Found API exception.
 */
public class NotFoundException extends APIException {

	/**
	 * Error message.
	 */
	public static final String ERROR_TEXT = "not_found";

	protected static final String MODEL_NAME = "object";

	protected NotFoundException(String modelName) {
		super(ERROR_TEXT, modelName);
	}

	protected NotFoundException(Set<String> troubleElements) {
		super(ERROR_TEXT, (String[]) troubleElements.toArray());
	}
}
