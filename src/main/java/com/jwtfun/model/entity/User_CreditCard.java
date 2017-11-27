package com.jwtfun.model.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class User_CreditCard implements Serializable {

	private static final long serialVersionUID = -2818977740910855345L;

	@EmbeddedId
	private User_CreditCard_Id id;

	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@MapsId("cardId")
	@ManyToOne
	@JoinColumn(name = "CREDIT_CARD_ID")
	private CreditCard creditCard;

	public User_CreditCard_Id getId() {
		return id;
	}

	public void setId(User_CreditCard_Id id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}