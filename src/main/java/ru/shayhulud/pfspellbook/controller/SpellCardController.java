package ru.shayhulud.pfspellbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.render.SpellCardRenderDTO;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.service.SpellCardConverter;
import ru.shayhulud.pfspellbook.service.SpellService;

/**
 * API for card generating.
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SpellCardController {

	private final SpellCardConverter spellCardConverter;
	private final SpellService spellService;

	//TODO:Swagger
	@GetMapping("/render/{id}")
	public String example(Model model, @PathVariable("id") Long id) throws NotFoundException {
		Spell spell = this.spellService.getById(id);
		SpellCardRenderDTO renderDTO = this.spellCardConverter.convertSpellForRender(spell);
		model.addAttribute("spell", renderDTO);
		return "spell-card";
	}
}
