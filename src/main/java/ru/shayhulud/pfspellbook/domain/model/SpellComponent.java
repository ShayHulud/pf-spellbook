package ru.shayhulud.pfspellbook.domain.model;

import lombok.Data;
import ru.shayhulud.pfspellbook.domain.enumiration.Component;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Spell-components relations.
 */
@Data
@Entity
@Table(name = "spell_component")
public class SpellComponent implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "spell_id")
	private Spell spell;

	@Column(name = "component")
	@Enumerated(EnumType.STRING)
	private Component component;

	@Column(name = "reagent")
	private String reagent;
}
