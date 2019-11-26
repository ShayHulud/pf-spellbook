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
	PRIEST("жрец");

	@Getter
	private final String ruName;
}
