package com.jwtfun;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jwtfun.controller.UserController;
import com.jwtfun.model.entity.User;
import com.jwtfun.service.UserService;
import com.jwtfun.utility.View;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	MockMvc mockMvc;

	@Mock
	UserService userServiceMock;

	@InjectMocks
	UserController userController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).addFilters(new CorsFilter()).build();
	}

	@Test
	public void addNewUser_SignUpUsersuccessfully_ViewLogin() throws Exception {

		User user = null;
		given(userServiceMock.getUser("pedro.villalba.a@gmail.com")).willReturn(user);

		mockMvc.perform(post("/createUser")

				.param("name", "pedro").param("password", "pedro").param("email", "pedro.villalba.a@gmail.com")
				.param("usertype", "Administrator"))

				.andExpect(status().isOk()).andExpect(view().name(View.LOGIN.view()));

		verify(userServiceMock, times(1)).getUser("pedro.villalba.a@gmail.com");
	}

	@Test
	public void addNewUser_TrySignUpUserExistDB_ViewCreateAccount() throws Exception {

		User user = new User();
		given(userServiceMock.getUser("pedro.villalba.a@gmail.com")).willReturn(user);

		mockMvc.perform(post("/createUser")

				.param("name", "pedro").param("password", "pedro").param("email", "pedro.villalba.a@gmail.com")
				.param("usertype", "Administrator"))

				.andExpect(status().is(302)).andExpect(redirectedUrl(View.CREATE_ACCOUNT.view()));

	}

	@Test
	public void addNewUser_EmptyName_ViewCreateAccount() throws Exception {

		User user = new User();
		given(userServiceMock.getUser("pedro.villalba.a@gmail.com")).willReturn(user);

		mockMvc.perform(post("/createUser")

				.param("name", "").param("password", "pedro").param("email", "pedro.villalba.a@gmail.com")
				.param("usertype", "Administrator"))

				.andExpect(status().isOk()).andExpect(view().name(View.CREATE_ACCOUNT.view()));

		verify(userServiceMock, times(0)).getUser(anyString());
	}

	@Test
	public void addNewUser_EmptyPassword_ViewCreateAccount() throws Exception {

		User user = new User();
		given(userServiceMock.getUser("pedro.villalba.a@gmail.com")).willReturn(user);

		mockMvc.perform(post("/createUser")

				.param("name", "pedro").param("password", "").param("email", "pedro.villalba.a@gmail.com")
				.param("usertype", "Administrator"))

				.andExpect(status().isOk()).andExpect(view().name(View.CREATE_ACCOUNT.view()));

		verify(userServiceMock, times(0)).getUser(anyString());
	}

	@Test
	public void addNewUser_EmptyEmail_ViewCreateAccount() throws Exception {

		User user = new User();
		given(userServiceMock.getUser("pedro.villalba.a@gmail.com")).willReturn(user);

		mockMvc.perform(post("/createUser")

				.param("name", "pedro").param("password", "pedro").param("email", "")
				.param("usertype", "Administrator"))

				.andExpect(status().isOk()).andExpect(view().name(View.CREATE_ACCOUNT.view()));
		
		verify(userServiceMock, times(0)).getUser(anyString());
	}
}
