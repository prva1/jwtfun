package com.jwtfun.configuration.security.model;

import java.io.Serializable;
import java.util.List;

/**
 * Model class for User.
 */
public class User implements Serializable {

	private static final long serialVersionUID = -9168949337724566504L;
	private String userName;
	private String firstName;
	private String email;
	private String password;
	private boolean enabled;
	private List<Authority> authorities;

	public User() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(final List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User{"

				+ "userName='" + userName + '\'' + ", firstName='" + firstName + '\'' + ", email='" + email + '}';
	}
}