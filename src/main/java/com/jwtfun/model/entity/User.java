package com.jwtfun.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity containing data of the User.
 * 
 * Cardinality is User(1-N) - CreditCard(1-N) = N:N
 *
 */
@Entity
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -6753425024238221892L;

	private Long userId;

	private String name;

	private String email;

	private String password;

	private String salt;
	
	private boolean userType;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}

	@Column(name = "USER_NAME", unique = false, nullable = false, length = 25)
	public String getName() {
		return name;
	}

	// TODO - coalition !
	@Column(name = "USER_PASSWORD", unique = false, nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	@Column(name = "USER_EMAIL", unique = true, nullable = false, length = 30)
	public String getEmail() {
		return email;
	}

	@Column(name = "USER_SALT", unique = false, nullable = false, length = 100)
	public String getSalt() {
		return salt;
	}

	/*public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}*/

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "USER_TYPE", nullable = false, length = 1)
	public boolean isAdmin() {
		return userType;
	}

	public void setAdmin(boolean admin) {
		this.userType = admin;
	}

	public User() {
	}

	public User(final String name, final String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName()).append('{');
		sb.append("id=").append(userId);
		sb.append(", name=").append(name);
		sb.append(", email=").append(email);
		sb.append(", password=").append(password);
		sb.append(", salt=").append(salt.getBytes());
		//sb.append(", creditCard=").append(creditCard);
		
		return sb.append('}').toString();
	}
}