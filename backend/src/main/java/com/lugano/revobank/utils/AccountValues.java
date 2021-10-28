package com.lugano.revobank.utils;

import java.util.HashSet;

public class AccountValues {

	private static AccountValues instance;
	public HashSet<String> accountsCreated;

	private AccountValues() {
		this.accountsCreated = new HashSet<String>();
	}

	public static AccountValues getInstance() {
		if (instance == null) {
			instance = new AccountValues();
		}
		return instance;
	}

}
