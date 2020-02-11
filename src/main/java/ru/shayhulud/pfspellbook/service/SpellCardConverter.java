package ru.shayhulud.pfspellbook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.render.SpellCardRenderDTO;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Converter spell to spellCard.
 */
@Service
public class SpellCardConverter {

	@Transactional
	public SpellCardRenderDTO convertSpellForRender(Spell spell) {
		SpellCardRenderDTO spellRender = new SpellCardRenderDTO();

		spellRender.setName(spell.getName());
		spellRender.setClassRanks(spell.getClassRanks());
		spellRender.setDescription(spell.getDescription());
		spellRender.setParameters(this.convertSpellParams(spell));

		return spellRender;
	}

	//TODO: Localization
	private Map<String, String> convertSpellParams(Spell spell) {
		Map<String, String> params = new LinkedHashMap<>();
		StringBuilder schoolSB = new StringBuilder(spell.getSchool().getRuName());
		if (spell.getSubSchool() != null && !spell.getSubSchool().isEmpty()) {
			schoolSB.append(" (").append(spell.getSubSchool()).append(")");
		}
		params.put("Школа", schoolSB.toString());
		params.put("Время сотворения", spell.getCastTime());
		if (!spell.getComponents().isEmpty()) {
			String components = String.join(",",
				spell.getComponents().stream()
					.map(_spellComponent -> {
						StringBuilder _sb = new StringBuilder(_spellComponent.getComponent().getRuName());
						if (_spellComponent.getReagent() != null && !_spellComponent.getReagent().isEmpty()) {
							_sb.append("(").append(_spellComponent.getReagent()).append(")");
						}
						return _sb.toString();
					}).collect(Collectors.toSet())
			);
			params.put("Компоненты", components);
		}
		params.put("Дистанция", spell.getDistance());
		if (spell.getEffect() != null && !spell.getEffect().isEmpty()) {
			params.put("Эффект", spell.getEffect());
		}
		if (spell.getTarget() != null && !spell.getTarget().isEmpty()) {
			params.put("Цель", spell.getTarget());
		}
		if (spell.getDuration() != null && !spell.getDuration().isEmpty()) {
			params.put("Длительность", spell.getDuration());
		}
		if (spell.getSavingThrow() != null && !spell.getSavingThrow().isEmpty()) {
			params.put("Спасбросок", spell.getSavingThrow());
		}
		params.put("Устойчивость к магии", spell.isResistable() ? "Да" : "Нет");
		return params;
	}
}
