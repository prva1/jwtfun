package com.jwtfun.filter;

import com.jwtfun.configuration.security.service.CustomSecurityContext;
import com.jwtfun.filter.AuthJwtFilter;
import com.jwtfun.utility.View;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthJwtFilter extends GenericFilterBean {

	private static final String UNKNOWN_USER = "UNKNOWN_USER_FOR_INVALID_TOKEN";
	private static final String USER_ID = "UserId";
    
	@Autowired
	CustomSecurityContext customSecurityContext;
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		
		if (request== null)
			System.out.println("request== null");

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		System.out.println("tutututut: " + httpRequest.getSession().getAttribute(View.TOKEN.view()));
		
		if (httpRequest == null)
			System.out.println("httpRequest== null");
		
			String userId = customSecurityContext.customSecurityContextHolder(httpRequest);
			
			System.out.println("userId: " + userId);
			
			setMdc(userId);

		try {
			chain.doFilter(httpRequest, response);
		} finally {
			MDC.remove(USER_ID);
		}
	}

	private void setMdc(final String userId) {
		if (StringUtils.isNotBlank(userId)) {
			MDC.put(USER_ID, userId);
		} else {
			MDC.put(USER_ID, UNKNOWN_USER);
		}
	}

	@Override
	public void destroy() {
		MDC.remove(USER_ID);
	}
}