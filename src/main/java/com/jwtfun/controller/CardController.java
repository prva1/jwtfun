package com.jwtfun.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jwtfun.model.entity.CreditCard;
import com.jwtfun.model.entity.User;
import com.jwtfun.service.CardService;
import com.jwtfun.utility.Helper;
import com.jwtfun.utility.View;

@Controller
public class CardController {

	@Autowired
	private CardService cardService;

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/creditCard", method = RequestMethod.POST)
	public ModelAndView addNewCreditCard(
			final @RequestParam(value = "nameCardType", required = true) String nameCardTypeId,
			final @RequestParam(value = "number", required = true) String number,
			final @RequestParam(value = "expireYY", required = true) String expireYY,
			final @RequestParam(value = "expireMM", required = true) String expireMM, final HttpSession session) {

		// TODO - take it from jwtToken, change it.
		User user = (User) session.getAttribute(View.SESSION.view());
		ModelMap model = new ModelMap();

		if (cardService.addNewCreditCard(nameCardTypeId, expireYY, expireMM, number, user)) {
			// TODO - message
			// model.addAttribute("name", card.getNameTypeId());
			return new ModelAndView(View.CREATE_CREDIT_CARD_DATA.view(), model);
		}
		// TODO - message
		// model.addAttribute("name", card.getNameTypeId());
		return new ModelAndView(View.CREATE_CREDIT_CARD_DATA.view(), model);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/showCreditCard", method = RequestMethod.POST)
	public ModelAndView getCreditCardData(final @RequestParam(value = "number", required = true) String number,
			 final HttpSession session) {

		ModelMap model = new ModelMap();

		//CreditCard card = cardService.getCreditCardData(number);
		// TODO - take it from jwtToken, change it.
		User user = (User) session.getAttribute(View.SESSION.view());
		List<CreditCard> creditCardList = cardService.getCreditCardData(number, user.getUserId());
       
		if (creditCardList != null) {	
			// TODO - CHANGE NO_DATA VIEW
			model.addAttribute(View.NO_DATA.view(), View.DATA_FOUND);
			model.addAttribute(View.CREDIT_CARD_LIST.view(), creditCardList);
			//model.addAttribute(View.NAME.view(), card.getNameTypeId().getNameType());
			//model.addAttribute(View.NUMBER.view(), card.getCreditCardNumber());
			//model.addAttribute(View.EXPIRED_DATE.view(), Helper.getModelAndViewFormatDate(card.getDate()));
		} else {

			model.addAttribute(View.NO_DATA.view(), View.DATA_NOT_FOUND);
		}

		return new ModelAndView(View.SHOW_DATA_CREDIT_CARD.view(), model);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/putCreditCardType", method = RequestMethod.POST)
	public ModelAndView getCreateCreditCardDataType(
			final @RequestParam(value = "nameType", required = true) String nameType) {

		ModelMap model = null;

		try {
			// TODO - Missing validation.

			return new ModelAndView(cardService.putCreditCardType(nameType));
		} catch (Exception e) {

			model = new ModelMap();
			model.addAttribute(View.STATUS.view(), View.EXIST.view());

			return new ModelAndView(View.ADD_CREDIT_CARD_TYPE.view(), model);
		}
	}
}