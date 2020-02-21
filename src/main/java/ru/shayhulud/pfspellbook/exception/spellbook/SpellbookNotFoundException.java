package ru.shayhulud.pfspellbook.exception.spellbook;

import ru.shayhulud.pfspellbook.exception.NotFoundException;

/**
 * NotFound exception for spellbook with pre-defined model name for message.
 */
public class SpellbookNotFoundException extends NotFoundException {

	protected static final String MODEL_NAME = "spellbook";

	public SpellbookNotFoundException() {
		super(MODEL_NAME);
	}
}
