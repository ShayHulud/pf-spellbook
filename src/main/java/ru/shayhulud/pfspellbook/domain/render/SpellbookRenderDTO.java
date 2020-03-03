package ru.shayhulud.pfspellbook.domain.render;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for rendering card.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellbookRenderDTO implements Serializable {

	private List<SpellCardRenderDTO> spells;
	private String name;
}
