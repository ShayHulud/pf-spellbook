package ru.shayhulud.pfspellbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.repository.SpellRepository;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.exception.spell.SpellCreationException;
import ru.shayhulud.pfspellbook.exception.spell.SpellUpdateException;

/**
 * Spell manage service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpellService {

	private final SpellRepository spellRepository;

	@Transactional
	public Spell createSpell(Spell input) throws SpellCreationException {
		if (input.getId() != null) {
			throw new SpellCreationException("spell id exists");
		}
		Spell result = this.spellRepository.save(input);
		log.info("New spell was created: {}", result);
		return result;
	}

	@Transactional
	public Spell updateSpell(Spell input) throws SpellUpdateException {
		if (input.getId() == null) {
			throw new SpellUpdateException("spell id is null");
		}
		Spell result = this.spellRepository.save(input);
		log.info("Spell {} was updated: {}", result.getId(), result);
		return result;
	}

	@Transactional
	public Spell getById(Long id) throws NotFoundException {
		return this.spellRepository.getById(id)
			.orElseThrow(() -> new NotFoundException("spell"));
	}
}
