package com.epam.course.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="Product_Reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProdReviews {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private long prodId;
	private String review;
	private String comments;
	private int prodRank;

	public ProdReviews() {		
	}	
	
	public ProdReviews(long id, long prodId, String review, String comments, int prodRank) {
		super();
		Id = id;
		this.prodId = prodId;
		this.review = review;
		this.comments = comments;
		this.prodRank = prodRank;
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getProdRank() {
		return prodRank;
	}

	public void setProdRank(int prodRank) {
		this.prodRank = prodRank;
	}

}
