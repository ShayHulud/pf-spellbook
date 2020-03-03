package ru.shayhulud.pfspellbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shayhulud.pfspellbook.domain.model.Spellbook;
import ru.shayhulud.pfspellbook.domain.render.SpellbookRenderDTO;

import java.util.stream.Collectors;

/**
 * Renderer for Spellbook.
 */
@Service
@RequiredArgsConstructor
public class SpellbookRenderer {

	private final SpellCardRenderer spellCardRenderer;

	@Transactional
	public SpellbookRenderDTO convertSpellbookForRender(Spellbook spellbook) {

		SpellbookRenderDTO dto = new SpellbookRenderDTO();
		dto.setName(spellbook.getName());
		dto.setSpells(spellbook.getSpells().stream()
			.map(this.spellCardRenderer::convertSpellForRender)
			.collect(Collectors.toList())
		);
		return dto;
	}
}
