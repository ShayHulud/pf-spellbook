package ru.shayhulud.pfspellbook.domain.enumiration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Magic school.
 */
@AllArgsConstructor
public enum MagicSchool {

	/**
	 * Destruction school.
	 */
	DESTRUCTION("разрушение"),
	/**
	 * Illusion school.
	 */
	ILLUSION("иллюзия"),
	/**
	 * Embodiment school.
	 */
	EMBODIMENT("воплощение"),
	/**
	 * Necromancy school.
	 */
	NECROMANCY("некромантия"),
	/**
	 * Charm school.
	 */
	CHARM("очарование"),
	/**
	 * Transmutation school.
	 */
	TRANSMUTATION("превращение"),
	/**
	 * Obstruction school.
	 */
	OBSTRUCTION("преграждение"),
	/**
	 * Prophecy school.
	 */
	PROPHECY("прорицания");

	@Getter
	private final String ruName;
}
