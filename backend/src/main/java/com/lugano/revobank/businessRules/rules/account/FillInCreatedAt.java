package com.lugano.revobank.businessRules.rules.account;

import java.time.Instant;

import com.lugano.revobank.businessRules.results.ResultFacadeAccount;
import com.lugano.revobank.businessRules.specs.BusinessRuleAccount;
import com.lugano.revobank.entities.Account;

public class FillInCreatedAt implements BusinessRuleAccount {

	@Override
	public ResultFacadeAccount execute(Account account) {
		account.setCreatedAt(Instant.now());
		return new ResultFacadeAccount(null, account);
	}

}
