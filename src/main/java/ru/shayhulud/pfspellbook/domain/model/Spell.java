package ru.shayhulud.pfspellbook.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shayhulud.pfspellbook.domain.enumiration.MagicSchool;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Spell data entity.
 */
@Data
@Entity
@Table(name = "spell")
@AllArgsConstructor
@NoArgsConstructor
public class Spell implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "magic_school")
	@Enumerated(EnumType.STRING)
	private MagicSchool school;

	@Column(name = "sub_school")
	private String subSchool;

	@OneToMany(mappedBy = "spell", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpellClassRank> classRanks;

	@Column(name = "cast_time")
	private String castTime;

	@OneToMany(mappedBy = "spell", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SpellComponent> components;

	@Column(name = "distance")
	private String distance;

	@Column(name = "effect")
	private String effect;

	@Column(name = "target")
	private String target;

	@Column(name = "duration")
	private String duration;

	@Column(name = "saving_throw")
	private String savingThrow;

	@Column(name = "is_resistable")
	private boolean resistable;

	@Column(name = "description")
	private String description;

	@Override
	public String toString() {
		return "Spell{" +
			"id=" + getId() +
			", name='" + getName() + "'" +
			", school='" + getSchool() + "'" +
			", subSchool='" + getSubSchool() + "'" +
			", castTime='" + getCastTime() + "'" +
			", distance='" + getDistance() + "'" +
			", effect='" + getEffect() + "'" +
			", target='" + getTarget() + "'" +
			", duration='" + getDuration() + "'" +
			", savingThrow='" + getSavingThrow() + "'" +
			", resistable='" + isResistable() + "'" +
			", description='" + getDescription() + "'" +
			"}";
	}
}
