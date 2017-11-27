package com.jwtfun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtfun.model.entity.CreditCardType;
import com.jwtfun.service.CardService;
import com.jwtfun.utility.View;

@Controller
public class ViewController {
	
	@Autowired
	private CardController cardController;
	
	@Autowired
	private CardService cardService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return View.LOGIN.view();
	}
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut(final HttpServletRequest request) {
		request.getSession().invalidate();
		return View.LOGIN.view();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showWellcomePage() {
		return View.INDEX.view();
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String showCreateAccountPage() {
		return View.CREATE_ACCOUNT.view();
	}

	@RequestMapping(value = "/creditCardData", method = RequestMethod.GET)
	public String showCreditCardDataPage(ModelMap model) {
		// TODO - missing validation
		
		List<CreditCardType> list = cardService.getCreditCardType();
		
		model.addAttribute("databaseList", list);
				
		return View.CREATE_CREDIT_CARD_DATA.view();
	}

	@RequestMapping(value = "/getCreditCardType", method = RequestMethod.GET)
	public String getCreditCardDataType(ModelMap model) {
		// TODO - missing validation
		
		List<CreditCardType> list = cardService.getCreditCardType();
		
		model.addAttribute("databaseList", list);

		return View.SHOW_DATA_CREDIT_CARD.view();
	}
	
	@RequestMapping(value = "/searchCreditCard", method = RequestMethod.GET)
	public String showSearchCreditCardPage() {
		return View.SEARCH_CREDIT_CARD.view();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/creditCardTypePage", method = RequestMethod.GET)
	public String addCreditCardTypePage() {
		return View.ADD_CREDIT_CARD_TYPE.view();
	}
}