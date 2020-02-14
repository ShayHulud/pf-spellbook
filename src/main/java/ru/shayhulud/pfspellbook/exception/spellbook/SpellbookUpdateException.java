package ru.shayhulud.pfspellbook.exception.spellbook;

import ru.shayhulud.pfspellbook.exception.APIException;

/**
 * Spellbook Update API exception.
 */
public class SpellbookUpdateException extends APIException {

	/**
	 * Error message.
	 */
	public static final String ERROR_TEXT = "spellbook_update_error";

	public SpellbookUpdateException() {
		super(ERROR_TEXT);
	}

	public SpellbookUpdateException(String additionalMessage) {
		super(ERROR_TEXT, additionalMessage);
	}
}
