package com.jwtfun;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jwtfun.interfaces.modelhb.UserRepository;
import com.jwtfun.model.entity.User;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	private static final String email = "pedro.villalba.a@gmail.com";

	@MockBean
	private TestEntityManager testEntityManager;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp() {

		// Given
		User pedro = new User();
		pedro.setEmail(email);

		Mockito.when(userRepository.findByEmail(email)).thenReturn(pedro);
	}

	@Test
	public void whenFindByEmail_thenReturnEmployee() {

		// When
		User found = userRepository.findByEmail(email);

		// Then
		assertThat(found.getEmail()).isEqualTo(email);
	}
}