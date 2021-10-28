package com.lugano.revobank.businessRules.specs;

import com.lugano.revobank.repositories.AccountRepository;

public abstract class BusinessRuleAccountWithDatabaseQuery {

	private AccountRepository accountRepository;

	public BusinessRuleAccountWithDatabaseQuery(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

}
