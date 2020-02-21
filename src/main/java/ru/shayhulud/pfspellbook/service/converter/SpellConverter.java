package ru.shayhulud.pfspellbook.service.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shayhulud.pfspellbook.common.util.DateUtils;
import ru.shayhulud.pfspellbook.domain.dto.SpellDTO;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.model.SpellClassRank;
import ru.shayhulud.pfspellbook.domain.model.SpellComponent;

import java.util.stream.Collectors;

/**
 * DTO converter for spell entity.
 */
@Service
@RequiredArgsConstructor
public class SpellConverter {

	private final SpellComponentConverter spellComponentConverter;
	private final SpellClassRankConverter spellClassRankConverter;

	public Spell fromDTO(SpellDTO dto) {
		Spell spell = new Spell();
		spell.setId(dto.getId());
		this.copySimpleSpellParams(dto, spell);
		dto.getClassRanks()
			.forEach(_cr -> {
				SpellClassRank _scr = this.spellClassRankConverter.fromDTO(_cr);
				spell.addClassRank(_scr);
			});

		dto.getComponents()
			.forEach(_cr -> {
				SpellComponent _sc = this.spellComponentConverter.fromDTO(_cr);
				spell.addComponent(_sc);
			});
		return spell;
	}

	public SpellDTO toDTO(Spell spell) {
		SpellDTO dto = new SpellDTO();
		dto.setId(spell.getId());
		dto.setName(spell.getName());
		dto.setCastTime(spell.getCastTime());
		dto.setCreatedAt(DateUtils.fromDate(spell.getCreatedAt()));
		dto.setUpdatedAt(DateUtils.fromDate(spell.getUpdatedAt()));
		dto.setDescription(spell.getDescription());
		dto.setDistance(spell.getDistance());
		dto.setDuration(spell.getDuration());
		dto.setEffect(spell.getEffect());
		dto.setResistable(spell.isResistable());
		dto.setSavingThrow(spell.getSavingThrow());
		dto.setSchool(spell.getSchool());
		dto.setSubSchool(spell.getSubSchool());
		dto.setTarget(spell.getTarget());
		dto.setClassRanks(
			spell.getClassRanks().stream()
				.map(this.spellClassRankConverter::toDTO)
				.collect(Collectors.toList())
		);
		dto.setComponents(
			spell.getComponents().stream()
				.map(this.spellComponentConverter::toDTO)
				.collect(Collectors.toSet())
		);
		return dto;
	}

	public Spell copySimpleSpellParams(SpellDTO dto, Spell spell) {
		spell.setName(dto.getName());
		spell.setCastTime(dto.getCastTime());
		spell.setDescription(dto.getDescription());
		spell.setDistance(dto.getDistance());
		spell.setDuration(dto.getDuration());
		spell.setEffect(dto.getEffect());
		spell.setResistable(dto.isResistable());
		spell.setSavingThrow(dto.getSavingThrow());
		spell.setSchool(dto.getSchool());
		spell.setSubSchool(dto.getSubSchool());
		spell.setTarget(dto.getTarget());
		return spell;
	}
}
