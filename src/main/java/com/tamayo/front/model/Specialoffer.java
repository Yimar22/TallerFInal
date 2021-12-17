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
 * The persistent class for the specialoffer database table.
 *
 */

public class Specialoffer implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer specialofferid;

	private String category;

	private BigDecimal discountpct;

	private Timestamp modifieddate;

	// bi-directional many-to-one association to Specialofferproduct
	//@OneToMany(mappedBy = "specialoffer")
	private List<Specialofferproduct> specialofferproducts;

	public Specialoffer() {
	}

	public Specialofferproduct addSpecialofferproduct(Specialofferproduct specialofferproduct) {
		getSpecialofferproducts().add(specialofferproduct);
		specialofferproduct.setSpecialoffer(this);

		return specialofferproduct;
	}

	public String getCategory() {
		return this.category;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getSpecialofferid() {
		return this.specialofferid;
	}

	public List<Specialofferproduct> getSpecialofferproducts() {
		return this.specialofferproducts;
	}

	public Specialofferproduct removeSpecialofferproduct(Specialofferproduct specialofferproduct) {
		getSpecialofferproducts().remove(specialofferproduct);
		specialofferproduct.setSpecialoffer(null);

		return specialofferproduct;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDiscountpct(BigDecimal discountpct) {
		this.discountpct = discountpct;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setSpecialofferid(Integer specialofferid) {
		this.specialofferid = specialofferid;
	}

	public void setSpecialofferproducts(List<Specialofferproduct> specialofferproducts) {
		this.specialofferproducts = specialofferproducts;
	}

}