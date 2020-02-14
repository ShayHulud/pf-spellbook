package ru.shayhulud.pfspellbook.domain.dto.spellbook;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for updating spellbook params.
 */
@Data
public class UpdateSpellbookDTO implements Serializable {

	private Long id;
	private String name;
	private Set<String> spellNames;
}
