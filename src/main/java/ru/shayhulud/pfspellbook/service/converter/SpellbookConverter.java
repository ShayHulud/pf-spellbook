package ru.shayhulud.pfspellbook.service.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shayhulud.pfspellbook.common.behavior.Converter;
import ru.shayhulud.pfspellbook.common.util.DateUtils;
import ru.shayhulud.pfspellbook.domain.dto.SpellbookDTO;
import ru.shayhulud.pfspellbook.domain.model.Spellbook;

/**
 * DTO converter for Spellbook entity.
 */
@Service
@RequiredArgsConstructor
public class SpellbookConverter implements Converter<Spellbook, SpellbookDTO> {

	private final SpellConverter spellConverter;

	@Override
	public Spellbook fromDTO(SpellbookDTO dto) {
		Spellbook spellbook = new Spellbook();
		spellbook.setId(dto.getId());
		spellbook.setName(dto.getName());
		dto.getSpells().stream().
			map(this.spellConverter::fromDTO)
			.forEach(_spell -> spellbook.getSpells().add(_spell));
		return spellbook;
	}

	@Override
	public SpellbookDTO toDTO(Spellbook entity) {
		SpellbookDTO dto = new SpellbookDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreatedAt(DateUtils.fromDate(entity.getCreatedAt()));
		dto.setUpdatedAt(DateUtils.fromDate(entity.getUpdatedAt()));
		entity.getSpells().stream()
			.map(this.spellConverter::toDTO)
			.forEach(_spellDTO -> dto.getSpells().add(_spellDTO));
		return dto;
	}
}
