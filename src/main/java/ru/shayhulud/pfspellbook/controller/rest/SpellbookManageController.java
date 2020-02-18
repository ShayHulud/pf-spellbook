package ru.shayhulud.pfspellbook.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.SpellbookDTO;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.service.SpellbookService;

/**
 * REST controller for spellbooks additional managing.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "/api/spellbook", tags = {"spellbook"})
public class SpellbookManageController {

	private final SpellbookService spellbookService;

	@PostMapping("/api/spellbook/{id}/add/{spellName}")
	@ApiOperation("Add spell to spellbook")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 404,
			message = "Entities with this params not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellbookDTO addSpellToSpellbook(@PathVariable("id") Long sbId, @PathVariable("spellName") String spellName)
		throws NotFoundException {

		log.debug("REST request to add Spell {} to Spellbook : {}", spellName, sbId);
		return this.spellbookService.addSpellToSpellbook(sbId, spellName);
	}

	@PostMapping("/api/spellbook/{id}/remove/{spellName}")
	@ApiOperation("Remove spell from spellbook")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 404,
			message = "Spellbook with this id not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellbookDTO removeSpellFromSpellbook(@PathVariable("id") Long sbId,
	                                             @PathVariable("spellName") String spellName)
		throws NotFoundException {

		log.debug("REST request to remove Spell {} from Spellbook : {}", spellName, sbId);
		return this.spellbookService.removeSpellFromSpellbook(sbId, spellName);
	}
}
