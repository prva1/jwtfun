package com.jwtfun.utility;

public enum View {	
	LOGIN("login"),
	STATUS_KEY("status_key_success"),
	CREATE_CREDIT_CARD_DATA("creditCardData"),
	SEARCH_CREDIT_CARD("searchCreditCard"),
	ADD_CREDIT_CARD_TYPE("addCreditCardType"),
	MESSAGE("message"),
	SEARCH_CREDIT_CARD_TYPE("searchCreditCard"),
    SHOW_DATA_CREDIT_CARD("showDataCreditCard"),
    CREATE_ACCOUNT("createAccount"),
    CREATE_ACCOUNT_SUCCESS("Account created Succesfully - Login to continue"),
    INDEX("index"),
    STATUS_FAILD("Try again - something is incorrect"),
    PARAMETER("Parameter incorrect"),
    STATUS("status"),
    USER_NAME("userName"),
    CREDIT_CARD_TYPE("creditCardType"),
    SESSION("userSession"),
    NO_DATA("noData"),
    EXIST("Sorry, it already exist"),
    DATA_NOT_FOUND("Not Found"),
    DATA_FOUND(" Found"),
    NAME("name"),
    NUMBER("number"),
    EXPIRED_DATE("expiredDate"),
    TOKEN("token"),
    X_AUTHORIZATION("X-Authorization"),
    ADMINISTRATOR("Administrator"),
    CREDIT_CARD_LIST("creditCardList");
	
	private String view;

	View(String view) {
		this.view = view;
	}

	public String view() {
		return view;
	}	
}