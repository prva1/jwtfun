package com.jwtfun.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtfun.configuration.security.PasswordHandle;
import com.jwtfun.interfaces.authentication.Seed;
import com.jwtfun.interfaces.modelhb.UserRepository;
import com.jwtfun.model.entity.User;
import com.jwtfun.utility.View;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Seed seed;

	public String addNewUser(final String name, final String password, final String email,final String userType) {

		User user = new User();
		
		if (View.ADMINISTRATOR.view().equals(userType)){
			user.setAdmin(true);
		}else{
			user.setAdmin(false);
		}
		
		user.setName(name);
		user.setEmail(email);
		password(user, password);

		getUserRepository().save(user);

		return View.CREATE_CREDIT_CARD_DATA.view();
	}

	private void password(final User user, final String pass) {

		byte[] salt = null;

		try {
			salt = seed.getSalt(PasswordHandle.SHA1PRNG);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String password = PasswordHandle.hash.getPasswordGeneration(pass, salt);
		user.setPassword(password);
		
		String base64 = Base64.getEncoder().encodeToString(salt);
		
		user.setSalt(base64);
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public User getUser(final String email) {
		User user = (User) userRepository.findByEmail(email);
		return user;
	}

	public User getPassword(final String email, final String password, final UserRepository userRepository) {
		User user = getUser(email);
		return user;
	}

	public Iterable<User> getAllUsers(final UserRepository userRepository) {
		return userRepository.findAll();
	}
}