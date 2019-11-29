package ru.shayhulud.pfspellbook.domain.enumiration;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Playing Class.
 */
@AllArgsConstructor
public enum PlayClass {

	/**
	 * Priest.
	 */
	PRIEST("жрец"),
	/**
	 * Bard.
	 */
	BARD("бард"),
	/**
	 * Barbarian.
	 */
	BARBARIAN("варвар"),
	/**
	 * Warrior.
	 */
	WARRIOR("воин"),
	/**
	 * Wizard.
	 */
	WIZARD("волшебник"),
	/**
	 * Druid.
	 */
	DRUID("друид"),
	/**
	 * Monk.
	 */
	MONK("монах"),
	/**
	 * Paladin.
	 */
	PALADIN("паладин"),
	/**
	 * Rouge.
	 */
	ROUGE("плут"),
	/**
	 * Ranger.
	 */
	RANGER("следопыт"),
	/**
	 * Sorcerer.
	 */
	SORCERER("чародей");

	@Getter
	private final String ruName;
}
