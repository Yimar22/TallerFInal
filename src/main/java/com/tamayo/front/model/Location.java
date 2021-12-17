package com.tamayo.front.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the location database table.
 *
 */

public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer locationid;

	private BigDecimal availability;

	private BigDecimal costrate;

	private Timestamp modifieddate;

	private String name;


	// bi-directional many-to-one association to Workorderrouting
	
	private List<Workorderrouting> workorderroutings;

	public Location() {
	}


	public Workorderrouting addWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().add(workorderrouting);
		workorderrouting.setLocation(this);

		return workorderrouting;
	}

	public BigDecimal getAvailability() {
		return this.availability;
	}

	public BigDecimal getCostrate() {
		return this.costrate;
	}

	public Integer getLocationid() {
		return this.locationid;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Workorderrouting> getWorkorderroutings() {
		return this.workorderroutings;
	}


	public Workorderrouting removeWorkorderrouting(Workorderrouting workorderrouting) {
		getWorkorderroutings().remove(workorderrouting);
		workorderrouting.setLocation(null);

		return workorderrouting;
	}

	public void setAvailability(BigDecimal availability) {
		this.availability = availability;
	}

	public void setCostrate(BigDecimal costrate) {
		this.costrate = costrate;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWorkorderroutings(List<Workorderrouting> workorderroutings) {
		this.workorderroutings = workorderroutings;
	}

}