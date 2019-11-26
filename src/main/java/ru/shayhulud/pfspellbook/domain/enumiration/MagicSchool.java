package ru.shayhulud.pfspellbook.domain.enumiration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Magic school.
 */
@AllArgsConstructor
public enum MagicSchool {

	DESTRUCTION("разрушение");

	@Getter
	private final String ruName;
}
