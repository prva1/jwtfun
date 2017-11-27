package com.jwtfun.service;

import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwtfun.configuration.security.model.User;
import com.jwtfun.interfaces.authentication.JWTToken;
import com.jwtfun.service.jwt.JwtTokenUtil;


public class JWTService implements JWTToken {

	@Override
	public String createJWTToken(final com.jwtfun.model.entity.User user, final Device device) {

		CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();

		User member = customUserDetailsService.parseUserGroups(user);

		final UserDetails userDetails = customUserDetailsService.convertToUserDetails(member);

		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

		final String token = jwtTokenUtil.generateToken(userDetails, device);

		System.out.println("Generated token = " + token);

		return token;
	}
}