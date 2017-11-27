package com.jwtfun.configuration;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jwtfun.configuration.security.PasswordHandle;
import com.jwtfun.controller.UserController;
import com.jwtfun.interfaces.authentication.Seed;

@Configuration
public class BeanConfiguration {

	private static final byte SALT_SIZE = 64;

	@Bean
	public PasswordHandle passwordEncoder() {
		return new PasswordHandle();
	}
	
	@Bean
	public UserController userController() {
		return new UserController();
	}

	@Bean
	public Seed salt() {
		return (a) -> {
			SecureRandom sr;
			byte[] salt = null;

			try {
				sr = SecureRandom.getInstance(a);
				salt = new byte[SALT_SIZE];
				sr.nextBytes(salt);
			} finally {
				sr = null;
			}

			return salt;
		};
	}
}