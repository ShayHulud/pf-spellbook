package ru.shayhulud.pfspellbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * DTO for Spellbook entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellbookDTO implements Serializable {

	private Long id;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;

	private String name;
	private Set<SpellDTO> spells = new TreeSet<>(Comparator.comparing(SpellDTO::getName));
}
