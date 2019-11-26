package ru.shayhulud.pfspellbook.domain.model;

import lombok.Data;
import ru.shayhulud.pfspellbook.domain.enumiration.PlayClass;

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
 * Spell rank-class relation.
 */
@Data
@Entity
@Table(name = "spell_class_rank")
public class SpellClassRank implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "spell_id")
	private Spell spell;

	@Column(name = "play_class")
	@Enumerated(EnumType.STRING)
	private PlayClass playClass;

	@Column(name = "rank")
	private Integer rank;

}
