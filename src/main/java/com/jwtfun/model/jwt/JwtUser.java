package com.jwtfun.model.jwt;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwtfun.configuration.security.model.Authority;
import com.jwtfun.configuration.security.model.User;

/**
 * A model for the JwtUser.
 */
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -6261545740583105651L;
	private final String username;
	private final String firstName;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;

	public JwtUser(final User user) {
		// -- user name must be unique. so what I am doing here is incorrect.
		
		this.username = user.hashCode() + " - Demo - " + user.getFirstName();
		this.firstName = user.getFirstName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authorities = mapToGrantedAuthorities(user.getAuthorities());
		this.enabled = user.getEnabled();
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Authority> authorities) {
		if (authorities == null) {
			return null;
		}

		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		for (Authority authority : emptyIfNull(authorities)) {
			authorityList.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
		}
		return authorityList;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
