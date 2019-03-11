package com.epam.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="Product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long prodId;
	private String name;
	private String details;
	private String detail2;	
	
	public Product() {		
	}
	
	public Product(long prodId, String name, String desc) {
		super();
		this.prodId = prodId;
		this.name = name;
		this.details = desc;
	}
	
	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return details;
	}

	public void setDesc(String desc) {
		this.details = desc;
	}
	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}
	
}
