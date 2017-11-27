package com.jwtfun.interfaces.authentication;

import java.security.NoSuchAlgorithmException;

@FunctionalInterface
public interface Seed {
	public static final byte SALT_SIZE = 64;
	
	public abstract byte[] getSalt(final String sha) throws NoSuchAlgorithmException;
}
