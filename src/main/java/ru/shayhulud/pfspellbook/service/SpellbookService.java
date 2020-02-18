package ru.shayhulud.pfspellbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.CreateSpellbookDTO;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.SpellbookDTO;
import ru.shayhulud.pfspellbook.domain.dto.spellbook.UpdateSpellbookDTO;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.model.Spellbook;
import ru.shayhulud.pfspellbook.domain.repository.SpellRepository;
import ru.shayhulud.pfspellbook.domain.repository.SpellbookRepository;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.exception.spellbook.SpellbookUpdateException;
import ru.shayhulud.pfspellbook.service.converter.SpellbookConverter;

import java.util.Set;

//TODO: when users appears select all by user

/**
 * Service for Spellbook entity.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpellbookService {

	private final SpellbookRepository spellbookRepository;
	private final SpellRepository spellRepository;

	private final SpellbookConverter spellbookConverter;

	private final SpellService spellService;

	@Transactional
	public SpellbookDTO create(CreateSpellbookDTO dto) {
		Spellbook spellbook = new Spellbook();
		spellbook.setName(dto.getName().toUpperCase());
		Spellbook created = this.spellbookRepository.save(spellbook);
		return this.spellbookConverter.toDTO(created);
	}

	@Transactional
	public SpellbookDTO update(UpdateSpellbookDTO dto)
		throws SpellbookUpdateException, NotFoundException {

		if (dto.getId() == null) {
			throw new SpellbookUpdateException("id is null");
		}
		Spellbook spellbook = this.spellbookRepository.getById(dto.getId())
			.orElseThrow(() -> new NotFoundException("spellbook"));

		spellbook.setName(dto.getName());

		//update spell set of spellbook
		Set<Spell> desiredSpellSet = this.spellRepository.getByNameInIgnoreCase(dto.getSpellNames());
		spellbook.getSpells().clear();
		desiredSpellSet.forEach(spellbook::addSpell);

		Spellbook updated = this.spellbookRepository.save(spellbook);
		log.info("Spellbook {} was updated: {}", updated.getId(), updated);
		return this.spellbookConverter.toDTO(updated);
	}

	@Transactional
	public SpellbookDTO addSpellToSpellbook(Long spellbookId, String spellName) throws NotFoundException {
		Spellbook spellbook = this.spellbookRepository.getById(spellbookId)
			.orElseThrow(() -> new NotFoundException("spellbook"));
		Spell desiredSpell = this.spellService.getEntityByName(spellName.toUpperCase());
		spellbook.addSpell(desiredSpell);
		Spellbook updated = this.spellbookRepository.save(spellbook);
		log.info("Spell: {}:{} was added to spellbook {}",
			desiredSpell.getId(), desiredSpell.getName(), updated.getId());
		return this.spellbookConverter.toDTO(updated);
	}

	@Transactional
	public SpellbookDTO removeSpellFromSpellbook(Long spellbookId, String spellName) throws NotFoundException {
		Spellbook spellbook = this.spellbookRepository.getById(spellbookId)
			.orElseThrow(() -> new NotFoundException("spellbook"));
		try {
			Spell desiredSpell = this.spellService.getEntityByName(spellName.toUpperCase());
			spellbook.removeSpell(desiredSpell);
			Spellbook updated = this.spellbookRepository.save(spellbook);
			log.info("Spell: {}:{} was removed from spellbook {}",
				desiredSpell.getId(), desiredSpell.getName(), updated.getId());
			return this.spellbookConverter.toDTO(updated);
		} catch (NotFoundException e) {
			log.warn("Spell with name {} was not found, nothing to remove from spellbook", spellName);
			return this.spellbookConverter.toDTO(spellbook);
		}
	}

	@Transactional
	public SpellbookDTO getById(Long id) throws NotFoundException {
		return this.spellbookConverter.toDTO(this.getEntityById(id));
	}

	@Transactional
	public Spellbook getEntityById(Long id) throws NotFoundException {
		return this.spellbookRepository.getById(id)
			.orElseThrow(() -> new NotFoundException("spellbook"));
	}

	@Transactional
	public void delete(Long id) {
		try {
			Spellbook spellbook = this.getEntityById(id);
			this.spellbookRepository.delete(spellbook);
			log.info("Spellbook with id {} was deleted successfully", id);
		} catch (NotFoundException e) {
			log.warn(e.getMessage());
		}
	}
}
