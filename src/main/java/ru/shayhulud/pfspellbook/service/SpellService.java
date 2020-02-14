package ru.shayhulud.pfspellbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shayhulud.pfspellbook.domain.dto.SpellDTO;
import ru.shayhulud.pfspellbook.domain.enumiration.PlayClass;
import ru.shayhulud.pfspellbook.domain.model.Spell;
import ru.shayhulud.pfspellbook.domain.model.SpellClassRank;
import ru.shayhulud.pfspellbook.domain.repository.SpellRepository;
import ru.shayhulud.pfspellbook.exception.NotFoundException;
import ru.shayhulud.pfspellbook.exception.spell.SpellCreationException;
import ru.shayhulud.pfspellbook.exception.spell.SpellUpdateException;
import ru.shayhulud.pfspellbook.service.converter.SpellClassRankConverter;
import ru.shayhulud.pfspellbook.service.converter.SpellComponentConverter;
import ru.shayhulud.pfspellbook.service.converter.SpellConverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Spell manage service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpellService {

	private final SpellRepository spellRepository;
	private final SpellConverter spellConverter;
	private final SpellClassRankConverter spellClassRankConverter;
	private final SpellComponentConverter spellComponentConverter;

	@Transactional
	public SpellDTO create(SpellDTO input) throws SpellCreationException {
		if (input.getId() != null) {
			throw new SpellCreationException("spell id exists");
		}
		input.setName(input.getName().toUpperCase());
		Spell newSpell = this.spellConverter.fromDTO(input);
		newSpell.getClassRanks().forEach(_cr -> _cr.setId(null));
		newSpell.getComponents().forEach(_cr -> _cr.setId(null));
		Spell result = this.spellRepository.save(newSpell);
		log.info("New spell was created: {}", result);
		return this.spellConverter.toDTO(result);
	}

	@Transactional
	public SpellDTO update(SpellDTO input) throws SpellUpdateException, NotFoundException {
		if (input.getId() == null) {
			throw new SpellUpdateException("spell id is null");
		}
		input.setName(input.getName().toUpperCase());

		Spell spell = this.spellRepository.getById(input.getId()).orElseThrow(() -> new NotFoundException("spell"));

		Spell possibleSameNameSpell = this.spellRepository.getByNameIgnoreCase(input.getName()).orElse(null);
		if (possibleSameNameSpell != null
			&& !possibleSameNameSpell.getId().equals(spell.getId())) {
			throw new SpellUpdateException("spell name already exists {}");
		}
		this.spellConverter.copySimpleSpellParams(input, spell);

		//merge components
		spell.getComponents().clear();
		input.getComponents().forEach(_comp -> {
			_comp.setId(null);
			spell.addComponent(this.spellComponentConverter.fromDTO(_comp));
		});

		//merge class ranks
		Map<PlayClass, SpellClassRank> prevClassRanks = new HashMap<>();
		spell.getClassRanks()
			.forEach(_scr -> prevClassRanks.put(_scr.getPlayClass(), _scr));

		Map<PlayClass, SpellClassRank> forUpdateClassRanks = new HashMap<>();
		input.getClassRanks().stream()
			.filter(_scr -> _scr.getId() != null)
			.map(this.spellClassRankConverter::fromDTO)
			.forEach(_scr -> forUpdateClassRanks.put(_scr.getPlayClass(), _scr));

		spell.getClassRanks().clear();

		prevClassRanks.forEach((key, updated) -> {
			if (forUpdateClassRanks.containsKey(key)) {
				SpellClassRank inputed = forUpdateClassRanks.get(key);
				updated.setRank(inputed.getRank());
				updated.setUpdatedAt(new Date());
				spell.addClassRank(updated);
			}
		});

		input.getClassRanks().stream()
			.filter(_scr -> _scr.getId() == null)
			.forEach(_scr -> {
				spell.addClassRank(this.spellClassRankConverter.fromDTO(_scr));
			});

		spell.setUpdatedAt(new Date());

		Spell updated = this.spellRepository.save(spell);
		log.info("Spell {} was updated: {}", updated.getId(), updated);
		return this.spellConverter.toDTO(updated);
	}

	@Transactional
	public Spell getEntityById(Long id) throws NotFoundException {
		return this.spellRepository.getById(id)
			.orElseThrow(() -> new NotFoundException("spell"));
	}

	@Transactional
	public SpellDTO getById(Long id) throws NotFoundException {
		return this.spellConverter.toDTO(this.getEntityById(id));
	}

	@Transactional
	public Spell getEntityByName(String name) throws NotFoundException {
		return this.spellRepository.getByNameIgnoreCase(name)
			.orElseThrow(() -> new NotFoundException("spell"));
	}

	@Transactional
	public SpellDTO getByName(String name) throws NotFoundException {
		return this.spellConverter.toDTO(this.getEntityByName(name));
	}

	@Transactional
	public void delete(Long id) {
		try {
			Spell spell = this.getEntityById(id);
			this.spellRepository.delete(spell);
			log.info("Spell with id {} was deleted successfully", id);
		} catch (NotFoundException e) {
			log.warn(e.getMessage());
		}
	}
}
