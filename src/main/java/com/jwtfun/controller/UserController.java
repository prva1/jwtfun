package com.jwtfun.controller;

import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jwtfun.configuration.security.PasswordHandle;
import com.jwtfun.model.entity.User;
import com.jwtfun.service.JWTService;
import com.jwtfun.service.UserService;
import com.jwtfun.utility.Helper;
import com.jwtfun.utility.View;
import com.mysql.jdbc.StringUtils;

/**
 * Controller entry point for handling user.
 * 
 * Could be helpful to add swagger and security control with
 * WebSecurityConfigurerAdapter so is the proper way to do this. However this is
 * a fat jar already and change this is more configuration and another story in
 * turn of server Confg.
 */
@Controller
public class UserController {

	private UserService userService;	
	private PasswordHandle passwordHandle;

	/**
	 * In charge to create a new user in DB MYSQL.
	 * 
	 * 
	 * @param name
	 *            of the user.
	 * @param password
	 *            of the user.
	 * @param email
	 *            of the user.
	 * @param session
	 *            helper object.
	 * @return ModelAndView.
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView addNewUser(
			final @RequestParam(value = "name", required = true) String name,
			final @RequestParam(value = "password", required = true) String password,
			final @RequestParam(value = "email", required = true) String email,
			final @RequestParam(value = "usertype", required = true) String userType) {

		ModelMap model = new ModelMap();

		if (!isValidParameter(name, password.trim(), email.trim())) {

			// Using email as -Id-.
			User user = userService.getUser(email.trim());

			if (user == null) {
				// userService.addNewUser(name, password.trim(), email.trim(),userType, session);

				userService.addNewUser(name, password.trim(), email.trim(),userType);
			} else {

				// TODO - Add in the DataModel error message for email is used
				// already.
				return new ModelAndView(new RedirectView(View.CREATE_ACCOUNT.view()));
			}
			
			model.addAttribute(View.STATUS_KEY.view(), View.CREATE_ACCOUNT_SUCCESS.view());
			return new ModelAndView(View.LOGIN.view(), model);

		} else
		model.addAttribute(View.STATUS.view(), View.STATUS_FAILD.view());
		return new ModelAndView(View.CREATE_ACCOUNT.view(), model);
	}

	/**
	 * In charge to authenticate user credential to DB MySQL. (could be helpful
	 * to add swagger).
	 * 
	 * @param password
	 *            of the user.
	 * @param email
	 *            of the user.
	 * @param session
	 *            helper object.
	 * @return ModelAndView.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(final @RequestParam(value = "password", required = true) String password,
			final @RequestParam(value = "email", required = true) String email, final HttpSession session,
			final Device device) {

		// TODO - Also I could use AOP.
		ModelMap model = new ModelMap();
		if (!isValidParameter(password, email)) {

			// Validate email in db exists and retrieve the user.
			User user = userService.getUser(email);

			if (user == null) {

				// User not exist (email is the id of the user)
				model.addAttribute(View.STATUS.view(), View.STATUS_FAILD.view());
				return new ModelAndView(View.LOGIN.view(), model);
			} else {

				byte[] decodeBase64 = Base64.decodeBase64(user.getSalt());

				Stream<byte[]> stream1 = Stream.of(decodeBase64);
				stream1.forEach(x -> System.out.println(x));

				if (PasswordHandle.hash.getPasswordGeneration(password, decodeBase64).equals(user.getPassword())) {

					// call service to generate token.
					JWTService jWTService = new JWTService();

					String jwtToken = jWTService.createJWTToken(user, device);
					/*
					 * if (con!= null){
					 * con.setRequestProperty("X_AUTHORIZATION","Bearer " +
					 * jwtToken); }else{
					 * 
					 * System.out.println("con is null"); }
					 */

					System.out.println("Adding token in the model: " + jwtToken);

					model.addAttribute(View.TOKEN.view(), jwtToken);

					model.addAttribute(View.USER_NAME.view(), user.getName());
					// session.a
					session.setAttribute(View.TOKEN.view(), jwtToken);

					session.setAttribute(View.SESSION.view(), user);

					return new ModelAndView(View.INDEX.view(), model);

				} else {

					model.addAttribute(View.STATUS.view(), View.STATUS_FAILD.view());
					return new ModelAndView(View.LOGIN.view(), model);
				}
			}

		} else {
			// TODO - should provide a error message for espesific error.
			// model.addAttribute
			model.addAttribute(View.STATUS.view(), View.PARAMETER.view());
			return new ModelAndView(View.LOGIN.view(), model);
		}
	}

	/**
	 * Validate parameter for addNewUser controller.
	 * 
	 * @param name
	 *            of the user.
	 * @param password
	 *            of the user.
	 * @param email
	 *            of the user.
	 * @return
	 */
	private boolean isValidParameter(final String name, final String password, final String email) {
		return (StringUtils.isNullOrEmpty(name) || StringUtils.isNullOrEmpty(email) || !Helper.isValidEmail(email)
				|| StringUtils.isNullOrEmpty(password));
	}

	/**
	 * Validate parameter for access controller.
	 * 
	 * @param password
	 *            of the user.
	 * @param email
	 *            of the user.
	 * @return
	 */
	private boolean isValidParameter(final String password, final String email) {
		return (StringUtils.isNullOrEmpty(password) || StringUtils.isNullOrEmpty(email) || !Helper.isValidEmail(email));
	}

	/**
	 * @return the passwordHandle
	 */
	public PasswordHandle getPasswordHandle() {
		return passwordHandle;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setPasswordHandle(PasswordHandle passwordHandle) {
		this.passwordHandle = passwordHandle;
	}	
}