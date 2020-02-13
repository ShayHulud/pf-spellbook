package ru.shayhulud.pfspellbook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shayhulud.pfspellbook.domain.model.Spell;

import java.util.Optional;

/**
 * Repo for spells.
 */
@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

	Optional<Spell> getById(Long id);
}
