package ru.shayhulud.pfspellbook.exception.spell;

import ru.shayhulud.pfspellbook.exception.APIException;

/**
 * Spell Creation API exception.
 */
public class SpellCreationException extends APIException {

	/**
	 * Error message.
	 */
	public static final String ERROR_TEXT = "spell_creation_error";

	public SpellCreationException() {
		super(ERROR_TEXT);
	}

	public SpellCreationException(String additionalMessage) {
		super(ERROR_TEXT, additionalMessage);
	}
}
