package ru.shayhulud.pfspellbook.service.converter;

import org.springframework.stereotype.Service;
import ru.shayhulud.pfspellbook.common.behavior.Converter;
import ru.shayhulud.pfspellbook.common.util.DateUtils;
import ru.shayhulud.pfspellbook.domain.dto.SpellClassRankDTO;
import ru.shayhulud.pfspellbook.domain.model.SpellClassRank;

/**
 * DTO converter for SpellClassRank entity.
 */
@Service
public class SpellClassRankConverter implements Converter<SpellClassRank, SpellClassRankDTO> {

	public SpellClassRank fromDTO(SpellClassRankDTO dto) {
		SpellClassRank spellClassRank = new SpellClassRank();
		spellClassRank.setId(dto.getId());
		spellClassRank.setPlayClass(dto.getPlayClass());
		spellClassRank.setRank(dto.getRank());
		return spellClassRank;
	}

	public SpellClassRankDTO toDTO(SpellClassRank spellClassRank) {
		SpellClassRankDTO dto = new SpellClassRankDTO();
		dto.setId(spellClassRank.getId());
		dto.setCreatedAt(DateUtils.fromDate(spellClassRank.getCreatedAt()));
		dto.setUpdatedAt(DateUtils.fromDate(spellClassRank.getUpdatedAt()));
		dto.setPlayClass(spellClassRank.getPlayClass());
		dto.setRank(spellClassRank.getRank());
		dto.setSpellId(spellClassRank.getSpell().getId());
		return dto;
	}
}
