package ru.shayhulud.pfspellbook.controller.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.repository.SpellRepository;

/**
 * Resource for managing spell library.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "/api/spell", tags = {"spell", "resource"})
public class SpellResource {

	private final SpellRepository spellRepository;

	@PostMapping("/api/spell")
	public Spell save(@RequestBody Spell spell) {
		log.debug("REST request to save Spell : {}", spell);
		//TODO: realize;
		return spell;
	}

}
