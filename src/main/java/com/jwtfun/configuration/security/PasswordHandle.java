package com.jwtfun.configuration.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

import com.jwtfun.interfaces.authentication.HashSalt512;

/**
 * Class in change to generate salt seed and SHA-256 password. in java 8 jdk
 * could be PBKDF2WithHmacSHA1 / PBKDF2WithHmacSHA512 and other options are
 * scrypt and bcrypt (mostly used)
 */
@Component
public class PasswordHandle {
	public static final String SHA1PRNG = "SHA1PRNG";
	public static final String SHA_256 = "SHA-256";
	public static final String KDF_512 = "PBKDF2WithHmacSHA512";

	public static final HashSalt512 hash = (p, s) -> {
		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance(SHA_256);
			md.update(s);
			byte[] bytes = md.digest(p.getBytes());

			StringBuilder sb = new StringBuilder();

			for (byte b : bytes)
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

			generatedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	};

	/**
	 * Another Encryption types taken from
	 * https://www.owasp.org/index.php/Hashing_Java
	 */
	// TODO - not used
	public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
			final int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKey key = null;
		try {
			SecretKeyFactory skf;
			skf = SecretKeyFactory.getInstance(KDF_512);

			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			key = skf.generateSecret(spec);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		byte[] res = key.getEncoded();
		return res;
	}
}