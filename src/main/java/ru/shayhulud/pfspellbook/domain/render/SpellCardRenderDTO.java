package ru.shayhulud.pfspellbook.domain.render;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shayhulud.pfspellbook.domain.model.SpellClassRank;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DTO for rendering card.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellCardRenderDTO implements Serializable {

	private List<SpellClassRank> classRanks;
	private String name;
	private Map<String, String> parameters;
	private String description;
}
