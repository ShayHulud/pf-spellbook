package ru.shayhulud.pfspellbook.exception.spell;

import ru.shayhulud.pfspellbook.exception.NotFoundException;

/**
 * NotFound exception for spell with pre-defined model name for message.
 */
public class SpellNotFoundException extends NotFoundException {

	protected static final String MODEL_NAME = "spell";

	public SpellNotFoundException() {
		super(MODEL_NAME);
	}
}
