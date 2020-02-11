package ru.shayhulud.pfspellbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shayhulud.pfspellbook.domain.enumiration.Component;
import ru.shayhulud.pfspellbook.domain.enumiration.MagicSchool;
import ru.shayhulud.pfspellbook.domain.enumiration.PlayClass;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.model.SpellClassRank;
import ru.shayhulud.pfspellbook.domain.model.SpellComponent;
import ru.shayhulud.pfspellbook.domain.render.SpellCardRenderDTO;
import ru.shayhulud.pfspellbook.service.SpellCardConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * API for card generating.
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SpellCardController {

	private final SpellCardConverter spellCardConverter;

	@GetMapping("/hello")
	public String example(Model model) {

		Spell spell = new Spell(
			1L, "spellname", MagicSchool.CHARM, "Приворот", null, "1 ОД", null,
			"1", "Взрыв", "Все враги в области радиусом 10 футов", "10 раундов", "Воля", true, "example description"

		);
		List<SpellClassRank> spellClassRanks = new LinkedList<>(Arrays.asList(
			new SpellClassRank(2L, spell, PlayClass.PRIEST, 5),
			new SpellClassRank(3L, spell, PlayClass.DRUID, 2),
			new SpellClassRank(3L, spell, PlayClass.BARBARIAN, 2),
			new SpellClassRank(3L, spell, PlayClass.BARD, 2)
//			new SpellClassRank(3L, spell, PlayClass.MONK, 2),
//			new SpellClassRank(3L, spell, PlayClass.PALADIN, 2),
//			new SpellClassRank(3L, spell, PlayClass.RANGER, 2),
//			new SpellClassRank(3L, spell, PlayClass.SORCERER, 2),
//			new SpellClassRank(3L, spell, PlayClass.ROUGE, 2),
//			new SpellClassRank(3L, spell, PlayClass.WARRIOR, 2),
//			new SpellClassRank(3L, spell, PlayClass.WIZARD, 2)
		));
		Set<SpellComponent> spellComponents = new HashSet<>(Arrays.asList(
			new SpellComponent(3L, spell, Component.FOCUS_ITEM, "слиторк платины"),
			new SpellComponent(3L, spell, Component.GESTURE, null),
			new SpellComponent(3L, spell, Component.WORD, null)
		));
		spell.setClassRanks(spellClassRanks);
		spell.setComponents(spellComponents);

		SpellCardRenderDTO renderDTO = this.spellCardConverter.convertSpellForRender(spell);

		model.addAttribute("spell", renderDTO);
		model.addAttribute("playClasses", PlayClass.values());
		return "spell-card";
	}
}
