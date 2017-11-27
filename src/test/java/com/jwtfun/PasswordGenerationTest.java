package com.jwtfun;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jwtfun.configuration.security.PasswordHandle;
import com.jwtfun.interfaces.authentication.Seed;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class PasswordGenerationTest {
	
	private String result = null;	
	
	public static final byte SALT_SIZE = 64;
	
	private static final String passwordToGenerate = "Pedro";
	
	private final String PASS = "d7e860d566c9e379313d2c8ec7740809c340c15a2012623824334a497acb1745";	
	
	private final byte[] generatedSalt = { 6, 11, -66, 77, -51, -4, 44, 28, 125, 89, -123, -92, 27, 
			-48, -32, 108, -38, -114, -104, -88, -69, -30, -104, 41, 22, 70, 17, -30, 97, -18,
			-117, 44, 37, -82, 38, 58, 51, -72, 25, -16, -110, 80, 75, 110, -72, -63, 33, 100,
			19, -84, -109, -58, 21, 34, -29, -27, -72, -79, 72, -81, 29, 76, 103, -4 };
	
	
	@Autowired
	private Seed seed;
	
	 @TestConfiguration
	    static class EmployeeServiceImplTestContextConfiguration {
	  
		 @Bean
			public Seed seed (){    	
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
	
	
	@Test
	public void testWrongHasSalt() {
		try {
			seed.getSalt("Wrong");
			fail("Expected an NoSuchAlgorithmException to be thrown");
		} catch (NoSuchAlgorithmException e) {
			assertEquals(e.getMessage(), "Wrong SecureRandom not available");
		}
	}

	@Test
	public void testCreateSalt() {
		byte[] salt = null;

		try {
			salt = seed.getSalt(PasswordHandle.SHA1PRNG);
			assertNotNull("Salt generated ok", salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreatePassword() {		
		result = PasswordHandle.hash.getPasswordGeneration(passwordToGenerate, generatedSalt);
		
		assertNotNull(result);
		assertTrue(result.length() > 0);
		assertEquals(result, PASS);
	}
}