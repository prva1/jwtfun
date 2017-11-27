package com.jwtfun.configuration.security.model;

import java.io.Serializable;

/**
 * Model class for Authority.
 */
public class Authority implements Serializable {

	private static final long serialVersionUID = 7710151491009212090L;

	private String authorityName;

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(final String authorityName) {
		this.authorityName = authorityName;
	}
}