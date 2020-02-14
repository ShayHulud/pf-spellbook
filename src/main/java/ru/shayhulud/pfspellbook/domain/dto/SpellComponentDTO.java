package ru.shayhulud.pfspellbook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shayhulud.pfspellbook.domain.enumiration.Component;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for SpellComponent entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellComponentDTO implements Serializable {

	private Long id;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;

	private Long spellId;
	private Component component;
	private String reagent;
}
