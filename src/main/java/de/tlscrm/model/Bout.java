package de.tlscrm.model;
// Generated 18-Nov-2018 16:12:17 by Hibernate Tools 5.2.11.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Bout generated by hbm2java
 */
@Entity
@Table(name = "bout", schema = "fencing")
public class Bout implements java.io.Serializable {

	private int boutId;
	private Weapon weapon;
	private Date boutDate;
	private Set<BoutFencer> boutFencers = new HashSet<>(0);

	public Bout() {}

	public Bout(final int boutId) {
		this.boutId = boutId;
	}

	public Bout(final int boutId, final Weapon weapon, final Date boutDate,
	        final Set<BoutFencer> boutFencers) {
		this.boutId = boutId;
		this.weapon = weapon;
		this.boutDate = boutDate;
		this.boutFencers = boutFencers;
	}

	@Id

	@Column(name = "bout_id", unique = true, nullable = false)
	public int getBoutId() {
		return this.boutId;
	}

	public void setBoutId(final int boutId) {
		this.boutId = boutId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weapon_id")
	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(final Weapon weapon) {
		this.weapon = weapon;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bout_date", length = 13)
	public Date getBoutDate() {
		return this.boutDate;
	}

	public void setBoutDate(final Date boutDate) {
		this.boutDate = boutDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bout")
	public Set<BoutFencer> getBoutFencers() {
		return this.boutFencers;
	}

	public void setBoutFencers(final Set<BoutFencer> boutFencers) {
		this.boutFencers = boutFencers;
	}

}
