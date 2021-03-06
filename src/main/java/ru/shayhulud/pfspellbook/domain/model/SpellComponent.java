package ru.shayhulud.pfspellbook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.shayhulud.pfspellbook.domain.enumiration.Component;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Spell-components relations.
 */
@Data
@Entity
@Table(name = "spell_component")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"spell"})
public class SpellComponent implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "spell_id")
	@JsonIgnoreProperties("components")
	private Spell spell;

	@Column(name = "component")
	@Enumerated(EnumType.STRING)
	private Component component;

	@Column(name = "reagent")
	private String reagent;

	@Override
	public String toString() {
		return "SpellClassRank{" +
			"id=" + getId() +
			", spell='" + getSpell().getId() + "'" +
			", component='" + getComponent() + "'" +
			", reagent='" + getReagent() + "'" +
			"}";
	}
}
