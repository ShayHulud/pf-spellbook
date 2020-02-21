package ru.shayhulud.pfspellbook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shayhulud.pfspellbook.domain.model.Spell;

import java.util.Optional;
import java.util.Set;

/**
 * Repo for spells.
 */
@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

	Optional<Spell> getById(Long id);

	Optional<Spell> getByNameIgnoreCase(String name);

	Set<Spell> getByNameInIgnoreCase(Set<String> names);
}
