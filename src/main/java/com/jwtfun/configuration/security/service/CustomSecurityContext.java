package com.jwtfun.configuration.security.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jwtfun.configuration.security.model.User;
import com.jwtfun.service.jwt.JwtTokenUtil;
import com.jwtfun.utility.View;

/**
 * Implementation for decode service token.
 */
@Component
public class CustomSecurityContext {
	private static final String AUTHORITY = "authority";
	private static final String ROLE = "ROLE_";
	private static final String X_AUTHORIZATION = "X-Authorization";

	public String customSecurityContextHolder(final HttpServletRequest httpRequest) {
		System.out.println("customSecurityContextHolder started");

		//String jwtToken = httpRequest.getHeader(X_AUTHORIZATION);
		String jwtToken = (String) httpRequest.getSession().getAttribute(View.TOKEN.view());
		System.out.println("X_AUTHORIZATION in customSecurityContextHolder : " + jwtToken);
		User userDetail = new User();

		if (StringUtils.isNotBlank(jwtToken)) {
			JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
			List authoritiesList = jwtTokenUtil.retrieveGroups(jwtToken);
			userDetail.setUserName(jwtTokenUtil.getUsernameFromToken(jwtToken));
			List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();

			if (!authoritiesList.isEmpty()) {
				for (Object authorityGroup : authoritiesList) {
					String permission = ((LinkedHashMap) authorityGroup).get(AUTHORITY).toString().trim();
					grantedAuthority.add(new SimpleGrantedAuthority(ROLE + permission));
				}

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetail.getUserName(), null, grantedAuthority);
				authentication.setDetails(userDetail);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			System.out.println("customSecurityContextHolder ended , invalid parameters: jwtToken or AccountName");
		}

		return userDetail.getUserName();
	}
}