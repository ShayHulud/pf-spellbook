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
import ru.shayhulud.pfspellbook.domain.dto.spellbook.CreateSpellbookDTO;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.SpellbookDTO;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.UpdateSpellbookDTO;
import ru.shayhulud.pfspellbook.exception.APIException;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.exception.spellbook.SpellbookUpdateException;
import ru.shayhulud.pfspellbook.service.SpellbookService;

import java.util.Map;

/**
 * Resource for managing spellbook library.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "/api/spellbook", tags = {"spellbook", "CRUD"})
public class SpellbookResource {

	private final SpellbookService spellbookService;

	@PostMapping("/api/spellbook")
	@ApiOperation("Create new spellbook")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		)
	})
	public SpellbookDTO create(@RequestBody CreateSpellbookDTO dto) {
		log.debug("REST request to create Spellbook : {}", dto);
		return this.spellbookService.create(dto);
	}

	@PutMapping("/api/spellbook/{id}")
	@ApiOperation("Update spellbook")
	@ApiResponses({
		@ApiResponse(
			code = 200,
			message = "Success"
		),
		@ApiResponse(
			code = 400,
			message = "Bad request. Possible error key\n" +
				"* " + SpellbookUpdateException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		),
		@ApiResponse(
			code = 404,
			message = "Spellbook with this id not found. Possible error key\n" +
				"* " + NotFoundException.ERROR_TEXT,
			response = String.class, responseContainer = "Map"
		)
	})
	public SpellbookDTO update(@PathVariable("id") Long id, @RequestBody UpdateSpellbookDTO spellbook)
		throws NotFoundException, SpellbookUpdateException {

		spellbook.setId(id);
		log.debug("REST request to update Spellbook : {}", spellbook);
		return this.spellbookService.update(spellbook);

	}

	@GetMapping("/api/spellbook/{id}")
	@ApiOperation("Get spell by id")
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
	public SpellbookDTO getById(@PathVariable Long id) throws NotFoundException {
		log.debug("REST request to get Spellbook {}", id);
		return this.spellbookService.getById(id);
	}

	@DeleteMapping("/api/spellbook/{id}")
	@ApiOperation("Delete spellbook by id")
	@ApiResponses({
		@ApiResponse(
			code = 204,
			message = "Success"
		)
	})
	public void delete(@PathVariable("id") Long id) {
		log.debug("REST request to delete Spellbook {}", id);
		this.spellbookService.delete(id);
	}

	@ExceptionHandler({SpellbookUpdateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handle400(APIException e) {
		log.error("Error during process request:", e);
		return e.getErrors();
	}
}
