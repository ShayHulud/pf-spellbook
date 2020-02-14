package ru.shayhulud.pfspellbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shayhulud.pfspellbook.domain.enumiration.PlayClass;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for SpellClassRank entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellClassRankDTO implements Serializable {

	private Long id;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;

	private Long spellId;
	private PlayClass playClass;
	private Integer rank;
}
