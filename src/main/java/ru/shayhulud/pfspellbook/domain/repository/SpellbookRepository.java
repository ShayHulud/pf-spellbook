package ru.shayhulud.pfspellbook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shayhulud.pfspellbook.domain.model.Spellbook;

import java.util.Optional;

/**
 * Repository for spellbooks.
 */
@Repository
public interface SpellbookRepository extends JpaRepository<Spellbook, Long> {

	Optional<Spellbook> getById(Long id);
}
