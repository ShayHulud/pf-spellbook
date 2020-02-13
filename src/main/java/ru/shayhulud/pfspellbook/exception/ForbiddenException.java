package ru.shayhulud.pfspellbook.exception;

import java.util.Set;

/**
 * Forbidden API exception.
 */
public class ForbiddenException extends APIException {

	/**
	 * Error message.
	 */
	public static final String ERROR_TEXT = "forbidden";

	public ForbiddenException() {
		super(ERROR_TEXT);
	}

	public ForbiddenException(String text) {
		super(ERROR_TEXT, text);
	}

	protected ForbiddenException(Set<String> troubleElements) {
		super(ERROR_TEXT, (String[]) troubleElements.toArray());
	}
}
