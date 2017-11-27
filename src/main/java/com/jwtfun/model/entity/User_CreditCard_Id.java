package com.jwtfun.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class User_CreditCard_Id implements Serializable {
	
	private static final long serialVersionUID = 2099387962810380260L;
	
	private long userId;
	
	private long cardId;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCreditCardId() {
		return cardId;
	}
	public void setCreditCardId(long creditCardId) {
		this.cardId = creditCardId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cardId ^ (cardId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User_CreditCard_Id other = (User_CreditCard_Id) obj;
		if (cardId != other.cardId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append('{');
		sb.append("userId=").append(userId);
		sb.append(", cardId=").append(cardId);

		return sb.append('}').toString();
	}	
}