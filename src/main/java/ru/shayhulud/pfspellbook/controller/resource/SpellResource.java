package ru.shayhulud.pfspellbook.controller.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.shayhulud.pfspellbook.domain.dto.SpellDTO;
import ru.shayhulud.pfspellbook.exception.APIException;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.exception.spell.SpellCreationException;
import ru.shayhulud.pfspellbook.exception.spell.SpellUpdateException;
import ru.shayhulud.pfspellbook.service.SpellService;

import java.util.Map;

/**
 * Resource for managing spell library.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "/api/spell", tags = "spell")
public class SpellResource {

	private final SpellService spellService;

	@PostMapping("/api/spell")
	@ApiOperation(value = "Create new spell", tags = "CRUD")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 400,
			message = "Bad request. Possible error key\n" +
				"* " + SpellCreationException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellDTO create(@RequestBody SpellDTO spell) throws SpellCreationException {
		log.debug("REST request to create Spell : {}", spell);
		return this.spellService.create(spell);
	}

	@PutMapping("/api/spell/{id}")
	@ApiOperation(value = "Update spell", tags = "CRUD")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 400,
			message = "Bad request. Possible error key\n" +
				"* " + SpellUpdateException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		),
		@ApiResponse(
			code = 404,
			message = "Spell with this id not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellDTO update(@PathVariable("id") Long id, @RequestBody SpellDTO spell)
		throws SpellUpdateException, NotFoundException {

		spell.setId(id);
		log.debug("REST request to update Spell : {}", spell);
		return this.spellService.update(spell);

	}

	@GetMapping("/api/spell/{id}")
	@ApiOperation(value = "Get spell by id", tags = "CRUD")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 404,
			message = "Spell with this id not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellDTO getById(@PathVariable Long id) throws NotFoundException {
		log.debug("REST request to get Spell {}", id);
		return this.spellService.getById(id);
	}

	@DeleteMapping("/api/spell/{id}")
	@ApiOperation(value = "Delete spell by id", tags = "CRUD")
	@ApiResponses({
		@ApiResponse(
			code = 204,
			message = "Success"
		)
	})
	public void delete(@PathVariable("id") Long id) {
		log.debug("REST request to delete Spell {}", id);
		this.spellService.delete(id);
	}

	@ExceptionHandler({SpellUpdateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handle400(APIException e) {
		log.error("Error during process request:", e);
		return e.getErrors();
	}
}
