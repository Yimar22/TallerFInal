package com.tamayo.front.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.data.annotation.Id;

import com.tamayo.front.model.Location;

/**
 * The persistent class for the workorderrouting database table.
 *
 */

public class Workorderrouting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer workorderroutingid;
	
	public Integer getWorkorderroutingid() {
		return workorderroutingid;
	}

	public void setWorkorderroutingid(Integer workorderroutingid) {
		this.workorderroutingid = workorderroutingid;
	}

	private WorkorderroutingPK id;

	private BigDecimal actualcost;

	private Timestamp actualenddate;

	private BigDecimal actualresourcehrs;

	private Timestamp actualstartdate;

	private Timestamp modifieddate;

	private BigDecimal plannedcost;

	private Timestamp scheduledenddate;

	private Timestamp scheduledstartdate;

	
	// bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name = "locationid", insertable = false, updatable = false)
	private Location location;

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	// bi-directional many-to-one association to Workorder
	@ManyToOne
	@JoinColumn(name = "workorderid", insertable = false, updatable = false)
	private Workorder workorder;

	public Workorderrouting() {
	}

	public BigDecimal getActualcost() {
		return this.actualcost;
	}

	public Timestamp getActualenddate() {
		return this.actualenddate;
	}

	public BigDecimal getActualresourcehrs() {
		return this.actualresourcehrs;
	}

	public Timestamp getActualstartdate() {
		return this.actualstartdate;
	}

	public WorkorderroutingPK getId() {
		return this.id;
	}


	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public BigDecimal getPlannedcost() {
		return this.plannedcost;
	}

	public Timestamp getScheduledenddate() {
		return this.scheduledenddate;
	}

	public Timestamp getScheduledstartdate() {
		return this.scheduledstartdate;
	}

	public Workorder getWorkorder() {
		return this.workorder;
	}

	public void setActualcost(BigDecimal actualcost) {
		this.actualcost = actualcost;
	}

	public void setActualenddate(Timestamp actualenddate) {
		this.actualenddate = actualenddate;
	}

	public void setActualresourcehrs(BigDecimal actualresourcehrs) {
		this.actualresourcehrs = actualresourcehrs;
	}

	public void setActualstartdate(Timestamp actualstartdate) {
		this.actualstartdate = actualstartdate;
	}

	public void setId(WorkorderroutingPK id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPlannedcost(BigDecimal plannedcost) {
		this.plannedcost = plannedcost;
	}

	public void setScheduledenddate(Timestamp scheduledenddate) {
		this.scheduledenddate = scheduledenddate;
	}

	public void setScheduledstartdate(Timestamp scheduledstartdate) {
		this.scheduledstartdate = scheduledstartdate;
	}

	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

}