package ru.shayhulud.pfspellbook.domain.enumiration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Magic school.
 */
@AllArgsConstructor
public enum Component {

	/**
	 * Word component.
	 */
	WORD("С"),
	/**
	 * Gesture component.
	 */
	GESTURE("Ж"),
	/**
	 * Reagent component.
	 */
	REAGENT("Р"),
	/**
	 * Focus item component.
	 */
	FOCUS_ITEM("Ф"),
	/**
	 * Sacral focus item component.
	 */
	SACRAL_FOCUS_ITEM("СФ");

	@Getter
	private final String ruName;
}
