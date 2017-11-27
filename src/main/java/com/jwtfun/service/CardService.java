package com.jwtfun.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtfun.interfaces.modelhb.CardRepository;
import com.jwtfun.interfaces.modelhb.CreditCardTypeRepository;
import com.jwtfun.interfaces.modelhb.UserCreditCardRepository;
import com.jwtfun.model.entity.CreditCard;
import com.jwtfun.model.entity.CreditCardType;
import com.jwtfun.model.entity.User;
import com.jwtfun.model.entity.User_CreditCard;
import com.jwtfun.model.entity.User_CreditCard_Id;
import com.jwtfun.utility.Helper;
import com.jwtfun.utility.Status;
import com.jwtfun.utility.View;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CreditCardTypeRepository creditCardTypeRepository;

	@Autowired
	private UserCreditCardRepository userCreditCardRepository;

	public boolean addNewCreditCard(final String creditCardTypeId, final String expireYY, final String expireMM,
			final String number, final User user) {

		try {
			CreditCardType creditCardType = creditCardTypeRepository.findOne(Long.parseLong(creditCardTypeId));
			Date dateExp = Helper.instanceLocalDate(expireYY, expireMM);
			CreditCard card = null;

			card = cardRepository.findCreditCardByCreditCardNumber(number);

			if (card == null) {
				card = new CreditCard();
				card.setCreditCardNumber(number);

				card.setNameTypeId(creditCardType);
				card.setDate(dateExp);

				cardRepository.saveAndFlush(card);
			}

			User_CreditCard_Id user_CreditCard_Id = new User_CreditCard_Id();
			user_CreditCard_Id.setCreditCardId(card.getCardId());
			user_CreditCard_Id.setUserId(user.getUserId());

			User_CreditCard user_CreditCard = new User_CreditCard();
			user_CreditCard.setId(user_CreditCard_Id);
			user_CreditCard.setCreditCard(card);
			user_CreditCard.setUser(user);

			userCreditCardRepository.saveAndFlush(user_CreditCard);

			return Status.SUCCESS.getValue();

		} catch (Exception e) {

			System.out.println("Log" + e);
			return Status.FAIL.getValue();
		}
	}

	public String putCreditCardType(final String nameType) {
		CreditCardType creditCardType = new CreditCardType();
		creditCardType.setNameType(nameType);
		creditCardTypeRepository.save(creditCardType);

		return View.ADD_CREDIT_CARD_TYPE.view();
	}

	public List<CreditCard> getCreditCardData(final String number, final Long userId) {
		// TODO - resolve the view table
		List<CreditCard> creditCardList = cardRepository.findCreditCardBySubCardNumber(number, userId);
		
		creditCardList.forEach(System.out::println);
		
		return creditCardList;
		
		// System.out.println();
		// return cardRepository.findCreditCardByCreditCardNumber(number);
	}

	public List<CreditCardType> getCreditCardType() {
		return creditCardTypeRepository.findAll();
	}
}
