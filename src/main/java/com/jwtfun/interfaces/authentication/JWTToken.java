package com.jwtfun.interfaces.authentication;
import org.springframework.mobile.device.Device;

import com.jwtfun.model.entity.User;

@FunctionalInterface
public interface JWTToken {
	String createJWTToken(final User user, final Device device);
}
