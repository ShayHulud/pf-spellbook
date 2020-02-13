package ru.shayhulud.pfspellbook.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping
@RequiredArgsConstructor
@Api(value = "/api/spell", tags = {"spell"})
public class SpellCardController {

	private final SpellCardConverter spellCardConverter;
	private final SpellService spellService;

	@GetMapping("/api/spell/{id}/render")
	@ApiOperation("Render spell into 'spell-card' jsp")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success",
			response = String.class
		),
		@ApiResponse(
			code = 404,
			message = "Spell with this id not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public String example(Model model, @PathVariable("id") Long id) throws NotFoundException {
		Spell spell = this.spellService.getById(id);
		SpellCardRenderDTO renderDTO = this.spellCardConverter.convertSpellForRender(spell);
		model.addAttribute("spell", renderDTO);
		return "spell-card";
	}
}
