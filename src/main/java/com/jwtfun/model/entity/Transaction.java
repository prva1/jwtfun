package com.jwtfun.model.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * Entity for keeping occurrence instance operations
 *
 */
@Entity
public class Transaction implements java.io.Serializable{
	
	private static final long serialVersionUID = 2517243778300028663L;

	@EmbeddedId
	private TransactionId Id;
	
	private Date date;
	
	
	@PrePersist
	  protected void onCreate() {
		date = new Date();
	  }
	
	public TransactionId getId() {
		return Id;
	}

	@NotNull
	public Date getDate() {
		return date;
	}

	public void setId(TransactionId id) {
		Id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Transaction(){}
}
