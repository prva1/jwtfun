package com.jwtfun.utility;

public enum Status {
	SUCCESS(true), FAIL(false);

	private final boolean value;

	Status(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return this.value;
	}
}