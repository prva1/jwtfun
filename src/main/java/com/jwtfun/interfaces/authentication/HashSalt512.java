package com.jwtfun.interfaces.authentication;

@FunctionalInterface
public interface HashSalt512 {
	public static final String SHA1PRNG = "SHA1PRNG";
	public static final String SHA_256 = "SHA-256";
	
	public abstract String getPasswordGeneration(String password, byte[] saltSize);
}
