package com.tamayo.front.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.tamayo.back.model.Productmodel;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "class")
	private String class_;

	private Integer productid;
	
	private String name;
	
	private String productnumber;
	
	private Integer daystomanufacture;

	private Timestamp sellenddate;

	private Timestamp sellstartdate;

	private String size;

	private BigDecimal weight;

	// bi-directional many-to-one association to Productsubcategory
	@ManyToOne
	@JoinColumn(name = "productsubcategoryid")
	private Productsubcategory productsubcategory;

	// bi-directional many-to-one association to Unitmeasure
	@ManyToOne
	@JoinColumn(name = "sizeunitmeasurecode")
	private Unitmeasure unitmeasure1;

	// bi-directional many-to-one association to Unitmeasure
	@ManyToOne
	@JoinColumn(name = "weightunitmeasurecode")
	private Unitmeasure unitmeasure2;


	// bi-directional many-to-one association to Workorder
	@OneToMany(mappedBy = "product")
	private List<Workorder> workorders;

	public Product() {
	}


	public Workorder addWorkorder(Workorder workorder) {
		getWorkorders().add(workorder);
		workorder.setProduct(this);

		return workorder;
	}

	public String getClass_() {
		return this.class_;
	}

	public Integer getDaystomanufacture() {
		return this.daystomanufacture;
	}

	public String getName() {
		return this.name;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public String getProductnumber() {
		return this.productnumber;
	}

	public Productsubcategory getProductsubcategory() {
		return this.productsubcategory;
	}

	public Timestamp getSellenddate() {
		return this.sellenddate;
	}

	public Timestamp getSellstartdate() {
		return this.sellstartdate;
	}

	public String getSize() {
		return this.size;
	}

	public Unitmeasure getUnitmeasure1() {
		return this.unitmeasure1;
	}

	public Unitmeasure getUnitmeasure2() {
		return this.unitmeasure2;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public List<Workorder> getWorkorders() {
		return this.workorders;
	}

	public Workorder removeWorkorder(Workorder workorder) {
		getWorkorders().remove(workorder);
		workorder.setProduct(null);

		return workorder;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public void setDaystomanufacture(Integer daystomanufacture) {
		this.daystomanufacture = daystomanufacture;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}

	public void setProductsubcategory(Productsubcategory productsubcategory) {
		this.productsubcategory = productsubcategory;
	}

	public void setSellenddate(Timestamp sellenddate) {
		this.sellenddate = sellenddate;
	}

	public void setSellstartdate(Timestamp sellstartdate) {
		this.sellstartdate = sellstartdate;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setUnitmeasure1(Unitmeasure unitmeasure1) {
		this.unitmeasure1 = unitmeasure1;
	}

	public void setUnitmeasure2(Unitmeasure unitmeasure2) {
		this.unitmeasure2 = unitmeasure2;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public void setWorkorders(List<Workorder> workorders) {
		this.workorders = workorders;
	}

}