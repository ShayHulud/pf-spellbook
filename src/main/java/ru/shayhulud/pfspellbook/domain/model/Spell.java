package ru.shayhulud.pfspellbook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.shayhulud.pfspellbook.domain.enumiration.MagicSchool;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "name")
	private String name;

	@Column(name = "magic_school")
	@Enumerated(EnumType.STRING)
	private MagicSchool school;

	@Column(name = "sub_school")
	private String subSchool;

	@JsonIgnoreProperties({"spell"})
	@OneToMany(mappedBy = "spell", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpellClassRank> classRanks = new LinkedList<>();

	@Column(name = "cast_time")
	private String castTime;

	@JsonIgnoreProperties({"spell"})
	@OneToMany(mappedBy = "spell", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SpellComponent> components = new HashSet<>();

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

	public Spell addClassRank(SpellClassRank classRank) {
		this.classRanks.add(classRank);
		classRank.setSpell(this);
		return this;
	}

	public Spell removeClassRank(SpellClassRank classRank) {
		this.classRanks.remove(classRank);
		classRank.setSpell(null);
		return this;
	}

	public Spell addComponent(SpellComponent spellComponent) {
		this.components.add(spellComponent);
		spellComponent.setSpell(this);
		return this;
	}

	public Spell removeComponent(SpellComponent spellComponent) {
		this.components.remove(spellComponent);
		spellComponent.setSpell(null);
		return this;
	}
}
