package ru.shayhulud.pfspellbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shayhulud.pfspellbook.domain.enumiration.MagicSchool;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

/**
 * DTO for Spell entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellDTO implements Serializable {

	private Long id;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;

	private String name;
	private MagicSchool school;
	private String subSchool;
	private List<SpellClassRankDTO> classRanks;
	private String castTime;
	private Set<SpellComponentDTO> components;
	private String distance;
	private String effect;
	private String target;
	private String duration;
	private String savingThrow;
	private boolean resistable;
	private String description;
}
