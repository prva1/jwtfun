package com.jwtfun.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity containing data of the Credit Card.
 *
 * Cardinality is CreditCard(1-N) - User(1-N) = N:N
 */
@Entity
public class CreditCard implements java.io.Serializable {

	private static final long serialVersionUID = -8889894421850799652L;

	private Long cardId;

	private String creditCardNumber;

	/**
	 * CreditCard(1-N) - CreditCardType(1-1) = N:1
	 */
	private CreditCardType nameTypeId;

	private Date date;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CREDIT_CARD_ID", unique = true, nullable = false)
	public Long getCardId() {
		return cardId;
	}

	@Column(name = "CREDIT_CARD_NUMBER", unique = true, nullable = false, length = 10)
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

    @JoinColumn(name = "ID")
	public CreditCardType getNameTypeId() {
		return nameTypeId;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "CREDIT_CARD_DATE", nullable = true, length = 10)
	public Date getDate() {
		return date;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}


	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNameTypeId(CreditCardType nameTypeId) {
		this.nameTypeId = nameTypeId;
	}

	public CreditCard() {
	}

	public CreditCard(final String creditCardNumber, final CreditCardType name) {
		this.creditCardNumber = creditCardNumber;
		this.nameTypeId = name;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append('{');
		sb.append("creditCardNumber=").append(creditCardNumber);
		sb.append(", nameTypeId=").append(nameTypeId);
		sb.append(", date=").append(date);

		return sb.append('}').toString();
	}
}