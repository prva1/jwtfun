package com.jwtfun.interfaces.modelhb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwtfun.model.entity.CreditCard;

public interface CardRepository extends JpaRepository<CreditCard, Long> {
	CreditCard findCreditCardByCreditCardNumber(final String creditCardNumber);
	
	@Query(value = "Select c from CreditCard c,User_CreditCard i, User u where c.cardId = i.creditCard and i.user = u.userId and u.userId =:userId and c.creditCardNumber like :creditCardNumber%")
	List<CreditCard> findCreditCardBySubCardNumber(@Param("creditCardNumber") String creditCardNumber,@Param("userId") Long userId);
}