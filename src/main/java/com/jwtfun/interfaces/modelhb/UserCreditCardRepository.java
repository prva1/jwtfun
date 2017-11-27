package com.jwtfun.interfaces.modelhb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtfun.model.entity.User_CreditCard;
import com.jwtfun.model.entity.User_CreditCard_Id;

public interface UserCreditCardRepository extends JpaRepository<User_CreditCard, User_CreditCard_Id>{
	void save(User_CreditCard_Id user_CreditCard_Id);	
}
