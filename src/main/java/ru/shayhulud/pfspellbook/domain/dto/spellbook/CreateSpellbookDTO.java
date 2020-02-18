package ru.shayhulud.pfspellbook.domain.dto.spellbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for spellbook creation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpellbookDTO implements Serializable {

	private String name;
}
