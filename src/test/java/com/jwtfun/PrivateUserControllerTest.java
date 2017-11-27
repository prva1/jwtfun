package com.jwtfun;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jwtfun.controller.UserController;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserController.class)
public class PrivateUserControllerTest {

	private UserController powerMockUserControllerSpy;
	private static final String METHOD = "isValidParameter";

	@Before
	public void setUp() {
		powerMockUserControllerSpy = PowerMockito.spy(new UserController());
	}

	@Test
	public void testMockPrivateMethod() throws Exception {

		PowerMockito.doReturn(true).when(powerMockUserControllerSpy, METHOD, ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

		PowerMockito.doReturn(true).when(powerMockUserControllerSpy, METHOD, ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString());

		// Call public method which call the above private Method.
		// Verify the method was call at least once.

	}
}