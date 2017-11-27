package com.jwtfun.interfaces.modelhb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtfun.model.entity.CreditCardType;

public interface CreditCardTypeRepository extends JpaRepository<CreditCardType, Long> {
	CreditCardType findByNameType(String nameType);
}