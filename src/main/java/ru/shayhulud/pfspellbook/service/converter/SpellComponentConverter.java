package ru.shayhulud.pfspellbook.service.converter;

import org.springframework.stereotype.Service;
import ru.shayhulud.pfspellbook.common.behavior.Converter;
import ru.shayhulud.pfspellbook.common.util.DateUtils;
import ru.shayhulud.pfspellbook.domain.dto.SpellComponentDTO;
import ru.shayhulud.pfspellbook.domain.model.SpellComponent;

/**
 * DTO converter for SpellComponent entity.
 */
@Service
public class SpellComponentConverter implements Converter<SpellComponent, SpellComponentDTO> {

	public SpellComponent fromDTO(SpellComponentDTO dto) {
		SpellComponent spellComponent = new SpellComponent();
		spellComponent.setId(dto.getId());
		spellComponent.setComponent(dto.getComponent());
		spellComponent.setReagent(dto.getReagent());
		return spellComponent;
	}

	public SpellComponentDTO toDTO(SpellComponent spellComponent) {
		SpellComponentDTO dto = new SpellComponentDTO();
		dto.setId(spellComponent.getId());
		dto.setComponent(spellComponent.getComponent());
		dto.setReagent(spellComponent.getReagent());
		dto.setSpellId(spellComponent.getSpell().getId());
		dto.setCreatedAt(DateUtils.fromDate(spellComponent.getCreatedAt()));
		dto.setUpdatedAt(DateUtils.fromDate(spellComponent.getUpdatedAt()));
		return dto;
	}
}
