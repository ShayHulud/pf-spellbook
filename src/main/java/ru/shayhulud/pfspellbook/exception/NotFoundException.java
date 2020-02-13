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

	public NotFoundException(String text) {
		super(ERROR_TEXT, text);
	}

	protected NotFoundException(Set<String> troubleElements) {
		super(ERROR_TEXT, (String[]) troubleElements.toArray());
	}
}
