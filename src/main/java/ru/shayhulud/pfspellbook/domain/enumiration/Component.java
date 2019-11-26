package ru.shayhulud.pfspellbook.domain.enumiration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Magic school.
 */
@AllArgsConstructor
public enum Component {

	WORD("С");

	@Getter
	private final String ruName;
}
