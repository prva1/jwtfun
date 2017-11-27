package com.jwtfun.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity containing data for type of Credit Card - this should be 
 * implemented as Ref Data.
 * 
 * Cardinality is CreditCardType(1-N)-CreditCard(1-1)
 *
 */
@Entity
public class CreditCardType implements java.io.Serializable {

	private static final long serialVersionUID = -8181799979314991478L;
	
	private Long id;
	
	private String nameType;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "NAME_TYPE", unique = true, nullable = false)
	public String getNameType() {
		return nameType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}

	public CreditCardType() {
	}

	public CreditCardType(final String nameType) {
		this.nameType = nameType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append('{');
		sb.append("id=").append(id);
		sb.append(", nameType=").append(nameType);

		return sb.append('}').toString();
	}
}
