package ru.shayhulud.pfspellbook.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Entity
@Table(name = "spellbook")
@AllArgsConstructor
@NoArgsConstructor
public class Spellbook implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "spellbook_spell",
		joinColumns = @JoinColumn(name = "spellbook_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "spell_id", referencedColumnName = "id"))
	private Set<Spell> spells = new TreeSet<>(Comparator.comparing(Spell::getName));

	@Override
	public String toString() {
		return "Spell{" +
			"id=" + getId() +
			", name='" + getName() + "'" +
			", spells='" + getSpells().stream().map(Spell::getId) + "'" +
			"}";
	}

	public Spellbook addSpell(Spell spell) {
		this.spells.add(spell);
		return this;
	}

	public Spellbook removeSpell(Spell spell) {
		this.spells.remove(spell);
		return this;
	}
}
