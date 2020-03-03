package ru.shayhulud.pfspellbook.controller.web;

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
import ru.shayhulud.pfspellbook.domain.model.Spellbook;
import ru.shayhulud.pfspellbook.domain.render.SpellbookRenderDTO;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.service.SpellbookRenderer;
import ru.shayhulud.pfspellbook.service.SpellbookService;

/**
 * API for card generating.
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
@Api(value = "/api/spellbook", tags = {"spellbook"})
public class SpellbookController {

	private final SpellbookRenderer spellbookRenderer;
	private final SpellbookService spellbookService;

	@GetMapping("/api/spellbook/{id}/render")
	@ApiOperation("Render spellbook into 'spellbook' jsp")
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
		Spellbook spellbook = this.spellbookService.getEntityById(id);
		SpellbookRenderDTO render = this.spellbookRenderer.convertSpellbookForRender(spellbook);
		model.addAttribute("spellbook", render);
		return "spellbook";
	}
}
