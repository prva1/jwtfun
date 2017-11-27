package com.jwtfun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtfun.configuration.security.model.Authority;
import com.jwtfun.configuration.security.model.User;
import com.jwtfun.service.jwt.JwtUserFactory;

/**
 * Add Authorities / Group (USER, ADMIN) for this demo. This should be a list of
 * grand.
 */
@Service
public class CustomUserDetailsService {
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USER = "USER";

	public CustomUserDetailsService() {
	}

	public User parseUserGroups(final com.jwtfun.model.entity.User user) {

		List<Authority> listAuthorities = new ArrayList<Authority>();
		Authority authority = new Authority();

		if (user.isAdmin()) {
			authority.setAuthorityName(ROLE_ADMIN);
		} else {
			authority.setAuthorityName(ROLE_USER);
		}

		listAuthorities.add(authority);

		User userBuilt = new User();

		userBuilt.setAuthorities(listAuthorities);

		return userBuilt;
	}

	public UserDetails convertToUserDetails(final User member) throws UsernameNotFoundException {
		return JwtUserFactory.create(member);
	}
}