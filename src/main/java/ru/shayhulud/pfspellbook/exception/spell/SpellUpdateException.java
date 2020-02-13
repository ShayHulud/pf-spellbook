package ru.shayhulud.pfspellbook.exception.spell;

import ru.shayhulud.pfspellbook.exception.APIException;

/**
 * Spell Update API exception.
 */
public class SpellUpdateException extends APIException {

	/**
	 * Error message.
	 */
	public static final String ERROR_TEXT = "spell_update_error";

	public SpellUpdateException() {
		super(ERROR_TEXT);
	}

	public SpellUpdateException(String additionalMessage) {
		super(ERROR_TEXT, additionalMessage);
	}
}
