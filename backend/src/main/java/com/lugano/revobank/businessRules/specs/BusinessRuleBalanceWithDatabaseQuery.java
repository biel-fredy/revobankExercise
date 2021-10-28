package com.lugano.revobank.businessRules.specs;

import com.lugano.revobank.repositories.BalanceRepository;

public abstract class BusinessRuleBalanceWithDatabaseQuery {

	private BalanceRepository balanceRepository;

	public BusinessRuleBalanceWithDatabaseQuery(BalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}

	public BalanceRepository getBalanceRepository() {
		return balanceRepository;
	}

}
